package com.uade.tesis.evaluator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.uade.tesis.R;
import com.uade.tesis.evaluator.assignments.AssignmentsActivity;

public class EvaluatorLogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle("Evaluador");
        }

        setContentView(R.layout.activity_log_in);
        setUpView();
    }

    private void setUpView() {
        final EditText user = (EditText) findViewById(R.id.log_in_user);
        final EditText password = (EditText) findViewById(R.id.log_in_password);
        final Button button = (Button) findViewById(R.id.log_in_button);

        user.setHint("Usuario");
        password.setHint("Contraseña");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Intent intent = new Intent(EvaluatorLogInActivity.this, AssignmentsActivity.class);
                startActivity(intent);
            }
        });
    }
}
