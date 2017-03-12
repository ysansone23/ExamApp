package com.uade.tesis.evaluated.utils;

import android.graphics.Bitmap;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;

public class EvaluatedWebViewClient extends android.webkit.WebViewClient {

    private final WebViewActions actions;

    public EvaluatedWebViewClient(WebViewActions actions) {
        super();
        this.actions = actions;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        actions.onPageStarted();
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        animate(view);
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        actions.onReceivedError();
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        actions.onReceivedError();
    }

    private void animate(final WebView view) {
        Animation anim = AnimationUtils.loadAnimation(view.getContext(), android.R.anim.slide_in_left);
        view.startAnimation(anim);
    }

    /**
     * Interface used to set actions to the different web view states
     */
    public interface WebViewActions {

        void onPageStarted();

        void onReceivedError();
    }
}
