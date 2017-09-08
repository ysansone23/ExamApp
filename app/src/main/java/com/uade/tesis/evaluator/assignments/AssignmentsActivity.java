package com.uade.tesis.evaluator.assignments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.uade.tesis.R;

public class AssignmentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignments_list);

        final ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle("");
        }
    }

    public void assignment1(final View view) {

    }

    public void assignment2(final View view) {
    }

    public void assignment3(final View view) {
    }

    public void assignment4(final View view) {
    }
}
