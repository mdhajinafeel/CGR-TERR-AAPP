package com.cgr.codrinterraerp.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.db.views.ReceptionView;
import com.cgr.codrinterraerp.ui.activities.ReceptionActivity;
import com.cgr.codrinterraerp.ui.adapters.RecyclerViewAdapter;
import com.cgr.codrinterraerp.ui.adapters.ViewHolder;
import com.cgr.codrinterraerp.utils.AppLogger;
import com.cgr.codrinterraerp.viewmodel.ReceptionViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ReceptionFragment extends Fragment {

    private RecyclerView rvReceptionLists;
    private LinearLayout llNoData;
    private RecyclerViewAdapter<ReceptionView> receptionViewRecyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reception, container, false);
        try {
            MaterialButton btnAddReception = view.findViewById(R.id.btnAddReception);
            rvReceptionLists = view.findViewById(R.id.rvReceptionLists);
            llNoData = view.findViewById(R.id.llNoData);

            ReceptionViewModel receptionViewModel = new ViewModelProvider(this).get(ReceptionViewModel.class);

            btnAddReception.setOnClickListener(v -> {

                ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(requireContext(), R.anim.fade_fast_in, R.anim.fade_fast_out);
                Intent intent = new Intent(requireActivity(), ReceptionActivity.class);
                intent.putExtra("isEdit", false);
                expenseResultLauncher.launch(intent, options);
            });

            // ✅ Setup RecyclerView
            rvReceptionLists.setLayoutManager(new LinearLayoutManager(getContext()));

            // ✅ Initialize adapter ONCE
            initializeAdapter();

            // ✅ Observe data (auto updates)
            receptionViewModel.getReceptionList().observe(getViewLifecycleOwner(), this::bindReceptionData);
        } catch (Exception e) {
            AppLogger.e(getClass(), "onCreateView", e);
        }
        return view;
    }

    private void initializeAdapter() {

        receptionViewRecyclerViewAdapter = new RecyclerViewAdapter<>(getContext(), new ArrayList<>(), R.layout.row_item_warehouse_reception) {
            @Override
            public void onPostBindViewHolder(ViewHolder holder, ReceptionView item) {
                if (item != null) {
                    holder.setViewText(R.id.tvIca, item.ica);
                    holder.setViewText(R.id.tvSupplier, item.supplierName);
                    holder.setViewText(R.id.tvPieces, String.valueOf(item.totalPieces));
                    holder.setViewText(R.id.tvGrossVolume, String.valueOf(item.totalGrossVolume));
                    holder.setViewText(R.id.tvNetVolume, String.valueOf(item.totalNetVolume));
                    holder.setViewText(R.id.tvDate, item.receptionDate);
                    holder.setViewText(R.id.tvMeasurement, item.measurementName);
                }
            }
        };

        receptionViewRecyclerViewAdapter.setOnItemClickListener((view, position) -> {
            ReceptionView item = receptionViewRecyclerViewAdapter.getItem(position);
            Intent intent = new Intent(requireActivity(), ReceptionActivity.class);
            intent.putExtra("isEdit", true);
            intent.putExtra("id", item.id);
            intent.putExtra("ica", item.ica);

            expenseResultLauncher.launch(intent);
        });

        rvReceptionLists.setAdapter(receptionViewRecyclerViewAdapter);
    }

    // ✅ Bind data (only update adapter)
    private void bindReceptionData(List<ReceptionView> list) {
        try {
            if (list != null && !list.isEmpty()) {
                receptionViewRecyclerViewAdapter.setItems(list); // 🔥 only update data
                rvReceptionLists.setVisibility(View.VISIBLE);
                llNoData.setVisibility(View.GONE);
            } else {
                llNoData.setVisibility(View.VISIBLE);
                rvReceptionLists.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            AppLogger.e(getClass(), "bindReceptionData", e);
        }
    }

    // ✅ Activity result launcher
    private final ActivityResultLauncher<Intent> expenseResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == Activity.RESULT_OK) {

                            // 🔥 Optional: You DON'T need this if Room works correctly
                            // receptionViewModel.refresh();

                            Toast.makeText(requireContext(),
                                    "Reception saved",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
            );
}