package com.uade.tesis.evaluator.students;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.uade.tesis.R;
import com.uade.tesis.evaluator.ResponseActivity;
import com.uade.tesis.evaluator.utils.BaseButtonsAdapter;
import java.util.ArrayList;
import java.util.List;

public class StudentsActivity extends AppCompatActivity implements BaseButtonsAdapter.ButtonClickListener {

    private static final String TITLE = "title";

    public static Intent getIntent(@NonNull final Context context, @Nullable final String title) {
        final Intent intent = new Intent(context, StudentsActivity.class);
        intent.putExtra(TITLE, title);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttons_list);
        findViewById(R.id.buttons_list_fab).setVisibility(View.GONE);

        final ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle(getIntent().getStringExtra(TITLE));
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.buttons_list);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        final List<String> students = new ArrayList<>();
        students.add("Agustin Catalano");
        students.add("Yamila Sansone");
        students.add("Yanina Barrot");
        students.add("Emmanuel Castillo de Carvhalo");
        students.add("Florencia Silvestre");
        students.add("Alexis Castiglioni");
        students.add("Bruno Giulianetti");

        final List<Button> buttons = new ArrayList<>();
        Button button;
        for (int i = 0; i < students.size(); i++) {
            button = new Button(this);
            button.setText(students.get(i));
            button.setVisibility(View.INVISIBLE);
            buttons.add(button);
        }
        recyclerView.setAdapter(new BaseButtonsAdapter(buttons, this));
    }

    @Override
    public void onButtonClick(final String title) {
        final Intent intent = ResponseActivity.getIntent(this, title, "http://google.com", true);
        startActivity(intent);
    }
}
