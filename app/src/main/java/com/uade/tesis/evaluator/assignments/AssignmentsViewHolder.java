package com.uade.tesis.evaluator.assignments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.uade.tesis.evaluator.utils.BaseButtonsAdapter;

public class AssignmentsViewHolder extends RecyclerView.ViewHolder {

    private final Button button;

    public AssignmentsViewHolder(final View itemView) {
        super(itemView);
        button = (Button) itemView;
    }

    public void bindToElement(final Button button, final BaseButtonsAdapter.ButtonClickListener listener) {
        final String title = button.getText().toString();
        this.button.setText(title);
        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                listener.onButtonClick(title);
            }
        });
    }
}
