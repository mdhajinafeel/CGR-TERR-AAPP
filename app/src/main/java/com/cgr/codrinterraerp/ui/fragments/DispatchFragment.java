package com.cgr.codrinterraerp.ui.fragments;

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
import androidx.recyclerview.widget.RecyclerView;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.ui.activities.DispatchActivity;
import com.cgr.codrinterraerp.ui.activities.ReceptionActivity;
import com.cgr.codrinterraerp.utils.AppLogger;
import com.google.android.material.button.MaterialButton;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DispatchFragment extends Fragment {

    private RecyclerView rvDispatchLists;
    private LinearLayout llNoData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dispatch, container, false);
        try {
            MaterialButton btnAddReception = view.findViewById(R.id.btnAddDispatch);
            rvDispatchLists = view.findViewById(R.id.rvDispatchLists);
            llNoData = view.findViewById(R.id.llNoData);

            btnAddReception.setOnClickListener(v -> {

                ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(requireContext(), R.anim.fade_fast_in, R.anim.fade_fast_out);
                Intent intent = new Intent(requireActivity(), DispatchActivity.class);
                intent.putExtra("isEdit", false);
                expenseResultLauncher.launch(intent, options);
            });

            fetchData();
        } catch (Exception e) {
            AppLogger.e(getClass(), "onCreateView", e);
        }
        return view;
    }

    private void fetchData() {
        try {
            rvDispatchLists.setVisibility(View.GONE);
            llNoData.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            AppLogger.e(getClass(), "fetchData", e);
        }
    }

    // ✅ Register Activity Result Launcher
    private final ActivityResultLauncher<Intent> expenseResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->
                    Toast.makeText(requireActivity(), "Yes", Toast.LENGTH_SHORT).show());
}