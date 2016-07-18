package com.stu.com.app2.toolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class AlphaTitleScrollView extends ScrollView {
    public static final String TAG = "AlphaTitleScrollView";
    private int mSlop;
    private LinearLayout toolbar;
    private ImageView headView;

    public AlphaTitleScrollView(Context context, AttributeSet attrs,int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public AlphaTitleScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public AlphaTitleScrollView(Context context) {
        this(context, null);
    }

    private void init(Context context) {
        // mSlop = ViewConfiguration.get(context).getScaledDoubleTapSlop();
        mSlop = 10; //这个主要是为了到达这个值的时候直接设置为全透明了，免得还要使劲滑到0才设置透明度的话，那样体验就不好了，给个缓冲值会更好点。
        Log.i(TAG, mSlop + "");
    }

    /**
     * @param
     * @param
     */
    public void setTitleAndHead(LinearLayout toolbar, ImageView headView) {
        this.toolbar = toolbar;
        this.headView = headView;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        float headHeight = headView.getMeasuredHeight() - toolbar.getMeasuredHeight();
        int alpha = (int) (((float) t / headHeight) * 255);
        if (alpha >= 255)
            alpha = 255;
        if (alpha <= mSlop)
            alpha = 0;
        toolbar.getBackground().setAlpha(alpha);

        super.onScrollChanged(l, t, oldl, oldt);
    }
}