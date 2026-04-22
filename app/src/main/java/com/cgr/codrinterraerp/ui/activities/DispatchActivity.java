package com.cgr.codrinterraerp.ui.activities;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.ui.common.BaseActivity;
import com.cgr.codrinterraerp.utils.AppLogger;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DispatchActivity extends BaseActivity {

    private TextInputLayout tiContainerNumber, tiSupplierProduct, tiSupplierProductType, tiShippingLine, tiWarehouse, tiDispatchDate;
    private AppCompatEditText etContainerNumber, etSupplierProduct, etSupplierProductType, etShippingLine, etWarehouse, etDispatchDate;
    private MaterialButton btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch);
        statusBarSetting();
        hideKeyboard(this);
        initComponents();
    }

    private void initComponents() {
        try {
            AppCompatImageView imgBack = findViewById(R.id.imgBack);
            AppCompatTextView txtTitle = findViewById(R.id.txtTitle);

            txtTitle.setText(getString(R.string.add_dispatch));
            imgBack.setOnClickListener(v -> finish());
        } catch (Exception e) {
            AppLogger.e(getClass(), "initComponents", e);
        }
    }
}