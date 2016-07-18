package com.stu.com.app2.MagicViewPager;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.stu.com.app2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 自动轮播的ViewPager//没有加指示器，不能一个页面存在三个图片，没实现
 */
public class MainActivity_ViewPager extends AppCompatActivity implements View.OnClickListener ,ViewPager.OnPageChangeListener {

//    private static final String TAG=MainActivity_ViewPager.this.

    private AutoPlayViewPager autoPlayViewPage;
    private List<Integer> resIds;
    private BannerAdapter bannerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_viewpager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btn_scroll_change_left).setOnClickListener(this);
        findViewById(R.id.btn_scroll_change_right).setOnClickListener(this);
        findViewById(R.id.btn_scroll_previous).setOnClickListener(this);
        findViewById(R.id.btn_scroll_next).setOnClickListener(this);

        // 这里可以换成http://www.xx.com/xx.png这种连接的集合
        resIds = new ArrayList<>();
        // 模拟几张图片
        resIds.add(R.mipmap.ic_launcher);
        resIds.add(R.mipmap.h01);
        resIds.add(R.mipmap.h02);
//        resIds.add(R.mipmap.h03);
//        resIds.add(R.mipmap.h04);

        bannerAdapter = new BannerAdapter(this);
        bannerAdapter.update(resIds);

        autoPlayViewPage = (AutoPlayViewPager) findViewById(R.id.view_pagers);

        autoPlayViewPage.setPageMargin(30);
        autoPlayViewPage.setOffscreenPageLimit(3);

        autoPlayViewPage.addOnPageChangeListener(this);
        setUI();
        autoPlayViewPage.setAdapter(bannerAdapter);


        autoPlayViewPage.setDirection(AutoPlayViewPager.Direction.LEFT);// 设置播放方向
        autoPlayViewPage.setCurrentItem(200*resIds.size());
        autoPlayViewPage.start(); // 开始轮播
    }

    private ViewGroup radioGroup;
    private ImageView[] tips;
    private void setUI(){
        radioGroup = (ViewGroup)findViewById(R.id.indicator);
        radioGroup.removeAllViews();
        tips = new ImageView[resIds.size()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(5, 0, 0, 0);
        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.mipmap.select);
            } else {
                tips[i].setBackgroundResource(R.mipmap.un_select);
            }
            radioGroup.addView(imageView);
        }
        bannerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scroll_previous:// 播放上一个
                autoPlayViewPage.previous();
                break;
            case R.id.btn_scroll_next:// 播放下一个
                autoPlayViewPage.next();
                break;
            case R.id.btn_scroll_change_left:// 改变为向左滑动
                autoPlayViewPage.setDirection(AutoPlayViewPager.Direction.LEFT);
                break;
            case R.id.btn_scroll_change_right:// 改变为向右滑动
                autoPlayViewPage.setDirection(AutoPlayViewPager.Direction.RIGHT);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setImageBackground(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setImageBackground(int selectItems) {
        selectItems=selectItems % resIds.size();
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.mipmap.select);
            } else {
                tips[i].setBackgroundResource(R.mipmap.un_select);
            }
        }
    }
}
