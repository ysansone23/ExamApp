package com.uade.tesis.evaluated.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uade.tesis.R;
import com.uade.tesis.common.utils.ThesisDialog;
import com.uade.tesis.evaluated.model.DialogEvent;

import org.greenrobot.eventbus.EventBus;

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
        final TextView title = (TextView) findViewById(R.id.thesis_dialog_title);
        final TextView subtitle = (TextView) findViewById(R.id.thesis_dialog_subtitle);
        final Button accept = (Button) findViewById(R.id.thesis_dialog_button);

        title.setText(getContext().getResources().getString(R.string.welcome_title));
        subtitle.setText(getContext().getResources().getString(R.string.welcome_body));
        accept.setText(getContext().getResources().getString(R.string.action_accept));

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                /* DecoderActivity should handle this event */
                EventBus.getDefault().post(new DialogEvent());
            }
        });
    }
}
