package com.technopark.technopark_hw1;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumbersViewHolder> {

    private String NUMBER_VALUE = "value";
    private String NUMBER_COLOR = "color";
    private List<Integer> numbers;
    private Context context;

    NumbersAdapter(Context ctx, List<Integer> data) {
        numbers = data;
        context = ctx;
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
                    Fragment numberExpanded = new DisplayListItem();
                    Bundle args = new Bundle();
                    int value = numbers.get(position);
                    args.putInt(NUMBER_VALUE, value);
                    if (value % 2 == 0) {
                        args.putInt(NUMBER_COLOR, Color.RED);
                    } else {
                        args.putInt(NUMBER_COLOR, Color.BLUE);
                    }
                    numberExpanded.setArguments(args);

                    // TODO: redo with interface
                    FragmentManager fragmentManager = ((MainActivity)context).getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.container, numberExpanded);
                    transaction.addToBackStack(null);
                    transaction.commit();
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
        if (value % 2 == 0) {
            holder.numberValue.setTextColor(Color.RED);
        } else {
            holder.numberValue.setTextColor(Color.BLUE);
        }
        holder.bindClickListener(position);
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }
}
