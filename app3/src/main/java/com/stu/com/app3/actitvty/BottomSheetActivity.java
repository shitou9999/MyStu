package com.stu.com.app3.actitvty;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.stu.com.app3.R;
import com.stu.com.app3.net.Meizi;
import com.stu.com.app3.net.MyOkhttp;
import com.stu.com.app3.ui.GridAdapter;
import com.stu.com.app3.ui.SnackbarUtil;
import com.stu.com.app3.ui.StatusBarUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2016/7/11.
 * 仿百度地图 ddd
 */
public class BottomSheetActivity extends AppCompatActivity implements GridAdapter.OnRecyclerViewItemClickListener{
    public static final String TAG="jereh";
    private static RecyclerView recyclerview;
    private CoordinatorLayout coordinatorLayout;
    private GridAdapter mAdapter;
    private List<Meizi> meizis;
    private StaggeredGridLayoutManager mLayoutManager;
    private int lastVisibleItem ;
    private int page=1;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout design_bottom_sheet,design_bottom_sheet_bar;
    private BottomSheetBehavior behavior;
    private ImageView bottom_sheet_iv;
    private TextView bottom_sheet_tv;
    private boolean isSetBottomSheetHeight;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet_demo);
        StatusBarUtil.setStatusBarColor(BottomSheetActivity.this,R.color.colorPrimaryDark);//设置状态栏颜色

        initView();
        setListener();

        new GetData().execute("http://gank.io/api/data/福利/10/1");
    }

    private void initView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.bottom_sheet_demo_coordinatorLayout);
        recyclerview=(RecyclerView)findViewById(R.id.bottom_sheet_demo_recycler);

        design_bottom_sheet_bar=(RelativeLayout) findViewById(R.id.design_bottom_sheet_bar);

        design_bottom_sheet=(RelativeLayout) findViewById(R.id.design_bottom_sheet);
        bottom_sheet_iv=(ImageView) findViewById(R.id.bottom_sheet_iv);
        bottom_sheet_tv=(TextView) findViewById(R.id.bottom_sheet_tv);

        swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.bottom_sheet_demo_swipe_refresh) ;
        swipeRefreshLayout.setProgressViewOffset(false, 0,  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

        mLayoutManager=new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(mLayoutManager);



        behavior = BottomSheetBehavior.from(design_bottom_sheet);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);

        //修改SetBottomSheet的高度 留出顶部工具栏的位置
        if(!isSetBottomSheetHeight){
            CoordinatorLayout.LayoutParams linearParams =(CoordinatorLayout.LayoutParams) design_bottom_sheet.getLayoutParams();
            linearParams.height=coordinatorLayout.getHeight()-design_bottom_sheet_bar.getHeight();
            design_bottom_sheet.setLayoutParams(linearParams);
            isSetBottomSheetHeight=true;//设置标记 只执行一次
        }
    }

