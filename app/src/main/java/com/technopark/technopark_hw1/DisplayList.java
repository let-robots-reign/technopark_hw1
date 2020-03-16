package com.technopark.technopark_hw1;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DisplayList extends Fragment {
    private String DATA_KEY = "numbers";
    private ArrayList<Integer> numbers;
    private RecyclerView list;
    private NumbersAdapter adapter;
    private NumberClickListener clickListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        clickListener = (NumberClickListener) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // generating data
        if (savedInstanceState == null) {
            numbers = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                numbers.add(i);
            }
        } else {
            numbers = savedInstanceState.getIntegerArrayList(DATA_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.numbers_list, container, false);

        // finding RecyclerView
        list = rootView.findViewById(R.id.recycler_view);

        //setting LayoutManager
        int orientation = this.getResources().getConfiguration().orientation;
        int columns;  // number of columns depends on the orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            columns = 3;
        } else {
            columns = 4;
        }
        list.setLayoutManager(new GridLayoutManager(getActivity(), columns));

        // connecting to the adapter
        adapter = new NumbersAdapter(numbers);
        adapter.setClickListener(clickListener);

        list.setAdapter(adapter);

        // adding a new number
        Button addButton = rootView.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.add(numbers.size() + 1);
                adapter.notifyItemInserted(numbers.size() - 1);
                // it's better to scroll to the bottom when we add an element
                list.smoothScrollToPosition(numbers.size() - 1);
            }
        });

        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(DATA_KEY, numbers);
    }

    @Override
    public void onDetach() {
        clickListener = null;
        super.onDetach();
    }

    // an interface to process fragment replacement (implementation in Activity)
    public interface NumberClickListener {
        void onClick(int value, int color);
    }
}
