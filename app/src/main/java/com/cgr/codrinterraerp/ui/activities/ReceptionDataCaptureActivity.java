package com.cgr.codrinterraerp.ui.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.ui.common.BaseActivity;
import com.cgr.codrinterraerp.utils.AppLogger;
import com.google.android.material.textfield.TextInputEditText;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ReceptionDataCaptureActivity extends BaseActivity {

    private AppCompatTextView tvNoDataFound;
    private TextInputEditText etCircumference, etLength, etPieces;
    private AppCompatImageView ivDone, ivClear;
    private RecyclerView rvAvailableContainers;
    private Bundle bundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reception_data_capture);
        statusBarSetting();
        hideKeyboard(this);
        initComponents();
    }

    private void initComponents() {
        try {
            AppCompatImageView imgBack = findViewById(R.id.imgBack);
            AppCompatTextView txtTitle = findViewById(R.id.txtTitle);
            AppCompatTextView txtSubTitle = findViewById(R.id.txtSubTitle);
            tvNoDataFound = findViewById(R.id.tvNoDataFound);
            rvAvailableContainers = findViewById(R.id.rvAvailableContainers);
            etCircumference = findViewById(R.id.etCircumference);
            etLength = findViewById(R.id.etLength);
            etPieces = findViewById(R.id.etPieces);
            ivDone = findViewById(R.id.ivDone);
            ivClear = findViewById(R.id.ivClear);

            bundle = getIntent().getExtras();

            if (bundle != null) {
                txtTitle.setText(getString(R.string.measurement_data));
                txtSubTitle.setText(bundle.getString("ica"));
                imgBack.setOnClickListener(v -> finish());


            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.common_error), Toast.LENGTH_SHORT).show();
                finish();
            }
        } catch (Exception e) {
            AppLogger.e(getClass(), "initComponents", e);
        }
    }
}