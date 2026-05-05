package com.cgr.codrinterraerp.ui.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.ui.common.BaseActivity;
import com.cgr.codrinterraerp.utils.AppLogger;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AppStatusActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_status);
        statusBarSetting();
        hideKeyboard(this);
        initComponents();
    }

    private void initComponents() {
        try {
            AppCompatImageView imgBack = findViewById(R.id.imgBack);
            AppCompatTextView txtTitle = findViewById(R.id.txtTitle);

            txtTitle.setText(getString(R.string.app_status));
            imgBack.setOnClickListener(view -> finish());



        } catch (Exception e) {
            AppLogger.e(getClass(), "initComponents", e);
        }
    }
}