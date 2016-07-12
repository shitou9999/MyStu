package com.stu.com.mystu1.utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/7/6. 退出
 */
public class EixtActitvty extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 添加Activity到堆栈
        ActivityContainer.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 结束Activity从栈中移除该Activity
        ActivityContainer.getInstance().removeActivity(this);
    }

}
