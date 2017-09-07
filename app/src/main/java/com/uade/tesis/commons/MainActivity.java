package com.uade.tesis.commons;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.uade.tesis.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startEvaluated(final View view) {
        final Intent intent = new Intent(MainActivity.this, EvaluatedMenuActivity.class);
        startActivity(intent);
    }

    public void startTeacher(final View view) {
    }
}

