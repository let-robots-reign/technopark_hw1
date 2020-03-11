package com.technopark.technopark_hw1;

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
    private static final String DATA_KEY = "NUMBERS";
    private ArrayList<Integer> numbers;
    private RecyclerView list;

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

        // generating data
        if (savedInstanceState == null) { // if there's nothing to restore
            numbers = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                numbers.add(i);
            }
        } else {
            numbers = savedInstanceState.getIntegerArrayList(DATA_KEY);
        }
        // connecting to the adapter
        final NumbersAdapter adapter = new NumbersAdapter(numbers);
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
}
