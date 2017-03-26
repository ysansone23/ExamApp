package com.uade.tesis.evaluated;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.uade.tesis.R;
import com.uade.tesis.evaluated.utils.EvaluatedErrorView;
import com.uade.tesis.evaluated.utils.EvaluatedWebViewClient;

public class ExamWebViewActivity extends AppCompatActivity {

    private static final String URL = "url";
    private String url;
    private ProgressBar progressBar;
    private TextView textView;
    private WebView webView;
    private ViewGroup errorViewContainer;

    private static final int TIMER_DURATION_HOURS = 4;
    private static final int MINUTES_LEFT = 10;

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
        webView = (WebView) findViewById(R.id.evaluado_form_web_view);

        progressBar = (ProgressBar) findViewById(R.id.evaluado_web_view_progress_bar);
        textView = (TextView) findViewById(R.id.evaluado_progress_bar_text);
        progressBar.setVisibility(View.VISIBLE);

        setUpWebView(webView);

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setUpWebView(final WebView webView) {
        if (getIntent().hasExtra(URL)) {
            url = getIntent().getExtras().getString(URL);
        }

        if (webView != null) {
            webView.setWebViewClient(new EvaluatedWebViewClient(
                new EvaluatedWebViewClient.WebViewActions() {
                    @Override
                    public void onPageStarted() {
                        progressBar.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);
                }

                    @Override
                    public void onReceivedError(boolean isNetworkingError) {
                        showErrorScreen(isNetworkingError);
                    }

                    @Override
                    public void onPageFinished() {
                        startTimer(TIMER_DURATION_HOURS * 3600 /*seconds*/ * 1000 /*ms*/,
                                1000 /*We have to set a 1000ms interval so that it changes after one minute*/);
                    }
                }));

            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUserAgentString("Android");
            webView.loadUrl(url);
        }
    }

    private void showErrorScreen(final boolean isNetworkingError) {
        progressBar.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);

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

    //Timer
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        timerText = menu.findItem(R.id.counter);

        final String hours = String.valueOf(TIMER_DURATION_HOURS);
        StringBuilder builder = new StringBuilder();
        if (hours.length() < 2) {
            builder.append("0");
        }
        builder.append(hours).append(":00:00");

        timerText.setTitle(builder.toString());
        return true;
    }

    private void startTimer(int duration, int interval) {
        new CountDownTimer(duration, interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secondsLeft = (int) Math.round((millisUntilFinished / (double) 1000));
                timerText.setTitle(secondsToString(secondsLeft));

                //When there are 10 minutes left we will show a toast
                if (millisUntilFinished == (MINUTES_LEFT * 60)) {
                    Toast.makeText(getApplicationContext(), R.string.ten_minutes_left, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), R.string.end_of_exam, Toast.LENGTH_LONG).show();
                timerText.setTitle("00:00:00");
                //TODO agregar manejo del final del timer
            }
        }.start();
    }

    private String secondsToString(int improperSeconds) {
        Time secConverter = new Time();

        secConverter.hour = 0;
        secConverter.minute = 0;
        secConverter.second = 0;

        secConverter.second = improperSeconds;
        secConverter.normalize(true);

        String hours = String.valueOf(secConverter.hour);
        String minutes = String.valueOf(secConverter.minute);
        String seconds = String.valueOf(secConverter.second);

        if (seconds.length() < 2) {
            seconds = "0" + seconds;
        }
        if (minutes.length() < 2) {
            minutes = "0" + minutes;
        }
        if (hours.length() < 2) {
            hours = "0" + hours;
        }

        return hours + ":" + minutes + ":" + seconds;
    }


}