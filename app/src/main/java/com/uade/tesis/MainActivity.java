package com.uade.tesis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.uade.tesis.evaluated.DecoderActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startEvaluado(final View view) {
        final Intent intent = DecoderActivity.getIntent(this);
        startActivity(intent);
    }
}

