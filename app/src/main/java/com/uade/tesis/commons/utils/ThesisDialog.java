package com.uade.tesis.commons.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.uade.tesis.R;
import com.uade.tesis.evaluated.model.DialogEvent;
import org.greenrobot.eventbus.EventBus;

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

    public void setUpView(final String titleText, final String subtitleText, final boolean shouldFinish) {
        setUpView(titleText, subtitleText, null, shouldFinish);
    }

    public void setUpView(final String titleText, final String subtitleText,
        @Nullable View.OnClickListener listener, final boolean shouldFinish) {

        final TextView title = (TextView) findViewById(R.id.thesis_dialog_title);
        final TextView subtitle = (TextView) findViewById(R.id.thesis_dialog_subtitle);
        final Button accept = (Button) findViewById(R.id.thesis_dialog_button);

        title.setText(titleText);
        subtitle.setText(subtitleText);
        accept.setText("Aceptar");

        if (listener == null) {
            listener = new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    dismiss();
                }
            };
        }
        accept.setOnClickListener(listener);

        if (shouldFinish) {
            setOnDismissListener(new OnDismissListener() {
                @Override
                public void onDismiss(final DialogInterface dialog) {
                    EventBus.getDefault().post(new DialogEvent());
                }
            });
        }

    }

    public void setUpWelcomeDialog() {
        final TextView title = (TextView) findViewById(R.id.thesis_dialog_title);
        final TextView subtitle = (TextView) findViewById(R.id.thesis_dialog_subtitle);
        final Button accept = (Button) findViewById(R.id.thesis_dialog_button);

        final Resources resources = getContext().getResources();
        title.setText(resources.getString(R.string.welcome_title));
        subtitle.setText(resources.getString(R.string.welcome_body));
        accept.setText(resources.getString(R.string.action_accept));

        //Dismiss when tapping outside the dialog
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(final DialogInterface dialog) {
                /* DecoderActivity should handle this event */
                EventBus.getDefault().post(new DialogEvent());
            }
        });

        //Dismiss when tapping on the accept button
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                dismiss();
            }
        });
    }
}
