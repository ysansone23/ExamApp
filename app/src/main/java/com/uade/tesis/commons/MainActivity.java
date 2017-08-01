package com.uade.tesis.commons;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.uade.tesis.R;
import com.uade.tesis.evaluated.DecoderActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startEvaluated(final View view) {
        final Intent intent = DecoderActivity.getIntent(this);
        startActivity(intent);
    }
}

