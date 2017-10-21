package com.uade.tesis.evaluator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import com.uade.tesis.R;

public class SetExamDurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_set_exam_duration);

        findViewById(R.id.exam_duration).setFocusable(true);

        final ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle("Nuevo Examen");
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.new_exam_menu_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        super.onPrepareOptionsMenu(menu);
        final MenuItem next = menu.findItem(R.id.new_exam_next);
        next.setIcon(getResources().getDrawable(R.drawable.arrow_forward, getTheme()));
        next.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(final MenuItem item) {
                findViewById(R.id.exam_duration_label).setVisibility(View.GONE);
                findViewById(R.id.exam_duration).setVisibility(View.GONE);
                findViewById(R.id.exam_duration_hours).setVisibility(View.GONE);

                final ImageView image = (ImageView) findViewById(R.id.exam_duration_image);
                image.setImageDrawable(getResources().getDrawable(R.drawable.qr_code, getTheme()));
                image.setVisibility(View.VISIBLE);
                next.setIcon(getResources().getDrawable(R.drawable.check, getTheme()));
                next.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(final MenuItem item) {
                        setResult(RESULT_OK);
                        finish();
                        return false;
                    }
                });

                return true;
            }
        });

        return true;
    }
}
