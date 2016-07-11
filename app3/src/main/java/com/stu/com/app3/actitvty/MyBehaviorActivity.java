package com.stu.com.app3.actitvty;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stu.com.app3.R;
import com.stu.com.app3.net.Meizi;
import com.stu.com.app3.net.MyOkhttp;
import com.stu.com.app3.ui.GridAdapter;
import com.stu.com.app3.ui.StatusBarUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 */
public class MyBehaviorActivity extends AppCompatActivity {
    private static RecyclerView recyclerview;
    private CoordinatorLayout coordinatorLayout;
    private GridAdapter mAdapter;
    private List<Meizi> meizis;
    private StaggeredGridLayoutManager mLayoutManager;
    private int lastVisibleItem ;
    private int page=1;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_behavior_demo);

        StatusBarUtil.setStatusBarColor(MyBehaviorActivity.this,R.color.colorPrimaryDark);

        initView();
        setListener();

        new GetData().execute("http://gank.io/api/data/福利/10/1");

    }

    private void initView() {
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.behavior_demo_coordinatorLayout);
        recyclerview=(RecyclerView)findViewById(R.id.behavior_demo_recycler);
        swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.behavior_demo_swipe_refresh) ;
        fab=(FloatingActionButton) findViewById(R.id.fab);

        mLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(mLayoutManager);
        swipeRefreshLayout.setProgressViewOffset(false ,0 , (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

    }

    private void setListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                new GetData().execute("http://gank.io/api/data/福利/10/1");
            }
        });
        //recyclerview滚动监听实现自动加载
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//              0：当前屏幕停止滚动；1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；2时：随用户的操作，屏幕上产生的惯性滑动；
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+2>=mLayoutManager.getItemCount()){
                    new GetData().execute("http://gank.io/api/data/福利/10/"+(++page));
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] postions=mLayoutManager.findLastVisibleItemPositions(null);
                lastVisibleItem=Math.max(postions[0],postions[1]);
            }
        });
    }

    //获取图片列表数据
    JSONObject jsonObject;
    private class GetData extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected String doInBackground(String... params) {
            return MyOkhttp.get(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(!TextUtils.isEmpty(result)){
                Gson gson=new Gson();
                String jsonData=null;
                try {
                    jsonObject = new JSONObject(result);
                    jsonData = jsonObject.getString("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(meizis==null||meizis.size()==0){
                    meizis=gson.fromJson(jsonData,new TypeToken<List<Meizi>>(){}.getType());
                    Meizi pages=new Meizi();
                    pages.setPage(page);
                    meizis.add(pages);
                }else{
                    List<Meizi> more= gson.fromJson(jsonData, new TypeToken<List<Meizi>>() {}.getType());
                    meizis.addAll(more);
                    Meizi pages=new Meizi();
                    pages.setPage(page);
                    meizis.add(pages);
                }
                if(mAdapter==null){
                    recyclerview.setAdapter(mAdapter=new GridAdapter(MyBehaviorActivity.this,meizis));
                }else{
                    mAdapter.notifyDataSetChanged();
                }
            }
            swipeRefreshLayout.setRefreshing(false);
        }
    }


}
