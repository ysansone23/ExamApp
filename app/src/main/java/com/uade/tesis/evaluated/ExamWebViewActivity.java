package com.uade.tesis.evaluated;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
        super.onBackPressed();
    }

}