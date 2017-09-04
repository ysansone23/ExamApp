package com.uade.tesis.evaluated.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.uade.tesis.R;

public class EvaluatedErrorView extends FrameLayout {

    public EvaluatedErrorView(final Context context) {
        this(context, null);
    }

    public EvaluatedErrorView(final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EvaluatedErrorView(final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EvaluatedErrorView(final Context context, final AttributeSet attrs, final int defStyleAttr,
        final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        final LayoutParams params =
                new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);

        LayoutInflater.from(getContext()).inflate(R.layout.evaluated_error_view, this);
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));

        final int padding = (int) getContext().getResources().getDimension(R.dimen.pfi_24dp);
        setPadding(padding, 0, padding, 0);
    }

    public void setUpView(final ErrorViewActions actions, final boolean isNetworkingError) {
        final Button retry = (Button) findViewById(R.id.evaluated_error_view_retry);
        final Button scanAnother = (Button) findViewById(R.id.evaluated_error_view_scan_another);

        if (isNetworkingError) {
            retry.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {
                    actions.retry();
                }
            });
            retry.setVisibility(VISIBLE);
            scanAnother.setVisibility(GONE);
        } else {
            scanAnother.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {
                    actions.scanAnother();
                }
            });
            scanAnother.setVisibility(VISIBLE);
            retry.setVisibility(GONE);
        }
    }

    /**
     * Interface used to set the buttons actions
     */
    public interface ErrorViewActions {

        void retry();

        void scanAnother();
    }
}
