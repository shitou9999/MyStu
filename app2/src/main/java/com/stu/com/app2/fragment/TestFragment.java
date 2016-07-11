package com.stu.com.app2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.stu.com.app2.BottomSheetBehavior.BottomSheetActivity;

import com.stu.com.app2.adapter_test.MultiItemRvActivity;
import com.stu.com.app2.adapter_test.RecyclerViewActivity;

/**
 * 测试fragment
 */
public class TestFragment extends Fragment implements View.OnClickListener{

    Button bt1;
    Button bt2;
    Button bt3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_test_fragment,container,false);
        bt1=(Button) view.findViewById(R.id.bt1);
        bt2=(Button) view.findViewById(R.id.bt2);
        bt3=(Button) view.findViewById(R.id.bt3);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1:
                Intent intent=new Intent(getActivity(), RecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.bt2:
                Intent intent2=new Intent(getActivity(), MultiItemRvActivity.class);
                startActivity(intent2);
                break;
            case R.id.bt3:
                Intent intent3=new Intent(getActivity(), BottomSheetActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
