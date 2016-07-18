package com.stu.com.app2.Scroller;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by Administrator on 2016/7/15.
 */
public class ScrollLayout extends LinearLayout {

    private Scroller mScroller;
    public ScrollLayout(Context context) {
        super(context);
        mScroller=new Scroller(context);
    }

    /**
     * View重绘制的时候调用
     */
    @Override
    public void computeScroll() {
        if(mScroller.computeScrollOffset()){
            // 使View滚动到目前的位置
            this.scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            // 重绘View，从而导致 computeScroll()被调用，开启递归模式，直到scroller.computeScrollOffset()返回false。
            postInvalidate();
        }
    }

    public void scrollTo(int y){
        mScroller.startScroll(getScrollX(),getScrollY(),0,y);
        invalidate();
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
