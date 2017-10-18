package com.uade.tesis.evaluator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.uade.tesis.R;
import com.uade.tesis.evaluated.utils.EvaluatedErrorView;
import com.uade.tesis.evaluated.utils.EvaluatedWebViewClient;

public class NewExamActivity extends AppCompatActivity {

    private static final int NEW_ASSIGNMENT = 1;
    private static final String TITLE = "title";
    private static final String URL = "url";

    private String name = null;
    private WebView webView;
    private TextView progressBarText;
    private ProgressBar progressBar;
    private ViewGroup errorContainer;

    public static Intent getIntent(@NonNull final Context context, final String title, final String url) {
        final Intent intent = new Intent(context, NewExamActivity.class);
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

        webView = (WebView) findViewById(R.id.response_web_view);
        progressBarText = (TextView) findViewById(R.id.evaluado_progress_bar_text);
        progressBar = (ProgressBar) findViewById(R.id.evaluado_web_view_progress_bar);
        errorContainer = (ViewGroup) findViewById(R.id.evaluated_error_view_container);
        findViewById(R.id.grade_container).setVisibility(View.GONE);

        setUpView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setUpView() {
        webView.setWebViewClient(new EvaluatedWebViewClient(getClientActions()));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(getIntent().getStringExtra(URL));

    }

    public EvaluatedWebViewClient.WebViewActions getClientActions() {
        return new EvaluatedWebViewClient.WebViewActions() {
            @Override
            public void onPageStarted() {
                progressBarText.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                errorContainer.setVisibility(View.GONE);
                webView.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(final boolean isNetworkingError) {
                webView.setVisibility(View.GONE);
                progressBarText.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                errorContainer.setVisibility(View.VISIBLE);
                showError(isNetworkingError);
            }

            @Override
            public void onPageFinished() {
                progressBarText.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                errorContainer.setVisibility(View.GONE);
                name = "Teleinf. y redes II - final";
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
                webView.setVisibility(View.VISIBLE);
                errorContainer.setVisibility(View.GONE);
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
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.new_exam_menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        super.onPrepareOptionsMenu(menu);
        final MenuItem next = menu.findItem(R.id.new_exam_next);
        next.setIcon(getResources().getDrawable(R.drawable.arrow_forward, getTheme()));
        next.setVisible(true);
        next.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final MenuItem item) {
                final Intent intent = new Intent(NewExamActivity.this, SetExamDurationActivity.class);
                startActivityForResult(intent, NEW_ASSIGNMENT);
                return true;
            }
        });

        return true;
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ASSIGNMENT && resultCode == Activity.RESULT_OK) {
            final Intent intent = new Intent();
            intent.putExtra("name", "Teleinf. y redes II - final");
            setResult(RESULT_OK, intent);
            finish();
        }
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
