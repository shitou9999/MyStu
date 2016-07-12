package com.stu.com.mystu1;

/**
 * Created by Administrator on 2016/7/6.
 * APP配置类
 */
public class MyConfig {
    /**
     * 记录是否自动登录APP
     */
    public static String AUTO_LOGIN_WHRN_LAUNCH = "auto_login";
    public static String REMEMBER_PASSWORD = "remember_pws";
    public static String TOKEN = "token";
    public static String USERINFO = "user_info";
    public static String USERNAME = "name";
    public static String PASSWORD = "password";
    public static String ISNOTFIRSTFABU = "firstfabu";
    /**
     * 是否开启打印输出 true 开启打印输出 false 关闭打印输出,发布设置为flase
     */
    public static final boolean isDebug =true;
    /*发布设置为true*/
    public static final boolean isLine =false;

    /**
     * 获取服务器
     */
    public static final String getServer(){
        String serverIp=isLine? "http://api.cunli.zhanyaa.com" : "http://121.41.117.249:8090";
        return serverIp;
    }

}
