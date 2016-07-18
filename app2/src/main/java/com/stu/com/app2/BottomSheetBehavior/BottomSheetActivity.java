package com.stu.com.app2.BottomSheetBehavior;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.stu.com.app2.R;

/**
 * BottomSheet学习
 */
public class BottomSheetActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnBehavior,btnDialog;
    private BottomSheetBehavior<View> mBehavior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        btnBehavior=(Button) findViewById(R.id.btnBehavior);
        btnDialog=(Button) findViewById(R.id.btnDialog);
        btnBehavior.setOnClickListener(this);
        btnDialog.setOnClickListener(this);

        View bottomSheet =findViewById(R.id.bottom_sheet);
        if(bottomSheet!=null){
            mBehavior=BottomSheetBehavior.from(bottomSheet);
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED); //折叠的   设置初始状态
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBehavior:
                int state=mBehavior.getState();
                if (state == BottomSheetBehavior.STATE_EXPANDED) {
                    mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }else{
                    mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
//                if(state==BottomSheetBehavior.STATE_EXPANDED){ //展开的
//                    mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//                }else if(state==BottomSheetBehavior.STATE_COLLAPSED){ //折叠的
//                    mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN); //隐藏
//                }else if(state == BottomSheetBehavior.STATE_HIDDEN){
//                    mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//                }

                break;
            case R.id.btnDialog:
                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this);
                bottomSheetDialog.setContentView(R.layout.include_bottom_sheet_layout);
//                bottomSheetDialog.setCanceledOnTouchOutside(true);
                bottomSheetDialog.show();
                break;
        }
    }

//    BottomSheetCallback回调接口，状态改变时做的操作！！！！！！！！
//BottomSheet 使用需要CoordinatorLayout作为父布局
    /**
     * 当bottom sheets状态被改变回调
     * @param bottomSheet The bottom sheet view.
     * @param newState    改变后新的状态
     */
//    @Override
//    public void onStateChanged(@NonNull View bottomSheet, int newState) {
//           newState == BottomSheetBehavior.STATE_EXPANDED
//    }
    /**
     * 当bottom sheets拖拽时回调
     * @param bottomSheet The bottom sheet view.
     * @param slideOffset 滑动量;从0到1时向上移动
     */
//    @Override
//    public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//        |setPeekHeight|偷看的高度；哈，这么理解，就是默认显示后View露头的高度|
//        |getPeekHeight|@see setPeekHeight()|
//        |setHideable|设置是否可以隐藏，如果为true,表示状态可以为STATE_HIDDEN|
//        |isHideable|@see setHideable()|
//        |setState|设置状态;设置不同的状态会影响BottomSheetView的显示效果|
//        |getState|获取状态|
////        |setBottomSheetCallback|设置状态改变回调|
//    STATE_COLLAPSED： 默认的折叠状态， bottom sheets只在底部显示一部分布局。显示高度可以通过 app:behavior_peekHeight 设置（默认是0）
//    STATE_DRAGGING ： 过渡状态，此时用户正在向上或者向下拖动bottom sheet
//    STATE_SETTLING: 视图从脱离手指自由滑动到最终停下的这一小段时间
//    STATE_EXPANDED： bottom sheet 处于完全展开的状态：当bottom sheet的高度低于CoordinatorLayout容器时，整个bottom sheet都可见；
//    或者CoordinatorLayout容器已经被bottom sheet填满。
//    STATE_HIDDEN ： 默认无此状态（可通过app:behavior_hideable 启用此状态），启用后用户将能通过向下滑动完全隐藏 bottom sheet

//    app:behavior_hideable="false"说明这个BottomSheet不可以被手动滑动隐藏，设置为true则可以滑到屏幕最底部隐藏。

//    }


//    项目开发使用 BottomSheet 遇到设置 GridView 方法 setSelection 没有生效，需要在 Runnable 中执行才能生效。
//            list.post(new Runnable() {
//        @Override
//        public void run() {
//            try {
//                list.setSelection(10);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    });

}
