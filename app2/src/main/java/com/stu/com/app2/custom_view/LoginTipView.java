package com.stu.com.app2.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stu.com.app2.R;

/**
 * 自定义组合控件
 */
public class LoginTipView  extends RelativeLayout implements View.OnClickListener {

    TextView mLogin;
    public LoginTipView(Context context, AttributeSet attrs) {
        super(context, attrs);

        addView(View.inflate(context,R.layout.ch01_login_tip_view,null));

        mLogin = (TextView) findViewById(R.id.login_register);
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        getContext().startActivity(new Intent(getContext(), LoginActivity.class));
    }
}
