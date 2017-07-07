package com.uade.tesis.evaluated.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uade.tesis.R;
import com.uade.tesis.commons.utils.ThesisDialog;
import com.uade.tesis.evaluated.model.DialogEvent;

import org.greenrobot.eventbus.EventBus;

public class EvaluatedWelcomeDialog extends ThesisDialog {

    public EvaluatedWelcomeDialog(@NonNull final Context context) {
        this(context, 0);
    }

    public EvaluatedWelcomeDialog(@NonNull final Context context, @StyleRes final int themeResId) {
        super(context, themeResId);
        setUp();
    }

    protected EvaluatedWelcomeDialog(@NonNull final Context context, final boolean cancelable,
        @Nullable final OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        setUp();
    }

    private void setUp() {
        final TextView title = (TextView) findViewById(R.id.thesis_dialog_title);
        final TextView subtitle = (TextView) findViewById(R.id.thesis_dialog_subtitle);
        final Button accept = (Button) findViewById(R.id.thesis_dialog_button);

        title.setText(getContext().getResources().getString(R.string.welcome_title));
        subtitle.setText(getContext().getResources().getString(R.string.welcome_body));
        accept.setText(getContext().getResources().getString(R.string.action_accept));

        //Dismiss when tapping outside the dialog
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                /* DecoderActivity should handle this event */
                EventBus.getDefault().post(new DialogEvent());
            }
        });

        //Dismiss when tapping on the accept button
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
