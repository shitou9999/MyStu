package com.stu.com.app3.behavior;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

/**
 * Created by Administrator on 2016/7/11.
 * appan按钮的
 */
public class MyFabBehavior extends CoordinatorLayout.Behavior<View>{

    public MyFabBehavior(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    private float viewY;//控件距离coordinatorLayout底部的距离
    private boolean isAnimate;//动画是否在进行
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();

    //在嵌套滑动开始前回调
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        if(child.getVisibility()==View.VISIBLE&&viewY==0){
            //获取控件距离父布局（coordinatorLayout）底部距离
            viewY=coordinatorLayout.getHeight()-child.getY();
        }
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL)!=0; //判断是否是竖直滑动
    }

    //在嵌套滑动进行时，对象消费滚动距离前回调
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        //大于0是向上滚动 小于0是向下滚动
        if(dy>=0&&child.getVisibility()==View.VISIBLE&& !isAnimate){
            hide(child);
        }else if(dy<0&&child.getVisibility()==View.GONE&& !isAnimate){
            show(child);
        }
    }

    //隐藏时的动画
    private void hide(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(viewY).setInterpolator(INTERPOLATOR).setDuration(200);
//        ViewPropertyAnimator提供了一种简单的方式来为View的部分属性设置动画，使用一个单一的Animator对象。
//        它表现的更像 ObjectAnimator，因为他也要修改View对象的相应的属性值，但是当为多个属性同时设置动画时，它比 ObjectAnimator更高效
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isAnimate=true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.GONE);
                isAnimate=false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                show(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }
    //显示时的动画
    private void show(final View view) {
        ViewPropertyAnimator animator = view.animate().translationY(0).setInterpolator(INTERPOLATOR).setDuration(200);
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                view.setVisibility(View.VISIBLE);
                isAnimate=true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                isAnimate=false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                hide(view);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }


}
