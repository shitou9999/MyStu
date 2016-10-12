package com.stu.com.app3.ImageUp;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.stu.com.app3.R;

public class ImageUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_up);
        initViews();
        initPhoneImage();
    }

    /**
     * 初始化数据
     */
    private void initViews() {
        initEvent();
    }

    /**
     * 点击事件
     */
    private void initEvent() {
        //上传照片操作
        //1.处理照片
        upLoadServer();
    }

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
     */
    private void initPhoneImage() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                //扫描操作
                //扫描完成通知
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //接收通知干其他事

        }
    };

    /**
     * 处理照片
     */
    private void upLoadServer() {
        MyPictureHandler myHandler=new MyPictureHandler(this){

            @Override
            public void onSuccess(String value) {

            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(String value) {

            }
        };
        /**
         * 调用方法处理图片然后回调
         */
        myHandler.handlerPicture();

    }

}
