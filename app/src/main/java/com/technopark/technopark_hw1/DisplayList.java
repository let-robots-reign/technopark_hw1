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
import java.util.List;

public class DisplayList extends Fragment {
    private List<Integer> numbers;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.numbers_list, container, false);

        // finding RecyclerView
        RecyclerView list = rootView.findViewById(R.id.recycler_view);

        //setting LayoutManager
        int orientation = this.getResources().getConfiguration().orientation;
        int columns;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            columns = 3;
        } else {
            columns = 4;
        }
        list.setLayoutManager(new GridLayoutManager(getActivity(), columns));

        // generating data
        numbers = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            numbers.add(i);
        }
        // connecting to the adapter
        final NumbersAdapter adapter = new NumbersAdapter(numbers);
        list.setAdapter(adapter);

        Button addButton = rootView.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numbers.add(numbers.size() + 1);
                adapter.notifyItemInserted(numbers.size() - 1);
            }
        });

        return rootView;
    }
}
