package com.uade.tesis.evaluator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class ResponseActivity extends AppCompatActivity {

    private static final String TITLE = "title";

    public static Intent getIntent(@NonNull final Context context, final String title) {
        final Intent intent = new Intent(context, ResponseActivity.class);
        intent.putExtra(TITLE, title);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(getIntent().getStringExtra(TITLE));
        }
    }
}
