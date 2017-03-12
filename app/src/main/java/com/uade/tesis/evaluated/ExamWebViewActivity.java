package com.uade.tesis.evaluated;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.uade.tesis.R;

public class ExamWebViewActivity extends AppCompatActivity {

    private static final String TEXT = "text";
    private String url;
    private ProgressBar progressBar;
    private TextView textView;
    private WebView webView;

    public static Intent getIntent(@NonNull final Context context, String text) {
        Intent intent = new Intent(context, ExamWebViewActivity.class);
        intent.putExtra(TEXT, text);
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
        if (getIntent().hasExtra(TEXT)) {
            url = getIntent().getExtras().getString(TEXT);
        }
        if (webView != null) {
            webView.setWebViewClient(new EvaluadoWebViewClient());
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setUserAgentString("Android");
            webView.loadUrl(url);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private class EvaluadoWebViewClient extends WebViewClient {

        public EvaluadoWebViewClient() {
            super();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progressBar.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            animate(webView);
            webView.setVisibility(View.VISIBLE);
        }

        private void animate(final WebView view) {
            Animation anim = AnimationUtils.loadAnimation(getBaseContext(),
                    android.R.anim.slide_in_left);
            view.startAnimation(anim);
        }
    }
}