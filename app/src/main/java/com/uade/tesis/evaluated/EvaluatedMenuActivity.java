package com.uade.tesis.evaluated;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.uade.tesis.R;
import com.uade.tesis.evaluated.utils.EvaluatedWelcomeDialog;

public class EvaluatedMenuActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 123;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle("Evaluado");
        }
        setContentView(R.layout.evaluated_menu_activity);
    }

    public void scan(final View view) {
        final long permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permission == PackageManager.PERMISSION_DENIED) {
            final String[] permissions = { Manifest.permission.CAMERA };
            ActivityCompat.requestPermissions(EvaluatedMenuActivity.this, permissions, PERMISSION_REQUEST_CODE);

        } else {
            final Intent intent = DecoderActivity.getIntent(this);
            startActivity(intent);
        }
    }

    public void startModal(final View view) {
        final EvaluatedWelcomeDialog welcomeDialog = new EvaluatedWelcomeDialog(this);
        welcomeDialog.show();
    }

    public void showCongrats(final View view) {
        final Intent congrats = EvaluatedCongratsActivity.getIntent(this, "¡Tu examen fue enviado!",
            "Por consultas dirigite a tu profesor");
        startActivity(congrats);
    }

    public void startWebView(final View view) {
        final Intent webView = ExamWebViewActivity.getIntent(this,
            "https://docs.google.com/forms/d/1O2kEFlDqwm7OpgsDq6VLPZeoZ1i8F7q8zaHWuPAKG_w/viewform?edit_requested=true");
        startActivity(webView);
    }
}
