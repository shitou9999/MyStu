package com.stu.com.mystu1.utils;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

public class SpannableUtils {
    /**     * 创建一个Spannable对象
     *
     * @param string
     * @return
     */
    public static Spannable createSpannable(String string) {
        Spannable WordtoSpan = new SpannableString(string);
        return WordtoSpan;
    }

    /**
     * FOR: textview.setText(WordtoSpan)
     * Textview文字设置俩种不同颜色
     *
     * @param context
     * @param string
     * @param tag           从哪个索引开始分割
     * @param colorFirstId
     * @param colorSecondId
     * @return
     */
    private static Spannable setTextColor(Context context, String string, int tag, int colorFirstId, int colorSecondId) {
        Spannable WordtoSpan = createSpannable(string);
        WordtoSpan.setSpan(new ForegroundColorSpan(context.getResources().getColor(colorFirstId)), 0, tag, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        WordtoSpan.setSpan(new ForegroundColorSpan(context.getResources().getColor(colorSecondId)), tag, string.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return WordtoSpan;
    }

    /**
     * 设置字符串中部分串的字体颜色
     *
     * @param sp
     * @param start
     * @param end
     * @param color
     */
    public static void setTextColor(Spannable sp, int color, int start, int end) {
        if (sp != null) {
            sp.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    /**
     * 生成变色文字
     * @author YOLANDA
     * @param msg 要变色的文字
     * @param color 颜色
     * @param start 开始的位置
     * @param end 结束的位置
     * @return
     */
    public static SpannableString getSpannableStringColor(CharSequence msg, int color, int start, int end){
        SpannableString builder = new SpannableString(msg);
        builder.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    /**
     * 设置字符串中部分串的字体大小
     *
     * @param sp
     * @param textSize
     * @param start
     * @param end
     */    
public static void setTextSize(Spannable sp, int textSize, int start, int end) {        if (sp != null) {
            //设置字体大小,第二个参数boolean dip，如果为true，表示前面的字体大小单位为dip，否则为像素，
            sp.setSpan(new AbsoluteSizeSpan(textSize, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    /**
     * 设置不同的样式
     *
     * @param sp
     * @param textStyle
     * @param start
     * @param end
     */
    public static void setTextStyle(Spannable sp, int textStyle, int start, int end) {
        if (sp != null) {
            sp.setSpan(new StyleSpan(textStyle), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    /**
     * FOR:
     * SpannableString spStr = new SpannableString(str);
     * SpannableUtils.setTextClickable(tv_1, spStr, start, end);
     * tv_1.setText(spStr);
     * 设置字符串的可点击部分
     *
     * @param textView
     * @param sp
     * @param start
     * @param end
     */
    public static void setTextClickable(TextView textView, Spannable sp, int start, int end) {
        if (sp != null) {
            sp.setSpan(new ClickableSpan() {
                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(Color.BLUE);       //设置文件颜色
                    ds.setUnderlineText(true);      //设置下划线
                }
                @Override
                public void onClick(View widget) {
                    // do somthing...
                }
            }, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setHighlightColor(Color.TRANSPARENT); //设置点击后的颜色为透明，否则会一直出现高亮            textView.setMovementMethod(LinkMovementMethod.getInstance());//开始响应点击事件
        }
    }

    /**
     * 设置中划线
     *
     * @param sp
     * @param start
     * @param end
     */
    public static void setTextMiddleLine(Spannable sp, int start, int end) {
        if (sp != null) {
            StrikethroughSpan stSpan = new StrikethroughSpan();  //设置删除线样式
            sp.setSpan(stSpan, start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }
    }
}