package com.uade.tesis.evaluator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.uade.tesis.R;
import com.uade.tesis.evaluated.utils.EvaluatedErrorView;
import com.uade.tesis.evaluated.utils.EvaluatedWebViewClient;

public class ResponseActivity extends AppCompatActivity {

    private static final String TITLE = "title";
    private static final String URL = "url";
    private String name = null;
    private WebView webView;
    private View webViewContainer;
    private TextView progressBarText;
    private ProgressBar progressBar;
    private ViewGroup errorContainer;

    public static Intent getIntent(@NonNull final Context context, final String title, final String url) {
        final Intent intent = new Intent(context, ResponseActivity.class);
        intent.putExtra(URL, url);
        intent.putExtra(TITLE, title);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
        final ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(getIntent().getStringExtra(TITLE));
        }
        findViewById(R.id.note_container).setVisibility(View.GONE);
        webViewContainer = findViewById(R.id.evaluado_form_web_view);
        webView = (WebView) findViewById(R.id.response_web_view);
        progressBarText = (TextView) findViewById(R.id.evaluado_progress_bar_text);
        progressBar = (ProgressBar) findViewById(R.id.evaluado_web_view_progress_bar);
        errorContainer = (ViewGroup) findViewById(R.id.evaluated_error_view_container);

        setUpView();
    }

    private void setUpView() {
        webView.setWebViewClient(new EvaluatedWebViewClient(getClientActions()));
        webView.loadUrl(getIntent().getStringExtra(URL));
    }

    public EvaluatedWebViewClient.WebViewActions getClientActions() {
        return new EvaluatedWebViewClient.WebViewActions() {
            @Override
            public void onPageStarted() {
                progressBarText.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                webViewContainer.setVisibility(View.GONE);
                errorContainer.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(final boolean isNetworkingError) {
                progressBarText.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                webViewContainer.setVisibility(View.GONE);
                errorContainer.setVisibility(View.VISIBLE);
                showError(isNetworkingError);
            }

            @Override
            public void onPageFinished() {
                progressBarText.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                webViewContainer.setVisibility(View.VISIBLE);
                errorContainer.setVisibility(View.GONE);
                name = "Nueva materia";
            }

            @Override
            public void sendAnswer() {
                //nothing
            }
        };
    }

    private void showError(final boolean isNetworkingError) {
        final EvaluatedErrorView.ErrorViewActions errorViewActions = new EvaluatedErrorView.ErrorViewActions() {
            @Override
            public void retry() {
                webView.loadUrl(getIntent().getStringExtra(URL));
                errorContainer.setVisibility(View.GONE);
                webViewContainer.setVisibility(View.GONE);
            }

            @Override
            public void scanAnother() {
                //no-op
            }
        };

        final EvaluatedErrorView errorView = new EvaluatedErrorView(this);
        errorView.setUpView(errorViewActions, isNetworkingError);

        errorContainer.addView(errorView);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            final Intent intent = new Intent();
            if (!TextUtils.isEmpty(name)) {
                intent.putExtra("name", name);
            }
            setResult(Activity.RESULT_OK, intent);
            super.onBackPressed();
        }
    }
}