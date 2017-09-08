package com.uade.tesis.evaluator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.uade.tesis.R;

public class EvaluatorLogInActivity extends AppCompatActivity {

    private EditText user;
    private EditText password;

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
        user = (EditText) findViewById(R.id.log_in_user);
        password = (EditText) findViewById(R.id.log_in_password);
        final Button button = (Button) findViewById(R.id.log_in_button);

        user.setHint("Usuario");
        password.setHint("Contraseña");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(canLogIn()) {
                    final Intent intent = new Intent(EvaluatorLogInActivity.this, AssignmentsActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean canLogIn() {
        final boolean isUserEmpty = user.getText() == null || TextUtils.isEmpty(user.getText().toString());
        final boolean isPasswordEmpty = password.getText() == null || TextUtils.isEmpty(password.getText().toString());

        if (isUserEmpty && isPasswordEmpty) {
            Toast.makeText(this, "Debe ingresar datos", Toast.LENGTH_LONG).show();
            return false;
        } else if (isUserEmpty) {
                Toast.makeText(this, "Debe ingresar un usuario", Toast.LENGTH_LONG).show();
            return false;
        } else if (isPasswordEmpty) {
            Toast.makeText(this, "Debe ingresar una contraseña", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
