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
import com.cgr.codrinterraerp.db.views.DispatchView;
import com.cgr.codrinterraerp.ui.activities.DispatchActivity;
import com.cgr.codrinterraerp.ui.adapters.RecyclerViewAdapter;
import com.cgr.codrinterraerp.ui.adapters.ViewHolder;
import com.cgr.codrinterraerp.utils.AppLogger;
import com.cgr.codrinterraerp.viewmodel.DispatchViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

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
                    holder.setViewText(R.id.tvShippingLine, dispatchView.shippingLine);
                    holder.setViewText(R.id.tvPieces, String.valueOf(dispatchView.totalPieces));
                    holder.setViewText(R.id.tvGrossVolume, String.valueOf(dispatchView.totalGrossVolume));
                    holder.setViewText(R.id.tvNetVolume, String.valueOf(dispatchView.totalNetVolume));
                    holder.setViewText(R.id.tvDate, dispatchView.dispatchDate);
                    holder.setViewText(R.id.tvAvgGirth, String.valueOf(dispatchView.avgGirth));

                    holder.getView(R.id.btnEditDispatch).setTag(dispatchView);
                    holder.getView(R.id.btnDeleteDispatch).setTag(dispatchView);

                    holder.getView(R.id.btnEditDispatch).setOnClickListener(v -> {
                        DispatchView clickedItem = (DispatchView) v.getTag();
                        Toast.makeText(getContext(), "Edit - " + clickedItem.containerNumber, Toast.LENGTH_SHORT).show();
                    });

                    holder.getView(R.id.btnDeleteDispatch).setOnClickListener(v -> {
                        DispatchView clickedItem = (DispatchView) v.getTag();
                        Toast.makeText(getContext(), "Delete - " + clickedItem.containerNumber, Toast.LENGTH_SHORT).show();
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
}