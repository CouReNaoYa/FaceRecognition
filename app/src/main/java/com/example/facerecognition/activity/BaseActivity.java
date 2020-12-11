package com.example.facerecognition.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(getLayoutRes());

        initData(savedInstanceState);
        initView();
        setListener();
        loadData();
    }

    protected abstract void loadData();

    protected abstract void setListener();

    protected abstract void initData(Bundle savedInstanceState);

    //设置view
    protected abstract void initView();
    //设置布局
    protected abstract int getLayoutRes();
}
