package com.uade.tesis.commons;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.uade.tesis.R;
import com.uade.tesis.evaluated.DecoderActivity;
import com.uade.tesis.evaluated.EvaluatedCongratsActivity;
import com.uade.tesis.evaluated.utils.EvaluatedWelcomeDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startEvaluated(final View view) {
        final Intent intent = DecoderActivity.getIntent(this);
        startActivity(intent);
    }

    public void startModal(final View view) {
        final EvaluatedWelcomeDialog welcomeDialog = new EvaluatedWelcomeDialog(this);
        welcomeDialog.show();
    }

    public void showCongrats(final View view) {
        final Intent congrats = EvaluatedCongratsActivity.getIntent(this);
        startActivity(congrats);
    }
}

