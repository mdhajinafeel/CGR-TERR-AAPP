package com.cgr.codrinterraerp.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.cgr.codrinterraerp.ui.fragments.DispatchFragment;
import com.cgr.codrinterraerp.ui.fragments.ReceptionFragment;

public class TabPagerAdapter extends FragmentStateAdapter {

    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new ReceptionFragment();
        } else {
            return new DispatchFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}