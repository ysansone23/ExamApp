package com.uade.tesis.evaluated;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.uade.tesis.R;
import com.uade.tesis.evaluated.model.DialogEvent;
import com.uade.tesis.evaluated.utils.EvaluatedWelcomeDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class DecoderActivity extends Activity implements QRCodeReaderView.OnQRCodeReadListener {

    public static final long AUTOFOCUS_INTERVAL_IN_MS = 1000L;
    private QRCodeReaderView qrCodeReaderView;

    public static Intent getIntent(final Context context) {
        return new Intent(context, DecoderActivity.class);
    }

    @Subscribe
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decoder);

        qrCodeReaderView = (QRCodeReaderView) findViewById(R.id.qr_view);
        qrCodeReaderView.setOnQRCodeReadListener(this);

        // Use this function to enable/disable decoding
        qrCodeReaderView.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(AUTOFOCUS_INTERVAL_IN_MS);

        // Use this function to enable/disable Torch
        qrCodeReaderView.setTorchEnabled(true);

        // Use this function to set front camera preview
        qrCodeReaderView.setFrontCamera();

        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();

        setUpWelcomeDialog();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    // Called when a QR is decoded
    // "text" : the text encoded in QR
    // "points" : points where QR control points are placed in View
    @Override
    public void onQRCodeRead(final String text, final PointF[] points) {
        startActivity(ExamWebViewActivity.getIntent(this, text));
    }

    @Override
    protected void onResume() {
        super.onResume();
        qrCodeReaderView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        qrCodeReaderView.stopCamera();
    }

    /* Welcome Dialog */
    private void setUpWelcomeDialog() {
        qrCodeReaderView.setQRDecodingEnabled(false);

        final EvaluatedWelcomeDialog welcomeDialog = new EvaluatedWelcomeDialog(this);
        welcomeDialog.show();
    }

    /* Events */
    /**
     * Event handling method used to manage the dismiss of the {@link EvaluatedWelcomeDialog}
     */
    @Subscribe
    @SuppressWarnings("unused")
    public void onEvent(final DialogEvent welcomeDialogEvent) {
        qrCodeReaderView.setQRDecodingEnabled(true);
    }
}
