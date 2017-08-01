package com.uade.tesis.evaluated;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.uade.tesis.R;

public class EvaluatedCongratsActivity extends Activity {

    public static Intent getIntent(final Context context) {
        return new Intent(context, EvaluatedCongratsActivity.class);
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_congrats);
    }
}
