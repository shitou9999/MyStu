package com.stu.com.mystu1.Gridview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

import com.stu.com.mystu1.R;

/**
 * 靠边的格子都是半封闭的，要实现这种效果仅仅靠美工给图片恐怕不行。反编译zaker的代码，虽然看不到整个代码，但是从中可以摸索出zaker是重写了gridview的dispatchDraw实现的
 * 在dispatchDraw方法中，我们对每一个子view的边界按照一定的方式绘上了边框，一般一个格子只需绘制其中两条边，
 * 需要注意的是最边上的格子需要特殊处理。super.dispatchDraw(canvas);一定要调用，不然格子中的内容（子view）就得不到绘制的机会
 * 仔细看代码你会发现这个实现方式是很好的，但是代码并不完美，因为每条线的绘制我们都是以第一个子view 的宽高为基准的，如果某个格子的高度和第一个格子不一致，那么可能出现错位。
 * 如果你能确保每个格子大小均匀，直接拿来用，否则还需要些修改。这里是重写的dispatchDraw，
 * 其实我我们重写onDraw方法也可以得到相同的结果。要完全弄明白的话，就得看看FrameWork中GridView的源码了。
 */

public class LineGridView extends GridView {
    public LineGridView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    public LineGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public LineGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void dispatchDraw(Canvas canvas){
        super.dispatchDraw(canvas);
        View localView1 = getChildAt(0);
        int column = getWidth() / localView1.getWidth();
        int childCount = getChildCount();
        Paint localPaint;
        localPaint = new Paint();
        localPaint.setStyle(Paint.Style.STROKE);
        localPaint.setStrokeWidth((float) 3.0);
        localPaint.setColor(getContext().getResources().getColor(R.color.indigo));
        for(int i = 0;i < childCount;i++){
            View cellView = getChildAt(i);
            if((i + 1) % column == 0){
                canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
            }else if((i + 1) > (childCount - (childCount % column))){
                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
            }else{
                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
                canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
            }
        }
        if(childCount % column != 0){
            for(int j = 0 ;j < (column-childCount % column) ; j++){
                View lastView = getChildAt(childCount - 1);
                canvas.drawLine(lastView.getRight() + lastView.getWidth() * j, lastView.getTop(), lastView.getRight() + lastView.getWidth()* j, lastView.getBottom(), localPaint);
            }
        }
    }
}