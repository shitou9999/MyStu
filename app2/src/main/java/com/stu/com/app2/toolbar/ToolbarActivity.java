package com.stu.com.app2.toolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.stu.com.app2.R;

/**
 * Toobar透明度测试
 */
public class ToolbarActivity extends AppCompatActivity {
//    如果你只是简单白色变成灰色 很简单 很多方法可以做到 下面的方法是Tint进行着色
//    Drawable whiteIcon = DrawableCompat.wrap(whiteDrawable);
//    DrawableCompat.setTint(whiteIcon, grayColor);
//    但是如果你要类似alpha这种不停的不同程度进行渐变 可能需要ColorFilter进行argb各个元素进行操作
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
//        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("透明度测试");
//        setSupportActionBar(toolbar);
//        toolbar.getBackground().setAlpha(0);//初始化为0


        AlphaTitleScrollView scroll = (AlphaTitleScrollView) findViewById(R.id.scrollview);
        LinearLayout title = (LinearLayout) findViewById(R.id.title);
        ImageView head = (ImageView) findViewById(R.id.head);
        scroll.setTitleAndHead(title, head);
        title.getBackground().setAlpha(0);


    }
}
