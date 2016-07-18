package com.stu.com.app2.MagicViewPager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stu.com.app2.R;

/**
 * https://github.com/hongyangAndroid/MagicViewPager/blob/master/app/src/main/java/com/zhy/magicviewpager/sample/MainActivity.java
 * 单个页面显示三个图片
 */
public class ViewPagerActivity extends AppCompatActivity {
    private ViewPager vip;
    int[] imgRes={R.mipmap.h01,R.mipmap.h02,R.mipmap.h03,R.mipmap.h04};
    private PagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch02_view_pager);
        vip=(ViewPager) findViewById(R.id.vip);
        vip.setPageMargin(30);
        vip.setOffscreenPageLimit(3);
        vip.setAdapter(mAdapter = new PagerAdapter() {
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView=new ImageView(ViewPagerActivity.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageResource(imgRes[position]);
                container.addView(imageView);
                return imageView;
            }

            @Override
            public int getCount() {
                return imgRes.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        });
//        vip.setPageTransformer(true, );
    }


}
