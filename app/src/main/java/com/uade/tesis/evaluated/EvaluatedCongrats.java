package com.uade.tesis.evaluated;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.uade.tesis.R;

public class EvaluatedCongrats extends AppCompatActivity {

    public static Intent getIntent(final Context context) {
        return new Intent(context, EvaluatedCongrats.class);
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_congrats);
    }
}
