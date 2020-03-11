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

    public NumbersAdapter(List<Integer> data) {
        numbers = data;
    }

    class NumbersViewHolder extends RecyclerView.ViewHolder {
        private TextView numberValue;

        public NumbersViewHolder(@NonNull View itemView) {
            super(itemView);
            numberValue = itemView.findViewById(R.id.value);
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
        if (value % 2 == 0) {
            holder.numberValue.setTextColor(Color.RED);
        } else {
            holder.numberValue.setTextColor(Color.BLUE);
        }
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }
}
