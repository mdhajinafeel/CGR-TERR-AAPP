package com.cgr.codrinterraerp.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.db.CGRTerraERPDatabase;
import com.cgr.codrinterraerp.model.request.LoginRequest;
import com.cgr.codrinterraerp.model.request.LogoutRequest;
import com.cgr.codrinterraerp.model.response.LoginDataResponse;
import com.cgr.codrinterraerp.model.response.LoginResponse;
import com.cgr.codrinterraerp.model.response.LogoutResponse;
import com.cgr.codrinterraerp.repository.AuthRepository;
import com.cgr.codrinterraerp.wrapper.SingleLiveEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class AuthViewModel extends ViewModel {

    private final AuthRepository authRepository;
    private final Context context;
    private String errorTitle, errorMessage;
    private final SingleLiveEvent<Boolean> progressState = new SingleLiveEvent<>();
    private final SingleLiveEvent<Boolean> logoutStatus = new SingleLiveEvent<>();
    private final SingleLiveEvent<LoginDataResponse> loginResult = new SingleLiveEvent<>();
    private final SingleLiveEvent<Boolean> loginStatus = new SingleLiveEvent<>();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final CGRTerraERPDatabase cgrTerraERPDatabase;

    @Inject
    public AuthViewModel(CGRTerraERPDatabase cgrTerraERPDatabase, AuthRepository authRepository, @ApplicationContext Context context) {
        this.cgrTerraERPDatabase = cgrTerraERPDatabase;
        this.authRepository = authRepository;
        this.context = context;
    }

    public LiveData<Boolean> getProgressState() {
        return progressState;
    }

    public void login(LoginRequest loginRequest) {
        progressState.postValue(true);
        authRepository.authLogin(loginRequest).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                progressState.postValue(false);
                if (response.isSuccessful() && response.body() != null) {

                    if (!response.body().isStatus()) {
                        loginStatus.postValue(false);
                        setErrorTitle(context.getString(R.string.login_failed));
                        setErrorMessage(response.body().getMessage() != null ? response.body().getMessage() : context.getString(R.string.common_error));
                    } else {
                        loginStatus.postValue(true);
                        loginResult.setValue(response.body().getData());
                    }
                } else {
                    loginStatus.postValue(false);
                    setErrorTitle(context.getString(R.string.login_failed));
                    setErrorMessage(response.body() != null ? response.body().getMessage() : context.getString(R.string.common_error));
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                progressState.postValue(false);
                loginStatus.postValue(false);
                setErrorTitle(context.getString(R.string.error));
                setErrorMessage(t.getMessage());
            }
        });
    }

    public void logout(LogoutRequest logoutRequest) {
        progressState.postValue(true);
        authRepository.authLogout(logoutRequest).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<LogoutResponse> call, @NonNull Response<LogoutResponse> response) {
                progressState.postValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    if (!response.body().isStatus()) {
                        logoutStatus.postValue(false);
                        setErrorTitle(context.getString(R.string.logout_failed));
                        setErrorMessage(response.body().getMessage() != null ? response.body().getMessage() : context.getString(R.string.common_error));
                    } else {
                        logoutStatus.postValue(true);
                    }
                } else {
                    logoutStatus.postValue(false);
                    setErrorTitle(context.getString(R.string.logout_failed));
                    setErrorMessage(response.body() != null ? response.body().getMessage() : context.getString(R.string.common_error));
                }
            }

            @Override
            public void onFailure(@NonNull Call<LogoutResponse> call, @NonNull Throwable t) {
                progressState.postValue(false);
                logoutStatus.postValue(false);
                setErrorTitle(context.getString(R.string.error));
                setErrorMessage(t.getMessage());
            }
        });
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public LiveData<Boolean> getLoginStatus() {
        return loginStatus;
    }

    public LiveData<Boolean> getLogoutStatus() {
        return logoutStatus;
    }

    public LiveData<LoginDataResponse> getLoginResult() {
        return loginResult;
    }

    public void clearAllTableData() {
        executor.execute(cgrTerraERPDatabase::clearAllTables);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        executor.shutdown();
    }
}