package com.stu.com.app2.Scroller;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Scroller;
import android.widget.TextView;

/**
 * TextView实现移动
 * View的Scrooler
 */
public class ScrollerTextView extends TextView {

    private Scroller mScroller;
    public ScrollerTextView(Context context) {
        super(context);
        initScroller(context);
    }

    public ScrollerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initScroller(context);
    }

    public ScrollerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initScroller(context);
    }

    private void initScroller(Context context){
        mScroller=new Scroller(context);
    }

    /**
     * 外部调用实现滚动到目标位置
     */
    public void smoothScroolTo(int fx,int fy){
        int dx=fx-mScroller.getFinalX();
        int dy=fy-mScroller.getFinalY();
        smoothScrooBy(dx,dy);
    }

    public void smoothScrooBy(int dx,int dy){
        //设置mScrooler滚动偏移量
//        mScroller.startScroll(mScroller.getFinalX(),mScroller.getFinalY(),dx,dy,4000);
        mScroller.startScroll(getScrollX(),getScrollY(),dx,dy,4000);

        invalidate();//这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    }

    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){  //true说明滚动尚未完成，false说明滚动已经完成
            scrollTo(mScroller.getCurrX(),mScroller.getCurrY());

            postInvalidate();//这个比较重要，重绘！！！！
        }
        super.computeScroll();
    }

}












