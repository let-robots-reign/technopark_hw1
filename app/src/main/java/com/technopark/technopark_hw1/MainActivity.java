package com.technopark.technopark_hw1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements DisplayList.NumberClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment numberFragment = new DisplayList();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.container, numberFragment);
            transaction.commit();
        }
    }

    // implementing an interface and replacing fragments
    @Override
    public void onClick(int value, int color) {
        String NUMBER_VALUE = "value";
        String NUMBER_COLOR = "color";

        Fragment numberExpanded = new DisplayListItem();
        Bundle args = new Bundle();
        args.putInt(NUMBER_VALUE, value);
        args.putInt(NUMBER_COLOR, color);
        numberExpanded.setArguments(args);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, numberExpanded);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
