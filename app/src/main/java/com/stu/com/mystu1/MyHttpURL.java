package com.stu.com.mystu1;

/**
 * Created by Administrator on 2016/7/6.
 * 接口地址类
 */
public class MyHttpURL {
    /*接口地址*/
    private static String url = MyConfig.getServer();
    /*上传个人照片*/
    public static final String UPPHOTO = url + "/api/user/uploadUserImg.do";
}
