package com.technopark.technopark_hw1;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumbersViewHolder> {

    private List<Integer> numbers;
    private DisplayList.NumberClickListener clickListener;

    NumbersAdapter(List<Integer> data) {
        numbers = data;
    }

    class NumbersViewHolder extends RecyclerView.ViewHolder {
        private TextView numberValue;

        NumbersViewHolder(@NonNull View itemView) {
            super(itemView);
            numberValue = itemView.findViewById(R.id.value);
        }

        void bindClickListener(final int position) {
            numberValue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int value = numbers.get(position);
                    int color = (value % 2 == 0) ? Color.RED : Color.BLUE;
                    if (clickListener != null) {
                        clickListener.onClick(value, color);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public NumbersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.numbers_item, parent, false);
        return new NumbersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumbersViewHolder holder, int position) {
        int value = numbers.get(position);
        holder.numberValue.setText(String.valueOf(value));
        int color = (value % 2 == 0) ? Color.RED : Color.BLUE;
        holder.numberValue.setTextColor(color);

        holder.bindClickListener(position);
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    void setClickListener(DisplayList.NumberClickListener listener) {
        clickListener = listener;
    }
}
