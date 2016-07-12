package com.stu.com.app2.custom_view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.stu.com.app2.R;

/**
 * 测试Drawable组件    可以在XML中定义drawableLeft, drawableRight等的大小的控件
 */
public class ch01_Drawable_Widget_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch01__drawable__widget_);
    }
}