//    LayoutManager的常用方法
//    findFirstVisibleItemPosition() 返回当前第一个可见 Item 的 position
//    findFirstCompletelyVisibleItemPosition() 返回当前第一个完全可见 Item 的 position
//    findLastVisibleItemPosition() 返回当前最后一个可见 Item 的 position
//    findLastCompletelyVisibleItemPosition() 返回当前最后一个完全可见 Item 的 position.
//    scrollBy() 滚动到某个位置。
    private void setListener() {

        //监听状态
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState!=BottomSheetBehavior.STATE_COLLAPSED&&bottom_sheet_tv.getVisibility()==View.VISIBLE){  //展开并且字体显示的
                    bottom_sheet_tv.setVisibility(View.GONE);
                    bottom_sheet_iv.setVisibility(View.VISIBLE);
                    mAdapter.setOnItemClickListener(null);//展开状态下屏蔽RecyclerView item的点击
                }else if(newState==BottomSheetBehavior.STATE_COLLAPSED&&bottom_sheet_tv.getVisibility()==View.GONE){
                    bottom_sheet_tv.setVisibility(View.VISIBLE);
                    bottom_sheet_iv.setVisibility(View.GONE);
                    mAdapter.setOnItemClickListener( BottomSheetActivity.this);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                if(bottomSheet.getTop()<2*design_bottom_sheet_bar.getHeight()){
                    design_bottom_sheet_bar.setVisibility(View.VISIBLE);
                    design_bottom_sheet_bar.setAlpha(slideOffset);
                    design_bottom_sheet_bar.setTranslationY(bottomSheet.getTop()-2*design_bottom_sheet_bar.getHeight());
                }else{
                    design_bottom_sheet_bar.setVisibility(View.INVISIBLE);
                }
            }
        });

        design_bottom_sheet_bar.setOnClickListener(new View.OnClickListener() {  //点击设置
            @Override
            public void onClick(View v) {
                behavior.setState(BottomSheetBehavior.STATE_COLLAPSED); //折叠状态
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                new GetData().execute("http://gank.io/api/data/福利/10/1");
            }
        });
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //recyclerview滚动时  如果BottomSheetBehavior不是折叠状态就置为折叠
                if(behavior.getState()!=BottomSheetBehavior.STATE_COLLAPSED){
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
//                0：当前屏幕停止滚动；1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；2时：随用户的操作，屏幕上产生的惯性滑动；
                //用来判断recyclerview自动加载
                // 滑动状态停止并且剩余少于两个item时，自动加载下一页
                if(newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+2>=mLayoutManager.getItemCount()){
                    new GetData().execute("http://gank.io/api/data/福利/10/"+(++page));
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //用来判断recyclerview自动加载
//                StaggeredGridLayoutManager因为item的位置是交错的，findLastVisibleItemPosition()方法返回的是一个数组，所以我们得先判断下这个数组的最大值
                int[] positions=mLayoutManager.findLastVisibleItemPositions(null);
                lastVisibleItem=Math.max(positions[0],positions[1]);//根据StaggeredGridLayoutManager设置的列数来定

                //获取加载的最后一个可见视图在适配器的位置。------------------非瀑布流采用这种
//                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });

    }

    @Override
    public void onItemClick(View view) {
        //recyclerview item点击事件处理

        int position=recyclerview.getChildAdapterPosition(view);

        SnackbarUtil.ShortSnackbar(coordinatorLayout,"点击第"+position+"个",SnackbarUtil.Info).show();

        Picasso.with(BottomSheetActivity.this).load(meizis.get(position).getUrl()).into(bottom_sheet_iv);
    }

    @Override
    public void onItemLongClick(View view) {

    }

    //获取图片列表数据
    private class GetData extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected String doInBackground(String... params) {

            return MyOkhttp.get(params[0]);
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(!TextUtils.isEmpty(result)){
                JSONObject jsonObject;
                Gson gson=new Gson();
                String jsonData=null;

                try {
                    jsonObject = new JSONObject(result);
                    jsonData = jsonObject.getString("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(meizis==null||meizis.size()==0){
                    meizis= gson.fromJson(jsonData, new TypeToken<List<Meizi>>() {}.getType());
                    Meizi pages=new Meizi();
                    pages.setPage(page);
                    meizis.add(pages);

                    Picasso.with(BottomSheetActivity.this).load(meizis.get(0).getUrl()).into(bottom_sheet_iv);
                }else{
                    List<Meizi> more= gson.fromJson(jsonData, new TypeToken<List<Meizi>>() {}.getType());
                    meizis.addAll(more);
                    Meizi pages=new Meizi();
                    pages.setPage(page);
                    meizis.add(pages);
                }

                if(mAdapter==null){
                    recyclerview.setAdapter(mAdapter = new GridAdapter(BottomSheetActivity.this,meizis));
                    mAdapter.setOnItemClickListener(BottomSheetActivity.this);

                }else{
                    mAdapter.notifyDataSetChanged();
                }
            }
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
