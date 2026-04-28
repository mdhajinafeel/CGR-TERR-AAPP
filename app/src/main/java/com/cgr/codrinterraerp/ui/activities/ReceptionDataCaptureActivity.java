package com.cgr.codrinterraerp.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.db.entities.MeasurementSystemFormulaVariables;
import com.cgr.codrinterraerp.db.relations.FormulaWithVariables;
import com.cgr.codrinterraerp.db.views.DispatchView;
import com.cgr.codrinterraerp.db.views.ReceptionView;
import com.cgr.codrinterraerp.ui.adapters.RecyclerViewAdapter;
import com.cgr.codrinterraerp.ui.adapters.ViewHolder;
import com.cgr.codrinterraerp.ui.common.BaseActivity;
import com.cgr.codrinterraerp.utils.AppLogger;
import com.cgr.codrinterraerp.utils.CommonUtils;
import com.cgr.codrinterraerp.utils.FormulaEngine;
import com.cgr.codrinterraerp.viewmodel.DispatchViewModel;
import com.cgr.codrinterraerp.viewmodel.ReceptionDataViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ReceptionDataCaptureActivity extends BaseActivity {

    private TextInputLayout tiCircumference, tiLength, tiPieces;
    private AppCompatTextView tvNoDataFound;
    private TextInputEditText etCircumference, etLength, etPieces;
    private RecyclerView rvAvailableContainers;
    private RecyclerViewAdapter<DispatchView> dispatchViewRecyclerViewAdapter;
    private int normalColor, errorColor;
    private ReceptionDataViewModel receptionDataViewModel;
    private ReceptionView receptionView;
    private FormulaWithVariables formulaData;

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
            tiCircumference = findViewById(R.id.tiCircumference);
            tiLength = findViewById(R.id.tiLength);
            tiPieces = findViewById(R.id.tiPieces);
            etCircumference = findViewById(R.id.etCircumference);
            etLength = findViewById(R.id.etLength);
            etPieces = findViewById(R.id.etPieces);
            MaterialButton mbSubmit = findViewById(R.id.mbSubmit);
            MaterialButton mbClear = findViewById(R.id.mbClear);

            Bundle bundle = getIntent().getExtras();

            if (bundle != null) {
                txtTitle.setText(getString(R.string.measurement_data));
                txtSubTitle.setText(bundle.getString("ica"));
                imgBack.setOnClickListener(v -> finish());

                DispatchViewModel dispatchViewModel = new ViewModelProvider(this).get(DispatchViewModel.class);
                receptionDataViewModel = new ViewModelProvider(this).get(ReceptionDataViewModel.class);

                receptionView = (ReceptionView) bundle.getSerializable("receptionDetails");

                normalColor = getColor(R.color.colorDarkGreen);
                errorColor = getColor(R.color.colorErrorOrange);

                // ✅ Setup RecyclerView (HORIZONTAL)
                rvAvailableContainers.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

                // ✅ Initialize adapter ONCE
                initializeAdapter();

                // ✅ Observe data (auto updates)
                dispatchViewModel.getAvailableContainerList().observe(this, this::bindAvailableContainerData);
                dispatchViewModel.availableContainerload();

                CommonUtils.clearErrorOnTyping(etCircumference, tiCircumference);
                CommonUtils.clearErrorOnTyping(etLength, tiLength);
                CommonUtils.clearErrorOnTyping(etPieces, tiPieces);

                mbClear.setOnClickListener(v -> clearFields());

                mbSubmit.setOnClickListener(v -> {
                    if (validateInputs()) {
                        submitMeasurementData();
                        clearFields();
                    }
                });

                clearFields();
                fetchFormulas();
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.common_error), Toast.LENGTH_SHORT).show();
                finish();
            }
        } catch (Exception e) {
            AppLogger.e(getClass(), "initComponents", e);
        }
    }

    private void initializeAdapter() {

        dispatchViewRecyclerViewAdapter = new RecyclerViewAdapter<>(getApplicationContext(), new ArrayList<>(), R.layout.row_item_available_containers) {
            @Override
            public void onPostBindViewHolder(ViewHolder holder, DispatchView dispatchView) {
                if (dispatchView != null) {
                    holder.setViewText(R.id.tvContainerNumber, dispatchView.containerNumber);
                    holder.setViewText(R.id.tvShippingLine, dispatchView.shippingLine);
                    holder.setViewText(R.id.tvPieces, String.valueOf(dispatchView.totalPieces));
                    holder.setViewText(R.id.tvGrossVolume, String.valueOf(dispatchView.totalGrossVolume));
                    holder.setViewText(R.id.tvNetVolume, String.valueOf(dispatchView.totalNetVolume));
                    holder.setViewText(R.id.tvAvgGirth, String.valueOf(dispatchView.avgGirth));
                }
            }
        };

        rvAvailableContainers.setAdapter(dispatchViewRecyclerViewAdapter);
    }

    // ✅ Bind data (only update adapter)
    private void bindAvailableContainerData(List<DispatchView> list) {
        try {
            if (list != null && !list.isEmpty()) {
                dispatchViewRecyclerViewAdapter.setItems(list); // 🔥 only update data
                rvAvailableContainers.setVisibility(View.VISIBLE);
                tvNoDataFound.setVisibility(View.GONE);
            } else {
                tvNoDataFound.setVisibility(View.VISIBLE);
                rvAvailableContainers.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            AppLogger.e(getClass(), "bindDispatchData", e);
        }
    }

    private boolean validateInputs() {

        hideKeyboard(this);

        String circ = Objects.requireNonNull(etCircumference.getText()).toString().trim();
        String length = Objects.requireNonNull(etLength.getText()).toString().trim();
        String pieces = Objects.requireNonNull(etPieces.getText()).toString().trim();

        boolean isValid = true;

        // ❗ Reset first
        resetBorders();

        if (circ.isEmpty()) {
            tiCircumference.setBoxStrokeColor(errorColor);
            etCircumference.requestFocus();
            isValid = false;
        }

        if (length.isEmpty()) {
            tiLength.setBoxStrokeColor(errorColor);
            if (isValid) etLength.requestFocus();
            isValid = false;
        }

        if(!length.isEmpty()) {
            double l = Double.parseDouble(length);
            if(l <= 100) {
                Toast.makeText(getApplicationContext(), "Length must be greater than 100", Toast.LENGTH_SHORT).show();
                isValid = false;
            }
        }

        if (pieces.isEmpty()) {
            tiPieces.setBoxStrokeColor(errorColor);
            if (isValid) etPieces.requestFocus();
            isValid = false;
        }

        return isValid;
    }

    private void resetBorders() {
        tiCircumference.setBoxStrokeColor(normalColor);
        tiLength.setBoxStrokeColor(normalColor);
        tiPieces.setBoxStrokeColor(normalColor);
    }

    private void clearFields() {
        etCircumference.setText("");
        etLength.setText("");
        etPieces.setText("1");
        etCircumference.requestFocus();
    }

    private void fetchFormulas() {
        try {
            formulaData = receptionDataViewModel.getFormulasWithVariables(receptionView.measurementSystem);
        } catch (Exception e) {
            AppLogger.e(getClass(), "fetchFormulas", e);
        }
    }

    private void submitMeasurementData() {
        try {
            double c = Double.parseDouble(Objects.requireNonNull(etCircumference.getText()).toString());
            double l = Double.parseDouble(Objects.requireNonNull(etLength.getText()).toString());
            int pieces = Integer.parseInt(Objects.requireNonNull(etPieces.getText()).toString());

            // ✅ Build variable map dynamically
            Map<String, Double> inputValues = new HashMap<>();

            for (MeasurementSystemFormulaVariables v : formulaData.variables) {
                String key = v.getVarName();
                switch (key) {
                    case "c":
                        inputValues.put("c", c);
                        break;

                    case "l":
                        inputValues.put("l", l);
                        break;
                }
            }

            // ✅ Evaluate
            double value = FormulaEngine.evaluate(formulaData.formula.getFormula(), inputValues);

            // ✅ Apply rounding
            double finalValue = FormulaEngine.applyRounding(value, formulaData.formula.getRoundPrecision(), formulaData.formula.getRoundingType());

            // ✅ Multiply pieces
            double total = finalValue * pieces;

            // ✅ Display
            Toast.makeText(getApplicationContext(), "Per Piece: " + finalValue + "\nTotal: " + total, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            AppLogger.e(getClass(), "submitMeasurementData", e);
        }
    }
}