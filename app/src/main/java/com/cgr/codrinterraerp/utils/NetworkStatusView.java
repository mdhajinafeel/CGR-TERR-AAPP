package com.cgr.codrinterraerp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.TrafficStats;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.helper.PreferenceManager;

import java.util.Locale;

@SuppressWarnings("deprecation")
public class NetworkStatusView extends LinearLayout {

    private AppCompatImageView imgNetwork;
    private AppCompatTextView txtSpeed;
    private final Handler handler = new Handler(Looper.getMainLooper());

    private long lastRxBytes = 0;
    private long lastTime = 0;

    private TelephonyManager telephonyManager;
    private MobileSignalCallback mobileSignalCallback;

    public NetworkStatusView(Context context) {
        super(context);
        init();
    }

    public NetworkStatusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_network_status, this, true);

        imgNetwork = findViewById(R.id.imgWifi);
        txtSpeed = findViewById(R.id.txtSpeed);

        telephonyManager = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
    }

    /* ================= PUBLIC API ================= */

    public void start() {
        registerMobileSignalListener();
        handler.post(updateRunnable);
    }

    public void stop() {
        handler.removeCallbacks(updateRunnable);
        unregisterMobileSignalListener();
    }

    /* ================= UPDATE LOOP ================= */

    private final Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {

            double speedKBps = getSpeedKbps();

            if (isWifiConnected()) {
                updateWifiIcon(speedKBps);
            } else if (isMobileConnected()) {
                updateMobileIcon(speedKBps);
            } else {
                imgNetwork.setAlpha(0.4f);
            }

            updateSpeedText(speedKBps);

            handler.postDelayed(this, 1000);
        }
    };

    /* ================= WIFI ================= */

    private void updateWifiIcon(double speedKBps) {

        imgNetwork.setAlpha(1f);
        imgNetwork.clearColorFilter();

        int iconRes;

        if (speedKBps < 1) {
            iconRes = R.drawable.ic_wifi_0;      // Idle
        } else if (speedKBps < 30) {
            iconRes = R.drawable.ic_wifi_1;      // Slow
        } else if (speedKBps < 150) {
            iconRes = R.drawable.ic_wifi_2;      // Normal
        } else {
            iconRes = R.drawable.ic_wifi_3;      // Fast
        }

        imgNetwork.setImageResource(iconRes);
    }

    private boolean isWifiConnected() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkCapabilities caps = cm.getNetworkCapabilities(cm.getActiveNetwork());

        return caps != null && caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
    }

    /* ================= MOBILE ================= */

    private void updateMobileIcon(double speedKBps) {

        imgNetwork.setAlpha(1f);
        imgNetwork.setImageResource(R.drawable.ic_mobile_signal);

        int color;
        if (speedKBps < 5) {
            color = ContextCompat.getColor(getContext(), R.color.red);
        } else if (speedKBps < 50) {
            color = ContextCompat.getColor(getContext(), R.color.orange);
        } else if (speedKBps < 200) {
            color = ContextCompat.getColor(getContext(), R.color.green);
        } else {
            color = ContextCompat.getColor(getContext(), R.color.darkGreen);
        }

        imgNetwork.setColorFilter(color);
    }

    private boolean isMobileConnected() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkCapabilities caps = cm.getNetworkCapabilities(cm.getActiveNetwork());

        return caps != null && caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR);
    }

    /* ================= SPEED ================= */

    private void updateSpeedText(double speedKBps) {

        Locale locale = getLocale();

        // Speed formatting
        if (speedKBps > 1024) {
            txtSpeed.setText(String.format(locale, "%.1f MB/s", speedKBps / 1024));
        } else if (speedKBps > 0) {
            txtSpeed.setText(String.format(locale, "%.1f KB/s", speedKBps));
        } else {
            txtSpeed.setText(String.format(locale, "%.0f KB/s", speedKBps));
        }
    }

    @NonNull
    private static Locale getLocale() {
        String localeStr = PreferenceManager.INSTANCE.getCurrencyFormat();

        Locale locale;

        // Fallback to default locale if null/empty/invalid
        if (localeStr.trim().isEmpty() || !localeStr.contains("_")) {
            locale = Locale.getDefault();
        } else {
            try {
                String[] parts = localeStr.split("_");
                locale = new Locale(parts[0], parts[1]);
            } catch (Exception e) {
                locale = Locale.getDefault(); // fallback if parsing fails
            }
        }
        return locale;
    }

    private double getSpeedKbps() {

        long nowBytes = TrafficStats.getTotalRxBytes();
        long nowTime = System.currentTimeMillis();

        if (lastTime == 0) {
            lastRxBytes = nowBytes;
            lastTime = nowTime;
            return 0;
        }

        long byteDiff = nowBytes - lastRxBytes;
        long timeDiff = nowTime - lastTime;

        lastRxBytes = nowBytes;
        lastTime = nowTime;

        return (byteDiff * 1000.0) / timeDiff / 1024;
    }

    /* ================= MOBILE SIGNAL LISTENER ================= */

    private void registerMobileSignalListener() {

        if (telephonyManager == null) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (mobileSignalCallback == null) {
                mobileSignalCallback = new MobileSignalCallback();
            }

            telephonyManager.registerTelephonyCallback(getContext().getMainExecutor(), mobileSignalCallback);

        } else {
            telephonyManager.listen(new PhoneStateListener() {
                @Override
                public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                }
            }, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
        }
    }

    private void unregisterMobileSignalListener() {

        if (telephonyManager == null) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (mobileSignalCallback != null) {
                telephonyManager.unregisterTelephonyCallback(mobileSignalCallback);
            }
        } else {
            telephonyManager.listen(null, PhoneStateListener.LISTEN_NONE);
        }
    }

    /* ================= CALLBACK CLASS ================= */

    @RequiresApi(api = Build.VERSION_CODES.S)
    private static class MobileSignalCallback extends TelephonyCallback implements TelephonyCallback.SignalStrengthsListener {

        @Override
        public void onSignalStrengthsChanged(@NonNull SignalStrength signalStrength) {
            // Signal retained for future use if needed
        }
    }
}