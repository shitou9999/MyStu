package com.stu.com.app2.toolbar;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.stu.com.app2.R;

/**
 * Created by Administrator on 2016/7/14.
 * 实现toobar渐变效果不好
 */
public class ToolbarAlphaBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    private static final String TAG="ToolbarAlphaBehavior";
    private int offset=0;
    private int startOffset=0;
    private int endOffet=0;
    private Context mContext;

    public ToolbarAlphaBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
    }


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        startOffset=0;
        endOffet=mContext.getResources().getDimensionPixelOffset(R.dimen.header_height)-child.getHeight();
        offset+=dyConsumed;
        if(offset<=startOffset){ //alpha为0
            child.getBackground().setAlpha(0);
        }else if(offset>startOffset&&offset<endOffet){//alpha为0到255
            float precent=(float) (offset-startOffset)/endOffet;
            int alpha=Math.round(precent*255);
            child.getBackground().setAlpha(alpha);
        }else if(offset>=endOffet){  //alpha为255
            child.getBackground().setAlpha(255);
        }





    }
}
