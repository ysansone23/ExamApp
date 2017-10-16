package com.uade.tesis.evaluator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.uade.tesis.R;
import com.uade.tesis.evaluator.students.StudentsActivity;

public class GradeActivity extends AppCompatActivity {

    private static final String TITLE = "title";

    public static Intent getIntent(@NonNull final Context context, final String title) {
        final Intent intent = new Intent(context, GradeActivity.class);
        intent.putExtra(TITLE, title);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grade);

        final ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(getIntent().getStringExtra(TITLE));
        }

        final Button button = (Button) findViewById(R.id.send_grade);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Intent intent = StudentsActivity.getIntent(GradeActivity.this, getIntent().getStringExtra(TITLE));
                startActivity(intent);
            }
        });
    }
}
