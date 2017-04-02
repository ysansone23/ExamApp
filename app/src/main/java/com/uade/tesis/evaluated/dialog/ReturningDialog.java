package com.uade.tesis.evaluated.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;

import com.uade.tesis.R;
import com.uade.tesis.common.utils.ThesisDialog;
import com.uade.tesis.evaluated.model.DialogEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Dialog used to show information when returning from another app
 */
public class ReturningDialog extends ThesisDialog {

    public ReturningDialog(@NonNull Context context) {
        this(context, 0);
    }

    public ReturningDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        setUpView();
    }

    protected ReturningDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        setUpView();
    }

    private void setUpView() {
        final Resources resources = getContext().getResources();

        setTitleText(resources.getString(R.string.returning_title));
        setSubtitleText(resources.getString(R.string.returning_subtitle));
        setUpButton(resources.getString(R.string.action_accept), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        onDismiss(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                /* ExamWebViewActivity should handle this event */
                EventBus.getDefault().post(new DialogEvent());
            }
        });
    }
}
