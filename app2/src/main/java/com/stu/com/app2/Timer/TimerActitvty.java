package com.stu.com.app2.Timer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.stu.com.app2.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器测试
 */
public class TimerActitvty extends AppCompatActivity implements View.OnClickListener{
    private TextView title;
    private TextView titles;
    private Button bt1;
    private Button bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_actitvty);
        title=(TextView) findViewById(R.id.title);
        titles=(TextView) findViewById(R.id.titles);
        bt1= (Button) findViewById(R.id.bt1);
        bt2= (Button) findViewById(R.id.bt2);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);

//        其中Thread类代表线程类， run()，包含线程运行时所执行的代码;Start()，用于启动线程）
        Thread mThread=new Thread(new Runnable() {
            @Override
            public void run() {
                Message message=new Message();
                message.what=1;
                mHandler.sendMessage(message);
            }
        });
        mThread.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt1:
                Timer timer=new Timer();
                timer.schedule(new TimerTaskTest(),1000,1000);
                break;
            case R.id.bt2:
                mHandler.post(progress);
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//
//                    }
//                },1000);
                break;
        }
    }
    /**
     * 执行的任务  多次使用要判!=null
     */
    private int time=60;
    public class TimerTaskTest extends TimerTask{

        @Override
        public void run() {  //在run方法里发送消息
            if(time==1){
                mHandler.sendEmptyMessage(0);
                time=60;
                this.cancel();
            }else{
                time--;
                mHandler.sendEmptyMessage(1);
            }
        }
    }
//    Handler 机制，它是Runnable和Activity交互的桥梁，在run方法中发送Message，在Handler里，通过不同的Message执行不同的任务。
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    title.setText("哈哈哈");
                    break;
                case 1:
                    title.setText("重新获取" + "(" + time + ")");
                    break;
                case 2:
                    titles.setText("成功"+time);
                    break;
                case 3:
                    titles.setText("测试中" + time );
                    break;
            }
        }
    };
    /****************************************************************************************************/
    private Runnable progress=new Runnable() {
        @Override
        public void run() {
            if(time==1){
                time+=1;
                mHandler.postDelayed(progress,1000);
                mHandler.sendEmptyMessage(2);
            }else{
                time-=1;
                mHandler.postDelayed(progress,1000);
                mHandler.sendEmptyMessage(3);
                //达到一定程度取消子线程，这里没有取消
            }
        }
    };
    /****************************************************************************************************/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(progress);
    }
}













