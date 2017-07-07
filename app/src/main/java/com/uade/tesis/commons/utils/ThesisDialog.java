package com.uade.tesis.commons.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import com.uade.tesis.R;

public class ThesisDialog extends Dialog {

    public ThesisDialog(@NonNull final Context context) {
        this(context, 0);
    }

    public ThesisDialog(@NonNull final Context context, @StyleRes final int themeResId) {
        super(context, themeResId);
        initView();
    }

    protected ThesisDialog(@NonNull final Context context, final boolean cancelable,
        @Nullable final OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    private void initView() {
        setContentView(R.layout.thesis_dialog);
    }


}
