package com.uade.tesis.evaluator.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.uade.tesis.R;
import com.uade.tesis.evaluator.assignments.AssignmentsViewHolder;
import java.util.List;

public class BaseButtonsAdapter extends RecyclerView.Adapter<AssignmentsViewHolder> {

    private final List<Button> buttons;
    private final ButtonClickListener listener;

    public BaseButtonsAdapter(final List<Button> buttons, final ButtonClickListener listener) {
        this.buttons = buttons;
        this.listener = listener;
    }

    @Override
    public AssignmentsViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View item = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new AssignmentsViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final AssignmentsViewHolder holder, final int position) {
        holder.bindToElement(buttons.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return buttons.size();
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.list_button;
    }

    public interface ButtonClickListener {
        void onButtonClick(final String title);
    }
}
