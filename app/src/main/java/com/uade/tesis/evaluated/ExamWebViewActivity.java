package com.uade.tesis.evaluated;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.uade.tesis.R;
import com.uade.tesis.evaluated.dialog.ReturningDialog;
import com.uade.tesis.evaluated.model.DialogEvent;
import com.uade.tesis.evaluated.utils.EvaluatedErrorView;
import com.uade.tesis.evaluated.utils.EvaluatedTimer;
import com.uade.tesis.evaluated.utils.EvaluatedWebViewClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ExamWebViewActivity extends AppCompatActivity {

    private static final String URL = "url";
    private static final int TIMER_DURATION_HOURS = 4;

    private String url;
    private ProgressBar progressBar;
    private TextView progressBarTitle;
    private WebView webView;
    private ViewGroup errorViewContainer;

    private MenuItem timerText;

    public static Intent getIntent(@NonNull final Context context, String text) {
        Intent intent = new Intent(context, ExamWebViewActivity.class);
        intent.putExtra(URL, text);
        return intent;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluado);

        if (savedInstanceState != null) {
            //We are coming back from another app.
            //TODO buscar formas de chequear si vengo de otra app.
            //Deberiamos poder reconocer SOLO cuando abrimos otra app y por cuanto tiempo. En caso de ser menos de 5 segundos podriamos obviarlo.
            //El tema es que no encuentro nada sobre esto. Teoricamente se puede hacer.
            final ReturningDialog returningDialog = new ReturningDialog(this);
            returningDialog.show();
        }

        initializeViews();
        setUpWebView();
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

    /* WebView, ProgressBar, etc */
    private void initializeViews() {
        webView = (WebView) findViewById(R.id.evaluado_form_web_view);

        progressBar = (ProgressBar) findViewById(R.id.evaluado_web_view_progress_bar);
        progressBarTitle = (TextView) findViewById(R.id.evaluado_progress_bar_text);
        progressBar.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setUpWebView() {
        if (getIntent().hasExtra(URL)) {
            url = getIntent().getExtras().getString(URL);
        }

        if (webView != null) {
            webView.setWebViewClient(new EvaluatedWebViewClient(
                new EvaluatedWebViewClient.WebViewActions() {
                    @Override
                    public void onPageStarted() {
                        progressBar.setVisibility(View.VISIBLE);
                        progressBarTitle.setVisibility(View.VISIBLE);
                }

                    @Override
                    public void onReceivedError(boolean isNetworkingError) {
                        showErrorScreen(isNetworkingError);
                    }

                    @Override
                    public void onPageFinished() {
//                        final int duration = TIMER_DURATION_HOURS * 3600 /*seconds*/ * 1000 /*ms*/;
                        final int duration = 5000;
                        /*We have to set a 1000ms interval so that it changes after one minute*/
                        final int interval = 1000;
                        new EvaluatedTimer(duration, interval, timerText, getApplicationContext()).start();
                    }
                }));

            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUserAgentString("Android");
            webView.loadUrl(url);

            webView.setPadding(0,0,0,0);
        }
    }

    /* Error Screen */
    private void showErrorScreen(final boolean isNetworkingError) {
        progressBar.setVisibility(View.GONE);
        progressBarTitle.setVisibility(View.GONE);

        EvaluatedErrorView.ErrorViewActions errorViewActions = new EvaluatedErrorView.ErrorViewActions() {
            @Override
            public void retry() {
                webView.loadUrl(url);
                errorViewContainer.setVisibility(View.GONE);
                webView.setVisibility(View.GONE);
            }

            @Override
            public void scanAnother() {
                startActivity(DecoderActivity.getIntent(ExamWebViewActivity.this));
                finish();
            }
        };

        final EvaluatedErrorView errorView = new EvaluatedErrorView(this);
        errorView.setUpView(errorViewActions, isNetworkingError);

        errorViewContainer = (ViewGroup) findViewById(R.id.evaluated_error_view_container);
        errorViewContainer.setVisibility(View.VISIBLE);
        errorViewContainer.addView(errorView);
    }

    @Override
    public void onBackPressed() {
        //TODO comment super to stop back action
        super.onBackPressed();
    }

    /* Timer */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        timerText = menu.findItem(R.id.menu_timer);

        final String hours = String.valueOf(TIMER_DURATION_HOURS);
        StringBuilder builder = new StringBuilder();
        if (hours.length() < 2) {
            builder.append("0");
        }
        builder.append(hours).append(":00:00");

        timerText.setTitle(builder.toString());
        return true;
    }

    /* Events */
    @Subscribe
    @SuppressWarnings("unused")
    public void onEvent(final DialogEvent returningEvent) {
        /* Here whe should send data to the professor about the student who swap between apps */
    }

}