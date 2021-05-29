package com.rsschool.android2021;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity
        implements OnFirstFragmentListener, OnSecondFragmentListener {

    private Fragment fragment;
    private OnBackPressedListener onBackPressedListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            openFirstFragment(0);
        }
    }

    @Override
    protected void onDestroy() {
        onBackPressedListener = null;
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedListener != null)
            onBackPressedListener.doBack();
        else
            super.onBackPressed();
    }

    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    private void openFirstFragment(int previousNumber) {
        fragment = FirstFragment.newInstance(previousNumber);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    private void openSecondFragment(int min, int max) {
        fragment = SecondFragment.newInstance(min, max);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void toSecondPage(int min, int max) {
        openSecondFragment(min, max);
    }

    @Override
    public void toFirstPage(int prev) {
        getSupportFragmentManager().popBackStack();
        openFirstFragment(prev);
    }

    interface OnBackPressedListener {
        void doBack();
    }
}
