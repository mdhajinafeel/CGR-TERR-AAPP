package com.cgr.codrinterraerp.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AnimationUtils;

import androidx.appcompat.widget.AppCompatImageView;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.helper.PreferenceManager;
import com.cgr.codrinterraerp.ui.common.BaseActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
public class SplashActivity extends BaseActivity {

    private static final int SPLASH_DELAY = 2000; // 2 seconds
    private static final int INTENT_FLAGS = Intent.FLAG_ACTIVITY_NEW_TASK
            | Intent.FLAG_ACTIVITY_CLEAR_TASK
            | Intent.FLAG_ACTIVITY_CLEAR_TOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        hideKeyboard(this);

        AppCompatImageView ivLogo = findViewById(R.id.centerImage);
        ivLogo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.splash_combo));

        // Delay and move to next activity
        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            boolean isLoggedIn = PreferenceManager.INSTANCE.getLoggedIn();

            if (isLoggedIn) {
                redirectToMain();
            } else {
                redirectToLogin();
            }
        }, SPLASH_DELAY);
    }

    private void redirectToMain() {
        startActivity(new Intent(this, MainActivity.class)
                .putExtra("isFromLogin", false)
                .addFlags(INTENT_FLAGS));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void redirectToLogin() {
        startActivity(new Intent(this, LoginActivity.class)
                .addFlags(INTENT_FLAGS));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}