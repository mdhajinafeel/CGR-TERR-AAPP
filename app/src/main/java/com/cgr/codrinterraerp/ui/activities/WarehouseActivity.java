package com.cgr.codrinterraerp.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager2.widget.ViewPager2;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.ui.adapters.TabPagerAdapter;
import com.cgr.codrinterraerp.ui.common.BaseActivity;
import com.cgr.codrinterraerp.utils.AppLogger;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class WarehouseActivity extends BaseActivity {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse);
        statusBarSetting();
        hideKeyboard(this);
        initComponents();
    }

    private void initComponents() {
        try {
            AppCompatImageView imgBack = findViewById(R.id.imgBack);
            AppCompatTextView txtTitle = findViewById(R.id.txtTitle);
            tabLayout = findViewById(R.id.tabLayout);
            ViewPager2 viewPager = findViewById(R.id.viewPager);

            txtTitle.setText(getString(R.string.warehouse));
            imgBack.setOnClickListener(v -> finish());

            // Set Adapter
            viewPager.setAdapter(new TabPagerAdapter(this));

            // Fix initial icon state
            tabLayout.post(() -> {
                TabLayout.Tab firstTab = tabLayout.getTabAt(0);
                if (firstTab != null) {
                    firstTab.select(); // force trigger
                }
            });

            // Attach TabLayout with ViewPager2
            new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
                if (position == 0) {
                    tab.setText(getString(R.string.reception));
                    tab.setIcon(R.drawable.ic_warehouse); // default selected
                } else {
                    tab.setText(getString(R.string.dispatch));
                    tab.setIcon(R.drawable.ic_dispatch_unselected);
                }
            }).attach();

            // 🔥 ICON SWITCH + ANIMATION
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                    if (tab.getPosition() == 0) {
                        tab.setIcon(R.drawable.ic_warehouse);
                    } else {
                        tab.setIcon(R.drawable.ic_dispatch);
                    }

                    // 🔥 Bounce Animation
                    View tabView = ((ViewGroup) tabLayout.getChildAt(0))
                            .getChildAt(tab.getPosition());

                    tabView.animate()
                            .scaleX(1.1f)
                            .scaleY(1.1f)
                            .setDuration(150)
                            .withEndAction(() -> tabView.animate()
                                    .scaleX(1f)
                                    .scaleY(1f)
                                    .setDuration(150)
                                    .start())
                            .start();
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    if (tab.getPosition() == 0) {
                        tab.setIcon(R.drawable.ic_warehouse_unselected);
                    } else {
                        tab.setIcon(R.drawable.ic_dispatch_unselected);
                    }
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });

            // 🎬 ViewPager Animation
            viewPager.setPageTransformer((page, position) -> {

                float absPos = Math.abs(position);

                // Fade
                page.setAlpha(1 - absPos);

                // Zoom
                page.setScaleY(0.9f + (1 - absPos) * 0.1f);
                page.setScaleX(0.9f + (1 - absPos) * 0.1f);

                // Slide
                page.setTranslationX(-position * page.getWidth());
            });

            viewPager.setUserInputEnabled(true);
        } catch (Exception e) {
            AppLogger.e(getClass(), "Error in initComponents", e);
        }
    }
}