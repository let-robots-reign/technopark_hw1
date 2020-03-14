package com.technopark.technopark_hw1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DisplayListItem extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.number_expanded, container, false);
        TextView text = rootView.findViewById(R.id.value);
        Bundle args = getArguments();
        String NUMBER_VALUE = "value";
        String NUMBER_COLOR = "color";
        int color = Color.BLACK;
        int value = 0;
        if (args != null) {
            value = args.getInt(NUMBER_VALUE);
            color = args.getInt(NUMBER_COLOR);
        }
        text.setText(String.valueOf(value));
        text.setTextColor(color);
        return rootView;
    }
}
