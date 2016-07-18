package com.stu.com.app2.Scroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.stu.com.app2.R;

/**
 * Scroller学习
 */
public class ch01_ScrollerTestActitvty extends AppCompatActivity {

//    private Scroller mScroller;
//    Scroller 它自己封装了滚动时间，滚动的目标位置，以及在每个时间内View应该滚动到的（x , y ) 轴坐标点。
//    这样就可以在有效的滚动周期内通过 Scroller 的 getCurrX() 和 getCurrY() 来获取当前时刻View应该滚动的位置，
//    然后通过调用 View 的 scrollTo 或者 ScrollBy 方法进行滚动。
//getCurrX() 和getCurrY()可能有点不太好理解，从startScroll方法调用开始，它会在设定的滚动时间内（默认的250ms）持续滚动到目标位置，
//    在它没有抵达目标位置之前，它是一直在滚动的，getCurrX() 和 getCurrY() 方法获取的是调用时 Scroller 所滚动的位置。

//    我们只需要重写 View 类的 computeScroll 方法，该方法是在 View 绘制时被调用，
//    在里面调用 Scroller 的 computeScrollOffset 来判断滚动是否完成，
//    没完成则继续通过 scrollTo 或者 ScrollBy 进行滚动视图，然后调用目标 View 的 postInvalidate() 或者 invalidate()
//    进行 View 重绘，View 的重绘又导致 computeScroll 方法被调用。


    private ScrollLayout mScrollLayout;
    private ScrollerTextView scrollerTextView;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ch01__scroller_test_actitvty);
//        mScroller=new Scroller(this);

//        mScroller.getCurrX();//获取当前水平滚动的位置
//        mScroller.getCurrY();//获取当前竖直滚动的位置
//        mScroller.getFinalX();//最终停止的水平位置
//        mScroller.setFinalX(int newX); //设置mScroller最终停留的水平位置，没有动画效果，直接跳到目标位置

        //滚动，startX, startY为开始滚动的位置，dx,dy为滚动的偏移量(注意：是偏移量，不是最终坐标), duration为完成滚动的时间
//        mScroller.startScroll(); //内部默认执行时间250ms
//        mScroller.computeScrollOffset();
        //返回值为boolean，true说明滚动尚未完成，false说明滚动已经完成。这是一个很重要的方法，通常放在View.computeScroll()中，用来判断是否滚动是否结束。

        /*
        mScrollLayout=new ScrollLayout(this);
        TextView textView=new TextView(this);
        textView.setText("测试");
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(Color.BLUE);
        mScrollLayout.setBackgroundColor(Color.GRAY);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300);
        textView.setLayoutParams(layoutParams);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScrollLayout.scrollTo(-200);
            }
        });
        mScrollLayout.addView(textView);
        setContentView(mScrollLayout);
        */

        setContentView(R.layout.ch01__scroller_test_actitvty);
        scrollerTextView= (ScrollerTextView) findViewById(R.id.vip);
        scrollerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollerTextView.smoothScroolTo(-300,-500);//多次点击不会在发生偏移
//                scrollerTextView.smoothScrooBy(-300,-500);//每次点击都会发生移动
            }
        });


    }
}
