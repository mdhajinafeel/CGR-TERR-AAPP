package com.cgr.codrinterraerp.ui.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.db.entities.ApiLogs;
import com.cgr.codrinterraerp.ui.adapters.RecyclerViewAdapter;
import com.cgr.codrinterraerp.ui.adapters.ViewHolder;
import com.cgr.codrinterraerp.ui.common.BaseActivity;
import com.cgr.codrinterraerp.utils.AppLogger;
import com.cgr.codrinterraerp.utils.CommonUtils;
import com.cgr.codrinterraerp.viewmodel.AppStatusViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AppStatusActivity extends BaseActivity {

    private LinearLayout llNoData;
    private RecyclerView rvAppStatusList;
    private RecyclerViewAdapter<ApiLogs> apiLogsRecyclerViewAdapter;
    private int expandedPosition = -1;
    private Drawable internetDrawable, crashDrawable, errorDrawable, databaseDrawable;
    private int colorInternet, colorCrash, colorError, colorDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_status);
        statusBarSetting();
        hideKeyboard(this);
        initComponents();
    }

    private void initComponents() {
        try {
            AppCompatImageView imgBack = findViewById(R.id.imgBack);
            AppCompatTextView txtTitle = findViewById(R.id.txtTitle);
            rvAppStatusList = findViewById(R.id.rvAppStatusList);
            llNoData = findViewById(R.id.llNoData);

            txtTitle.setText(getString(R.string.app_status));
            imgBack.setOnClickListener(view -> finish());

            AppStatusViewModel appStatusViewModel = new ViewModelProvider(this).get(AppStatusViewModel.class);

            rvAppStatusList.setLayoutManager(new LinearLayoutManager(this));
            initializeAdapter();

            appStatusViewModel.getApiLogsList().observe(this, this::bindAppStatusData);
            appStatusViewModel.load();

            internetDrawable = ContextCompat.getDrawable(this, R.drawable.ic_internet);
            crashDrawable = ContextCompat.getDrawable(this, R.drawable.ic_crash);
            errorDrawable = ContextCompat.getDrawable(this, R.drawable.ic_error);
            databaseDrawable = ContextCompat.getDrawable(this, R.drawable.ic_database_error);

            colorInternet = ContextCompat.getColor(this, R.color.colorInternet);
            colorCrash = ContextCompat.getColor(this, R.color.colorSindoorRed);
            colorError = ContextCompat.getColor(this, R.color.colorError);
            colorDb = ContextCompat.getColor(this, R.color.colorDBError);

        } catch (Exception e) {
            AppLogger.e(getClass(), "initComponents", e);
        }
    }

    private void initializeAdapter() {

        apiLogsRecyclerViewAdapter = new RecyclerViewAdapter<>(getApplicationContext(), new ArrayList<>(), R.layout.row_item_app_status) {
            @Override
            public void onPostBindViewHolder(ViewHolder holder, ApiLogs apiLog) {
                if (apiLog != null) {

                    View vStatusBar = holder.getView(R.id.vStatusBar);
                    LinearLayout expandableContainer = (LinearLayout) holder.getView(R.id.expandableContainer);

                    if (apiLog.success && apiLog.type.equalsIgnoreCase("api")) {
                        holder.setViewText(R.id.tvTitle, apiLog.endpoint.replace("/", "").toUpperCase());
                    } else {
                        holder.setViewText(R.id.tvTitle, apiLog.exceptionType);
                    }

                    if (!TextUtils.isEmpty(apiLog.tag) && !TextUtils.isEmpty(apiLog.methodName)) {
                        holder.setViewText(R.id.tvActivity, apiLog.tag + "." + apiLog.methodName);
                        holder.setViewVisibility(R.id.tvActivity, View.VISIBLE);
                    } else if (!TextUtils.isEmpty(apiLog.tag)) {
                        holder.setViewText(R.id.tvActivity, apiLog.tag);
                        holder.setViewVisibility(R.id.tvActivity, View.VISIBLE);
                    } else if (!TextUtils.isEmpty(apiLog.methodName)) {
                        holder.setViewText(R.id.tvActivity, apiLog.methodName);
                        holder.setViewVisibility(R.id.tvActivity, View.VISIBLE);
                    } else {
                        holder.setViewVisibility(R.id.tvActivity, View.GONE);
                    }

                    holder.setViewText(R.id.tvLogType, apiLog.type);

                    String type = apiLog.type.toUpperCase();

                    switch (type) {
                        case "API":
                        case "NETWORK":
                            holder.setViewImageDrawable(R.id.tvIcon, internetDrawable);
                            vStatusBar.setBackgroundColor(colorInternet);
                            holder.setViewTextColor(R.id.tvLogType, colorInternet);
                            break;
                        case "CRASH":
                            holder.setViewImageDrawable(R.id.tvIcon, crashDrawable);
                            vStatusBar.setBackgroundColor(colorCrash);
                            holder.setViewTextColor(R.id.tvLogType, colorCrash);
                            break;
                        case "ERROR":
                            holder.setViewImageDrawable(R.id.tvIcon, errorDrawable);
                            vStatusBar.setBackgroundColor(colorError);
                            holder.setViewTextColor(R.id.tvLogType, colorError);
                            break;
                        case "DATABASE":
                            holder.setViewImageDrawable(R.id.tvIcon, databaseDrawable);
                            vStatusBar.setBackgroundColor(colorDb);
                            holder.setViewTextColor(R.id.tvLogType, colorDb);
                            break;
                    }

                    holder.setViewText(R.id.tvTime, CommonUtils.convertTimeStampToDate(apiLog.createdAt, "dd/MM/yyyy hh:mm:ss a", getApplicationContext()));

                    if (apiLog.success && apiLog.type.equalsIgnoreCase("api")) {

                        holder.setViewVisibility(R.id.ivExpandLogs, View.INVISIBLE);
                        expandableContainer.setVisibility(View.GONE);
                        holder.setViewText(R.id.tvStackTrace, "");
                        holder.setViewText(R.id.tvErrorMessage, "");
                    } else {

                        holder.setViewVisibility(R.id.ivExpandLogs, View.VISIBLE);
                        boolean isExpanded = holder.getAbsoluteAdapterPosition() == expandedPosition;
                        expandableContainer.setVisibility(isExpanded ? View.VISIBLE : View.GONE);

                        AppCompatTextView tvStackTrace = (AppCompatTextView) holder.getView(R.id.tvStackTrace);
                        AppCompatTextView tvErrorMessage = (AppCompatTextView) holder.getView(R.id.tvErrorMessage);
                        View ivExpand = holder.getView(R.id.ivExpandLogs);

                        String formattedResponse = !TextUtils.isEmpty(apiLog.responseBody) ? formatLogText(apiLog.responseBody) : "";
                        String formattedError = !TextUtils.isEmpty(apiLog.errorMessage) ? formatLogText(apiLog.errorMessage) : "";

                        Runnable[] toggleRunnable = new Runnable[1];
                        toggleRunnable[0] = () -> {

                            boolean isShowingFull = tvStackTrace.getText().toString().contains(getString(R.string.show_less));

                            if (isShowingFull) {
                                tvStackTrace.setText(getTrimmedText(formattedResponse, 5, toggleRunnable[0]));
                            } else {
                                tvStackTrace.setText(getTrimmedText(formattedResponse, -1, toggleRunnable[0]));
                            }

                            tvStackTrace.setMovementMethod(LinkMovementMethod.getInstance());
                        };

                        tvStackTrace.setText(getTrimmedText(formattedResponse, 5, toggleRunnable[0]));

                        tvStackTrace.setMovementMethod(LinkMovementMethod.getInstance());

                        Runnable[] toggleRunnable1 = new Runnable[1];
                        toggleRunnable1[0] = () -> {

                            boolean isShowingFullError = tvErrorMessage.getText().toString().contains(getString(R.string.show_less));

                            if (isShowingFullError) {
                                tvErrorMessage.setText(getTrimmedText(formattedError, 3, toggleRunnable1[0]));
                            } else {
                                tvErrorMessage.setText(getTrimmedText(formattedError, -1, toggleRunnable1[0]));
                            }

                            tvErrorMessage.setMovementMethod(LinkMovementMethod.getInstance());
                        };

                        tvErrorMessage.setText(getTrimmedText(formattedError, 3, toggleRunnable1[0]));

                        tvErrorMessage.setMovementMethod(LinkMovementMethod.getInstance());

                        ivExpand.animate().rotation(isExpanded ? 180f : 0f).setDuration(200).start();
                        ivExpand.setOnClickListener(view -> {

                            int currentPosition = holder.getAbsoluteAdapterPosition();
                            int previousExpandedPosition = expandedPosition;

                            if (expandedPosition == currentPosition) {
                                expandedPosition = -1;
                                view.setRotation(isExpanded ? 180f : 0f);
                            } else {
                                expandedPosition = currentPosition;
                                view.setRotation(isExpanded ? 180f : 0f);
                            }

                            if (previousExpandedPosition != -1) {
                                apiLogsRecyclerViewAdapter.notifyItemChanged(previousExpandedPosition);
                            }

                            apiLogsRecyclerViewAdapter.notifyItemChanged(currentPosition);
                        });
                    }
                }
            }
        };

        rvAppStatusList.setAdapter(apiLogsRecyclerViewAdapter);
        rvAppStatusList.setHasFixedSize(true);

        RecyclerView.ItemAnimator animator = rvAppStatusList.getItemAnimator();

        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }
    }

    private void bindAppStatusData(List<ApiLogs> list) {
        try {
            if (list != null && !list.isEmpty()) {
                apiLogsRecyclerViewAdapter.setItems(list);
                rvAppStatusList.setVisibility(View.VISIBLE);
                llNoData.setVisibility(View.GONE);
            } else {
                llNoData.setVisibility(View.VISIBLE);
                rvAppStatusList.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            AppLogger.e(getClass(), "bindAppStatusData", e);
        }
    }

    private SpannableString getTrimmedText(String text, int maxLines, Runnable onToggleClick) {

        if (TextUtils.isEmpty(text)) {
            return new SpannableString("");
        }

        String toggleText;

        // FULL TEXT MODE
        if (maxLines == -1) {
            toggleText = getString(R.string.show_less);
            return getSpannableString(text, onToggleClick, toggleText);
        }

        // COLLAPSED MODE
        String[] lines = text.split("\n");

        if (lines.length <= maxLines) {
            return new SpannableString(text);
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < maxLines; i++) {
            builder.append(lines[i]).append("");
        }

        toggleText = getString(R.string.show_more);
        builder.append("\n").append(toggleText);
        return getSpannableString(onToggleClick, builder, toggleText);
    }

    @NonNull
    private static SpannableString getSpannableString(String text, Runnable onToggleClick, String toggleText) {
        String fullText =
                text.replace("\n", "") + "\n" + toggleText;

        SpannableString spannableString =
                new SpannableString(fullText);

        int start = fullText.indexOf(toggleText);
        int end = start + toggleText.length();

        spannableString.setSpan(
                new android.text.style.ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        onToggleClick.run();
                    }
                },
                start,
                end,
                android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        return spannableString;
    }

    @NonNull
    private static SpannableString getSpannableString(Runnable onToggleClick, StringBuilder builder, String toggleText) {
        SpannableString spannableString =
                new SpannableString(builder.toString());

        int start = builder.toString().indexOf(toggleText);
        int end = start + toggleText.length();

        spannableString.setSpan(
                new android.text.style.ClickableSpan() {
                    @Override
                    public void onClick(@NonNull View widget) {
                        onToggleClick.run();
                    }
                },
                start,
                end,
                android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        return spannableString;
    }

    private String formatLogText(String text) {

        if (TextUtils.isEmpty(text)) {
            return "";
        }

        return text
                .replace("{", "{\n")
                .replace("}", "\n}")
                .replace("[", "[\n")
                .replace("]", "\n]")
                .replace(",", ",\n");
    }
}