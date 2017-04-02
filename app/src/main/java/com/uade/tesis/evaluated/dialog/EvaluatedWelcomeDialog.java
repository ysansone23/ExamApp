package com.uade.tesis.evaluated.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;

import com.uade.tesis.R;
import com.uade.tesis.commons.utils.ThesisDialog;
import com.uade.tesis.evaluated.model.DialogEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Dialog used to show information when opening the camera to scan a qr code
 */
public class EvaluatedWelcomeDialog extends ThesisDialog {

    public EvaluatedWelcomeDialog(@NonNull Context context) {
        this(context, 0);
    }

    public EvaluatedWelcomeDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        setUp();
    }

    protected EvaluatedWelcomeDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        setUp();
    }

    private void setUp() {
        //Dismiss when tapping outside the dialog
        final OnDismissListener dismissListener = new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                /* DecoderActivity should handle this event */
                EventBus.getDefault().post(new DialogEvent());
            }
        };
        onDismiss(dismissListener);

        //Dismiss when tapping on the accept button
        final View.OnClickListener acceptButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        };
        setUpButton(getContext().getResources().getString(R.string.action_accept), acceptButtonListener);

        setTitleText(getContext().getResources().getString(R.string.welcome_title));
        setSubtitleText(getContext().getResources().getString(R.string.welcome_body));
    }
}
