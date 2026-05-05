package com.cgr.codrinterraerp.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.db.views.DispatchView;
import com.cgr.codrinterraerp.ui.activities.DispatchActivity;
import com.cgr.codrinterraerp.ui.activities.DispatchDataActivity;
import com.cgr.codrinterraerp.ui.adapters.RecyclerViewAdapter;
import com.cgr.codrinterraerp.ui.adapters.ViewHolder;
import com.cgr.codrinterraerp.utils.AppLogger;
import com.cgr.codrinterraerp.viewmodel.DispatchViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DispatchFragment extends Fragment {

    private RecyclerView rvDispatchLists;
    private LinearLayout llNoData;
    private RecyclerViewAdapter<DispatchView> dispatchViewRecyclerViewAdapter;
    private DispatchViewModel dispatchViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dispatch, container, false);
        try {
            MaterialButton btnAddReception = view.findViewById(R.id.btnAddDispatch);
            rvDispatchLists = view.findViewById(R.id.rvDispatchLists);
            llNoData = view.findViewById(R.id.llNoData);

            dispatchViewModel = new ViewModelProvider(this).get(DispatchViewModel.class);

            btnAddReception.setOnClickListener(v -> {

                ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(requireContext(), R.anim.fade_fast_in, R.anim.fade_fast_out);
                Intent intent = new Intent(requireActivity(), DispatchActivity.class);
                intent.putExtra("isEdit", false);
                dispatchResultLauncher.launch(intent, options);
            });

            // ✅ Setup RecyclerView
            rvDispatchLists.setLayoutManager(new LinearLayoutManager(getContext()));

            // ✅ Initialize adapter ONCE
            initializeAdapter();

            // ✅ Observe data (auto updates)
            dispatchViewModel.getDispatchList().observe(getViewLifecycleOwner(), this::bindDispatchData);
            dispatchViewModel.load();
        } catch (Exception e) {
            AppLogger.e(getClass(), "onCreateView", e);
        }
        return view;
    }

    private void initializeAdapter() {

        dispatchViewRecyclerViewAdapter = new RecyclerViewAdapter<>(getContext(), new ArrayList<>(), R.layout.row_item_warehouse_dispatch) {
            @Override
            public void onPostBindViewHolder(ViewHolder holder, DispatchView dispatchView) {
                if (dispatchView != null) {
                    holder.setViewText(R.id.tvContainerNumber, dispatchView.containerNumber);
                    holder.setViewText(R.id.tvContainerCategory, dispatchView.category);
                    holder.setViewText(R.id.tvShippingLine, dispatchView.shippingLine);
                    holder.setViewText(R.id.tvPieces, String.valueOf(dispatchView.totalPieces));

                    if(dispatchView.productTypeId == 1 || dispatchView.productTypeId == 3) {
                        holder.setViewImageDrawable(R.id.ivTypeIcon, ContextCompat.getDrawable(requireContext(), R.drawable.ic_square_logs));
                        holder.setViewText(R.id.tvGrossTitle, getString(R.string.volume_pie));
                        holder.setViewText(R.id.tvGrossVolume, String.valueOf(dispatchView.totalVolumePie));
                    } else {
                        holder.setViewImageDrawable(R.id.ivTypeIcon, ContextCompat.getDrawable(requireContext(), R.drawable.ic_round_logs));
                        holder.setViewText(R.id.tvGrossTitle, getString(R.string.gross_volume));
                        holder.setViewText(R.id.tvGrossVolume, String.valueOf(dispatchView.totalGrossVolume));

                        if(dispatchView.categoryId == 1) {
                            holder.setViewText(R.id.tvAvgGirthTitle, getString(R.string.cft));
                            holder.setViewText(R.id.tvAvgGirth, String.valueOf(dispatchView.cft));
                        } else {
                            holder.setViewText(R.id.tvAvgGirthTitle, getString(R.string.avg_girth));
                            holder.setViewText(R.id.tvAvgGirth, String.valueOf(dispatchView.avgGirth));
                        }
                    }

                    holder.setViewText(R.id.tvNetVolume, String.valueOf(dispatchView.totalNetVolume));
                    holder.setViewText(R.id.tvDate, dispatchView.dispatchDate);

                    holder.getView(R.id.btnEditDispatch).setTag(dispatchView);
                    holder.getView(R.id.btnDeleteDispatch).setTag(dispatchView);

                    holder.getView(R.id.btnEditDispatch).setOnClickListener(v -> {
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(requireContext(), R.anim.fade_fast_in, R.anim.fade_fast_out);
                        Intent intent = new Intent(requireActivity(), DispatchActivity.class);
                        intent.putExtra("isEdit", true);
                        intent.putExtra("dispatchDetails", dispatchView);
                        dispatchResultLauncher.launch(intent, options);
                    });

                    holder.getView(R.id.btnDeleteDispatch).setOnClickListener(v -> deleteDispatch(dispatchView));

                    holder.getView(R.id.btnContainerData).setOnClickListener(view -> {
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(requireContext(), R.anim.fade_fast_in, R.anim.fade_fast_out);
                        Intent intent = new Intent(requireActivity(), DispatchDataActivity.class);
                        intent.putExtra("dispatchDetails", dispatchView);
                        dispatchDataResultLauncher.launch(intent, options);
                    });
                }
            }
        };

        rvDispatchLists.setAdapter(dispatchViewRecyclerViewAdapter);
    }

    // ✅ Bind data (only update adapter)
    private void bindDispatchData(List<DispatchView> list) {
        try {
            if (list != null && !list.isEmpty()) {
                dispatchViewRecyclerViewAdapter.setItems(list); // 🔥 only update data
                rvDispatchLists.setVisibility(View.VISIBLE);
                llNoData.setVisibility(View.GONE);
            } else {
                llNoData.setVisibility(View.VISIBLE);
                rvDispatchLists.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            AppLogger.e(getClass(), "bindDispatchData", e);
        }
    }

    // ✅ Activity result launcher
    private final ActivityResultLauncher<Intent> dispatchResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK) {

                            // 🔥 Optional: You DON'T need this if Room works correctly
                            dispatchViewModel.load();

                            Toast.makeText(requireContext(), getString(R.string.data_added_successfully), Toast.LENGTH_SHORT).show();
                        }
                    }
            );

    private final ActivityResultLauncher<Intent> dispatchDataResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            dispatchViewModel.load();
                            Toast.makeText(requireContext(), getString(R.string.data_updated_successfully), Toast.LENGTH_SHORT).show();
                        }
                    }
            );

    private void deleteDispatch(DispatchView dispatchView) {
        try {
            LayoutInflater dialogInflater = LayoutInflater.from(requireContext());
            View dialogView = dialogInflater.inflate(R.layout.custom_dialog_delete, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setView(dialogView);

            AlertDialog dialog = builder.create();
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);

            AppCompatTextView dialogHeader = dialogView.findViewById(R.id.dialogHeader);
            AppCompatTextView dialogBody = dialogView.findViewById(R.id.dialogBody);
            MaterialButton btnDelete = dialogView.findViewById(R.id.btnDelete);
            MaterialButton btnCancel = dialogView.findViewById(R.id.btnCancel);

            dialogHeader.setText(R.string.confirmation);
            dialogBody.setText(R.string.delete_confirmation);

            btnCancel.setOnClickListener(v -> dialog.dismiss());

            btnDelete.setOnClickListener(v -> new Thread(() -> {

                int deleted = dispatchViewModel.deleteDispatchDetails(dispatchView.tempDispatchId, dispatchView.dispatchId, System.currentTimeMillis());

                // Switch to main thread safely
                new Handler(Looper.getMainLooper()).post(() -> {

                    // ✅ Prevent crash if fragment is not attached
                    if (!isAdded() || getContext() == null) return;

                    if (deleted > 0) {
                        Toast.makeText(getContext(), getString(R.string.data_deleted), Toast.LENGTH_SHORT).show();
                        // ✅ Refresh data
                        dispatchViewModel.load();
                    } else {
                        Toast.makeText(getContext(), getString(R.string.data_deleted_failed), Toast.LENGTH_SHORT).show();
                    }

                    // ✅ Safe dialog dismiss
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                });

            }).start());

            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        } catch (Exception e) {
            AppLogger.e(getClass(), "deleteReception", e);
        }
    }
}