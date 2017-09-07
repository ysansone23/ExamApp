package com.uade.tesis.commons;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.uade.tesis.R;
import com.uade.tesis.evaluated.DecoderActivity;
import com.uade.tesis.evaluated.EvaluatedCongratsActivity;
import com.uade.tesis.evaluated.ExamWebViewActivity;
import com.uade.tesis.evaluated.utils.EvaluatedWelcomeDialog;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 123;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startEvaluated(final View view) {
        final long permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (permission == PackageManager.PERMISSION_DENIED) {
            final String[] permissions = { Manifest.permission.CAMERA };
            ActivityCompat.requestPermissions(MainActivity.this, permissions, PERMISSION_REQUEST_CODE);

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
        final Intent congrats = EvaluatedCongratsActivity.getIntent(this);
        startActivity(congrats);
    }

    public void startWebView(final View view) {
        final Intent webView = ExamWebViewActivity.getIntent(this, "http://google.com");
        startActivity(webView);
    }
}

