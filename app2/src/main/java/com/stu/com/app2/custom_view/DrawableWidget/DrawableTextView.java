package com.stu.com.app2.custom_view.DrawableWidget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

public class DrawableTextView extends TextView {
    private DrawableSizeHelper mHelper;

    public DrawableTextView(Context context) {
        super(context);
    }

    public DrawableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyAttributes(context, attrs);
    }

    public DrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyAttributes(context, attrs);
    }

    @TargetApi(21)
    public DrawableTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, DrawableSizeHelper helper) {
        super(context, attrs, defStyleAttr, defStyleRes);
        applyAttributes(context, attrs);
    }

    private void applyAttributes(Context context, AttributeSet attrs) {
        if(mHelper == null) {
            mHelper = new DrawableSizeHelper();
        }
        mHelper.readAttributes(context, attrs);
        if(!mHelper.isNotSet()) {
            mHelper.setCompoundDrawablesWithIntrinsicBounds(this);
        }
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        if(mHelper == null) {
            mHelper = new DrawableSizeHelper();
        }
        mHelper.setDrawable(left, top, right, bottom);
        if(!mHelper.isNotSet()) {
            mHelper.setCompoundDrawablesWithIntrinsicBounds(this);
        }
    }

    @Override
    @TargetApi(17)
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable start, Drawable top, Drawable end, Drawable bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(start, top, end, bottom);
        if (mHelper == null) {
            mHelper = new DrawableSizeHelper();
        }
        mHelper.setDrawable(start, top, end, bottom);
        if (!mHelper.isNotSet()) {
            mHelper.setCompoundDrawablesRelativeWithIntrinsicBounds(this);
        }
    }
}