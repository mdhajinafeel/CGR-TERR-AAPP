package com.cgr.codrinterraerp.services.interceptors;

import androidx.annotation.NonNull;

import com.cgr.codrinterraerp.BuildConfig;
import com.cgr.codrinterraerp.constants.IAPIConstants;
import com.cgr.codrinterraerp.helper.PreferenceManager;
import com.cgr.codrinterraerp.utils.AppLogger;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DynamicAuthInterceptor implements Interceptor {

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request;

        if (PreferenceManager.INSTANCE.getLoggedIn()) {
            String token = PreferenceManager.INSTANCE.getAccessToken();
            request = original.newBuilder()
                    .header("Authorization", "Bearer " + token)
                    .build();
        } else {
            String credential = Credentials.basic(
                    BuildConfig.CLIENT_USERNAME,
                    BuildConfig.CLIENT_PASSWORD
            );
            request = original.newBuilder()
                    .header("Authorization", credential)
                    .build();
        }

        Response response = chain.proceed(request);

        if (response.code() == 401 && PreferenceManager.INSTANCE.getLoggedIn()) {
            response.close();

            synchronized (this) {
                String newAccessToken = refreshToken();

                if (newAccessToken != null) {
                    PreferenceManager.INSTANCE.setAccessToken(newAccessToken);

                    Request newRequest = original.newBuilder()
                            .header("Authorization", "Bearer " + newAccessToken)
                            .build();

                    return chain.proceed(newRequest);
                } else {
                    PreferenceManager.INSTANCE.clearLoginSession();
                }
            }
        }

        return response;
    }

    private String refreshToken() {
        try {
            String refreshToken = PreferenceManager.INSTANCE.getRefreshToken();

            MediaType jsonMediaType = MediaType.parse("application/json");
            String bodyStr = "{ \"refreshToken\": \"" + refreshToken + "\" }";
            RequestBody body = RequestBody.create(bodyStr, jsonMediaType);

            Request request = new Request.Builder()
                    .url(BuildConfig.BASE_URL + IAPIConstants.REFRESH_TOKEN)
                    .post(body)
                    .build();

            OkHttpClient client = new OkHttpClient();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    String resStr = response.body().string();
                    JSONObject json = new JSONObject(resStr);

                    if (json.getBoolean("status")) {
                        JSONObject data = json.getJSONObject("data");

                        String newAccessToken = data.getString("accessToken");
                        String newRefreshToken = data.getString("refreshToken");

                        PreferenceManager.INSTANCE.setAccessToken(newAccessToken);
                        PreferenceManager.INSTANCE.setRefreshToken(newRefreshToken);

                        return newAccessToken;
                    }
                }
            }

        } catch (Exception e) {
            AppLogger.e(getClass(), "refreshToken", e);
        }

        return null;
    }
}