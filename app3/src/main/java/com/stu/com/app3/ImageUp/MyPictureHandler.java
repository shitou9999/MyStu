package com.stu.com.app3.ImageUp;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2016/10/12.
 * 图片处理类 这里主要在线程中对图片进行大小和比例的缩小和图片上传
 */
public abstract class MyPictureHandler {
    private Context context;

    public MyPictureHandler(Context context) {
        this.context = context;
        //初始化其他参数
    }

    /**
     * 可以传参数
     */
    public void handlerPicture(){
        //判断有效性
        //开启线程处理照片
        Thread thread=new Thread(new MyRunnable());
        thread.start();
    }

    public class MyRunnable implements Runnable{

        public MyRunnable() {

        }

        @Override
        public void run() {
//            synchronized (obj) {
//                for (ImgBean bean : list) {
//                    if (Tools.StringHasContent(bean.getPath()) && !bean.getPath().contains("http")) {
//                        String path = BitmapTools.compressWidthAndHeight(bean.getPath(), width, height, saveFilePath);
//                        params.addBodyParameter("pic"+count, new File(path));
//                        System.out.println("params" + params);
//                        fileNameList.add(path);
//                        count++;
//                    }
//                }
            mHandler.sendEmptyMessage(0);
//            }
        }
    }


    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            uploadImg();
        }
    };

    private void uploadImg() {
        //网络请求-----回调
        //成功
        //失败

    }

    public abstract void onSuccess(String value);

    public abstract void onSuccess();

    public abstract void onFailure(String value);


}
