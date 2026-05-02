package com.cgr.codrinterraerp.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.db.views.DispatchView;
import com.cgr.codrinterraerp.model.ContainerWithReception;
import com.cgr.codrinterraerp.ui.adapters.RecyclerViewAdapter;
import com.cgr.codrinterraerp.ui.adapters.ViewHolder;
import com.cgr.codrinterraerp.ui.common.BaseActivity;
import com.cgr.codrinterraerp.utils.AppLogger;
import com.cgr.codrinterraerp.utils.CommonUtils;
import com.cgr.codrinterraerp.viewmodel.DispatchDataViewModel;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DispatchDataActivity extends BaseActivity {

    private AppCompatTextView tvContainerNumber, tvShippingLine, tvPieces, tvGrossVolume, tvNetVolume, tvNoDispatchData;
    private MaterialCardView cardDispatch;
    private RecyclerView rvDispatchData;
    private DispatchDataViewModel dispatchDataViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_data);
        statusBarSetting();
        hideKeyboard(this);
        initComponents();
    }

    private void initComponents() {
        try {
            AppCompatImageView imgBack = findViewById(R.id.imgBack);
            AppCompatTextView txtTitle = findViewById(R.id.txtTitle);
            AppCompatTextView txtSubTitle = findViewById(R.id.txtSubTitle);
            tvContainerNumber = findViewById(R.id.tvContainerNumber);
            tvShippingLine = findViewById(R.id.tvShippingLine);
            tvPieces = findViewById(R.id.tvPieces);
            tvGrossVolume = findViewById(R.id.tvGrossVolume);
            tvNetVolume = findViewById(R.id.tvNetVolume);
            tvNoDispatchData = findViewById(R.id.tvNoDispatchData);
            cardDispatch = findViewById(R.id.cardDispatch);
            rvDispatchData = findViewById(R.id.rvDispatchData);

            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {

                DispatchView dispatchView = (DispatchView) bundle.getSerializable("dispatchDetails");

                if (dispatchView != null) {
                    txtTitle.setText(getString(R.string.dispatch_data));
                    imgBack.setOnClickListener(v -> finish());

                    txtSubTitle.setVisibility(View.VISIBLE);
                    txtSubTitle.setText(getString(R.string.reception_subtitle, dispatchView.containerNumber, dispatchView.shippingLine));

                    dispatchDataViewModel = new ViewModelProvider(this).get(DispatchDataViewModel.class);

                    bindData(dispatchView);
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.common_error), Toast.LENGTH_SHORT).show();
                    finish();
                }
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.common_error), Toast.LENGTH_SHORT).show();
                finish();
            }
        } catch (Exception e) {
            AppLogger.e(getClass(), "initComponents", e);
        }
    }

    private void bindData(DispatchView dispatchView) {
        try {
            tvContainerNumber.setText(dispatchView.containerNumber);
            tvShippingLine.setText(dispatchView.shippingLine);
            tvPieces.setText(String.valueOf(dispatchView.totalPieces));
            tvGrossVolume.setText(String.valueOf(dispatchView.totalGrossVolume));
            tvNetVolume.setText(String.valueOf(dispatchView.totalNetVolume));

            List<ContainerWithReception> containerWithReceptionList = dispatchDataViewModel.fetchContainerData(dispatchView.dispatchId, dispatchView.tempDispatchId);

            if (containerWithReceptionList != null && !containerWithReceptionList.isEmpty()) {

                RecyclerViewAdapter<ContainerWithReception> containerDataRecyclerViewAdapter =
                        new RecyclerViewAdapter<>(getApplicationContext(), containerWithReceptionList, R.layout.row_item_container_data) {

                            @Override
                            public void onPostBindViewHolder(ViewHolder holder, ContainerWithReception item) {

                                holder.setViewText(R.id.tvGirth, CommonUtils.formatNumber(item.getCircumference()));
                                holder.setViewText(R.id.tvLength, CommonUtils.formatNumber(item.getLength()));
                                holder.setViewText(R.id.tvPieces, String.valueOf(item.getPieces()));
                                holder.setViewText(R.id.tvIca, item.getIca());
                                holder.setViewText(R.id.tvGrossVolume, CommonUtils.formatNumber(item.getGrossVolume()));
                                holder.setViewText(R.id.tvNetVolume, CommonUtils.formatNumber(item.getNetVolume()));
                            }
                        };

                rvDispatchData.setLayoutManager(new LinearLayoutManager(this));
                rvDispatchData.setAdapter(containerDataRecyclerViewAdapter);
                tvNoDispatchData.setVisibility(View.GONE);
                cardDispatch.setVisibility(View.VISIBLE);

            } else {
                tvNoDispatchData.setVisibility(View.VISIBLE);
                cardDispatch.setVisibility(View.GONE);
            }

        } catch (Exception e) {
            AppLogger.e(getClass(), "bindData", e);
        }
    }
}