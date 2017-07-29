package com.uade.tesis.evaluated.utils;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.Toast;

public class EvaluatedWebViewClient extends android.webkit.WebViewClient {

    private final WebViewActions actions;

    public EvaluatedWebViewClient(final WebViewActions actions) {
        super();
        this.actions = actions;
    }

    @Override
    public void onPageStarted(final WebView view, final String url, final Bitmap favicon) {
        actions.onPageStarted();
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(final WebView view, final String url) {
        super.onPageFinished(view, url);
        animate(view);
        view.setVisibility(View.VISIBLE);
        actions.onPageFinished();
    }

    @Override
    public void onReceivedError(final WebView view, final int errorCode, final String description,
        final String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        if (errorCode == ERROR_HOST_LOOKUP) {
            actions.onReceivedError(true);
        } else {
            actions.onReceivedError(false);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceivedError(final WebView view, final WebResourceRequest request, final WebResourceError error) {
        super.onReceivedError(view, request, error);
        if (error.getErrorCode() == ERROR_HOST_LOOKUP) {
            actions.onReceivedError(true);
        } else {
            actions.onReceivedError(false);
        }
    }

    private void animate(final WebView view) {
        final Animation anim = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_in_left);
        view.startAnimation(anim);
    }

    @Override
    public boolean shouldOverrideUrlLoading(final WebView view, final WebResourceRequest request) {

        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
        return super.shouldOverrideUrlLoading(view, url);
    }

    /**
     * Interface used to set actions to the different web view states
     */
    public interface WebViewActions {

        void onPageStarted();

        void onReceivedError(boolean isNetworkingError);

        void onPageFinished();
    }
}
