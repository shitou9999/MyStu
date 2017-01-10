package com.study.audiorecord;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Chronometer.OnChronometerTickListener{

    private static int RECORD_REQUEST_CODE = 0;

    private static final int SUCCESS_RESULT_CODE = 1;
    private static final int FAIL_RESULT_CODE = 2;


    Button btToRecord;
    Chronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_record);
        btToRecord = (Button) findViewById(R.id.bt_toRecord);
        timer = (Chronometer) findViewById(R.id.timer);
        btToRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                startActivityForResult(intent, RECORD_REQUEST_CODE);
            }
        });

    }

    public void btnClick(View view) {
        timer.setBase(SystemClock.elapsedRealtime());//计时器清零
        timer.start();
    }

    //为计时器绑定监听事件
    @Override
    public void onChronometerTick(Chronometer chronometer) {
        // 如果从开始计时到现在超过了60s
        if (SystemClock.elapsedRealtime() - chronometer.getBase() > 60 * 1000) {
            timer.stop();
//            start.setEnabled(true);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECORD_REQUEST_CODE && resultCode == SUCCESS_RESULT_CODE) {
            Bundle datas = data.getExtras();
            String time = datas.getString("time");
            String filePath = datas.getString("filePath");
            Toast.makeText(this, "录音时间为：" + time + "s,\n录音路径为:" + filePath, Toast.LENGTH_LONG).show();
            btToRecord.setText("已录音，点击重新录音");
        }
        if (requestCode == RECORD_REQUEST_CODE && resultCode == FAIL_RESULT_CODE) {
            btToRecord.setText("未录音，点击录音");
        }
    }



}
