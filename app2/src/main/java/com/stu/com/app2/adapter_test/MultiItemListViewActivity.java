package com.stu.com.app2.adapter_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.stu.com.app2.R;
import com.stu.com.app2.adapter_test.adapter.lv.ChatAdapter;
import com.stu.com.app2.adapter_test.bean.ChatMessage;
import com.zhy.adapter.abslistview.CommonAdapter;
public class MultiItemListViewActivity extends AppCompatActivity
{

    private ListView mListView;
    private CommonAdapter mAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mains);


        mListView = (ListView) findViewById(R.id.id_listview_list);
        mListView.setDivider(null);
        mListView.setAdapter(new ChatAdapter(this, ChatMessage.MOCK_DATAS));

    }

}
