package com.stu.com.app2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.lzy.widget.tab.PagerSlidingTabStrip;
import com.stu.com.app2.fragment.TestFragment;
import com.stu.com.app2.fragment.UploadFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private List<Fragment> mFragmentList;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.tab)
    PagerSlidingTabStrip tab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFragmentList=new ArrayList<>();

        mFragmentList.add(new UploadFragment());
        mFragmentList.add(new TestFragment());

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tab.setViewPager(viewPager);

    }

    private class MyAdapter extends FragmentPagerAdapter{
        private String[] titles = {"一般请求", "下载管理", "上传管理"};

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}
