package com.uade.tesis.commons.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uade.tesis.R;

public class ThesisDialog extends Dialog {

    private TextView title;
    private TextView subtitle;
    private Button button;

    public ThesisDialog(@NonNull Context context) {
        this(context, 0);
    }

    public ThesisDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        initView();
    }

    protected ThesisDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    private void initView() {
        setContentView(R.layout.thesis_dialog);

        title = (TextView) findViewById(R.id.thesis_dialog_title);
        subtitle = (TextView) findViewById(R.id.thesis_dialog_subtitle);
        button = (Button) findViewById(R.id.thesis_dialog_button);
    }

    public void setTitleText(final String titleText) {
        title.setText(titleText);
    }

    public void setSubtitleText(final String subtitleText) {
        subtitle.setText(subtitleText);
    }

    public void setUpButton(final String label, final View.OnClickListener listener) {
        button.setText(label);
        button.setOnClickListener(listener);
    }

    public void onDismiss(final OnDismissListener listener) {
        setOnDismissListener(listener);
    }
}
