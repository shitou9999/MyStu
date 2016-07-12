package com.stu.com.mystu1;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2016/7/6.
 */
public class MyApplication extends Application {
    private static MyApplication instance;
//    private User mUser;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
//        if (MyConfig.isDebug) { //其他第三方的一些配置
//            UpdateConfig.setDebug(true);
//        }
    }

    public static MyApplication getInstance(){
        return instance;
    }

    //判断有无网络
    public static boolean checkNetWorkConnectionState(Context context){
        ConnectivityManager connMgr=(ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connMgr.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isConnected()){
            return true;
        }else {
            return false;
        }
    }

//    public User getUser() {
//        return mUser;
//    }
//    public void setUser(User user) {
//        mUser = user;
//    }

}
