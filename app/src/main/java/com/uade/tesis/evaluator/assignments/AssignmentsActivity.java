package com.uade.tesis.evaluator.assignments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.uade.tesis.R;
import com.uade.tesis.evaluator.students.StudentsActivity;
import com.uade.tesis.evaluator.utils.BaseButtonsAdapter;
import java.util.ArrayList;
import java.util.List;

public class AssignmentsActivity extends AppCompatActivity implements BaseButtonsAdapter.ButtonClickListener {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttons_list);

        final ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle("Materias");
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.buttons_list);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        final List<String> assignments = new ArrayList<>();
        assignments.add("Ingenieria de software");
        assignments.add("Calidad de software");
        assignments.add("Arquitectura de computadores");
        assignments.add("Química");

        final List<Button> buttons = new ArrayList<>();
        Button button;
        for (int i = 0; i < assignments.size(); i++) {
            button = new Button(this);
            button.setText(assignments.get(i));
            button.setVisibility(View.INVISIBLE);
            buttons.add(button);
        }
        recyclerView.setAdapter(new BaseButtonsAdapter(buttons, this));
    }

    @Override
    public void onButtonClick(final String title) {
        final Intent intent = StudentsActivity.getIntent(this, title);
        startActivity(intent);
    }
}
