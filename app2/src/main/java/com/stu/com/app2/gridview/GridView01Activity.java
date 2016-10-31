package com.stu.com.app2.gridview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.stu.com.app2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridView01Activity extends AppCompatActivity {
    private GridView gridView;
    private GridViewAdapter adapter;
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view01);
        gridView = (GridView) findViewById(R.id.content);
        initData();
        gridView.setAdapter(adapter);
    }
    protected void initData(){
        Map<String, Object> renren=new HashMap<String, Object>();
        renren.put("id", "1");
        renren.put("name", "人人");
        Map<String, Object> sina=new HashMap<String, Object>();
        sina.put("id", "2");
        sina.put("name", "新浪微博");
        Map<String, Object> qq=new HashMap<String, Object>();
        qq.put("id", "3");
        qq.put("name", "QQ");
        Map<String, Object> weixin=new HashMap<String, Object>();
        weixin.put("id", "4");
        weixin.put("name", "微信");
        Map<String, Object> feition=new HashMap<String, Object>();
        feition.put("id", "5");
        feition.put("name", "飞信");
        list.add(renren);
        list.add(sina);
        list.add(qq);
        list.add(weixin);
        list.add(feition);
        list.add(renren);
        list.add(sina);
        list.add(qq);
        list.add(weixin);
        list.add(feition);
        adapter=new GridViewAdapter(this, list);
    }
}
