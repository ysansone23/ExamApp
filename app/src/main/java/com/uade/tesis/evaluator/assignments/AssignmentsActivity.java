package com.uade.tesis.evaluator.assignments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import com.uade.tesis.R;
import com.uade.tesis.evaluator.ResponseActivity;
import com.uade.tesis.evaluator.students.StudentsActivity;
import com.uade.tesis.evaluator.utils.BaseButtonsAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AssignmentsActivity extends AppCompatActivity implements BaseButtonsAdapter.ButtonClickListener {

    private static final int NEW_ASSIGNMENT = 1;
    private static final String ASSIGNMENTS = "assignments";
    List<String> assignments = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttons_list);

        final ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle("Materias");
        }

        recyclerView = (RecyclerView) findViewById(R.id.buttons_list);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        if (savedInstanceState == null) {
            assignments.add("Ingenieria de software");
            assignments.add("Calidad de software");
            assignments.add("Arquitectura de computadores");
            assignments.add("Química");
        } else {
            assignments = (List<String>) savedInstanceState.getSerializable(ASSIGNMENTS);
        }

        setUpButtons();
        setUpFab();
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        outState.putSerializable(ASSIGNMENTS, (Serializable) assignments);
        super.onSaveInstanceState(outState);
    }

    private void setUpButtons() {
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

    private void setUpFab() {
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.buttons_list_fab);
        fab.setSize(FloatingActionButton.SIZE_NORMAL);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Intent intent = ResponseActivity.getIntent(AssignmentsActivity.this, "Nuevo Examen",
                    "https://docs.google.com/forms/u/0/", false);
                startActivityForResult(intent, NEW_ASSIGNMENT);
            }
        });
        fab.show();
    }

    @Override
    public void onButtonClick(final String title) {
        final Intent intent = StudentsActivity.getIntent(this, title);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ASSIGNMENT && resultCode == Activity.RESULT_OK) {
            final String assignmentName = data.getStringExtra("name");
            if (!TextUtils.isEmpty(assignmentName)) {
                assignments.add(assignmentName);
            }
            setUpButtons();
        }
    }
}
