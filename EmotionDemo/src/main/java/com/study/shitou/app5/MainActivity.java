package com.study.shitou.app5;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.study.shitou.app5.emotion.DisplayUtils;
import com.study.shitou.app5.emotion.EmotionGvAdapter;
import com.study.shitou.app5.emotion.EmotionPagerAdapter;
import com.study.shitou.app5.emotion.EmotionUtils;
import com.study.shitou.app5.emotion.ImageUtils;
import com.study.shitou.app5.emotion.StringUtils;
import com.study.shitou.app5.emotion.WriteStatusGridImgsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * a.表情内容的数据格式
 *表情看上去是图片,但是在数据传输的时候本质上是一个特殊文本
 *比如QQ表情就是一个 "/表情字母"的结构,比如害羞的表情就是/hx,呲牙就是/cy...
 *微博里表情就是"[表情名字]"的接口,比如可爱的表情就是[可爱]等等...
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    // 输入框
    private EditText et_write_status;
    // 表情选择面板
    private LinearLayout ll_emotion_dashboard;
    private ViewPager vp_emotion_dashboard;
    private WriteStatusGridImgsAdapter statusImgsAdapter;
    private ArrayList<Uri> imgUris = new ArrayList<Uri>();
    private EmotionPagerAdapter emotionPagerGvAdapter;

    private TextView text,text2;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=(TextView) findViewById(R.id.text);
        text2=(TextView) findViewById(R.id.text2);
        btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText(StringUtils.getWeiboContent(MainActivity.this,text,et_write_status.getText().toString()));
//                text.setText(et_write_status.getText());
            }
        });

        // 输入框
        et_write_status = (EditText) findViewById(R.id.et_write_status);
        // 表情选择面板
        ll_emotion_dashboard = (LinearLayout) findViewById(R.id.ll_emotion_dashboard);
        vp_emotion_dashboard = (ViewPager) findViewById(R.id.vp_emotion_dashboard);

        initEmotion();
        test();
    }


    private void test(){

        ObjectAnimator animator=ObjectAnimator.ofFloat(text2,"translationX",30f,200f,30f);
        ObjectAnimator animator1=ObjectAnimator.ofFloat(text2,"alpha",1f,0f,1f);
        ObjectAnimator animator2=ObjectAnimator.ofFloat(text2,"rotation",360);
        animator1.setRepeatMode(ValueAnimator.RESTART);
        AnimatorSet set=new AnimatorSet();
        set.play(animator2).with(animator1);
        set.setDuration(1000);
        set.start();
    }


    /**
     *  初始化表情面板内容
     */
//    为了让Item能大小合适,最好的方法是利用动态计算的方式设置宽高,因为屏幕宽度各有不同
//    其他的一些大小计算也要注意用dp值转换下获取对应的px再使用，保证适配问题

//            获取屏幕宽度,减去间隙距离,然后除以7算出每个Item需要的宽度,
//            然后再乘以3加上需要的间隙,算出表情面板的高度,利用算出来的值去创建设置面板大小

//    思路是
//    for循环全部的图片名字,这里利用map.keySet获取全部的键集合 每次循环都把图片名添加到一个集合中, 然后进行判断,如果满20个了就作为一组表情,新建一个GridView设置上去
//    最后把所有表情生成的一个个GridView放到一个总view集合中,利用ViewPager显示
//    c.GridView末尾删除键处理
//            适配器和点击事件中,都利用position判断,如果是最后一个就进行特殊的显示和点击处理
    private void initEmotion() {
        int screenWidth = DisplayUtils.getScreenWidthPixels(this);
        int spacing = DisplayUtils.dp2px(this, 8);
       //表情面板的宽和高
        int itemWidth = (screenWidth - spacing * 8) / 7;
        int gvHeight = itemWidth * 3 + spacing * 4;

        List<GridView> gvs = new ArrayList<GridView>();
        List<String> emotionNames = new ArrayList<String>();
//        b.需要多少个GridView
        for(String emojiName : EmotionUtils.emojiMap.keySet()) {
            emotionNames.add(emojiName);

            if(emotionNames.size() == 20) {
                GridView gv = createEmotionGridView(emotionNames, screenWidth, spacing, itemWidth, gvHeight);
                gvs.add(gv);

                emotionNames = new ArrayList<String>();
            }
        }

        if(emotionNames.size() > 0) {
            GridView gv = createEmotionGridView(emotionNames, screenWidth, spacing, itemWidth, gvHeight);
            gvs.add(gv);
        }

        emotionPagerGvAdapter = new EmotionPagerAdapter(gvs);//viewPager适配器
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(screenWidth, gvHeight);
        vp_emotion_dashboard.setLayoutParams(params);
        vp_emotion_dashboard.setAdapter(emotionPagerGvAdapter);
    }

    /**
     * 创建显示表情的GridView
     */
    private GridView createEmotionGridView(List<String> emotionNames, int gvWidth, int padding, int itemWidth, int gvHeight) {
        GridView gv = new GridView(this);
        gv.setBackgroundResource(R.color.bg_gray);
        gv.setSelector(R.color.transparent);
        gv.setNumColumns(7);
        gv.setPadding(padding, padding, padding, padding);
        gv.setHorizontalSpacing(padding);
        gv.setVerticalSpacing(padding);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(gvWidth, gvHeight);
        gv.setLayoutParams(params);

        EmotionGvAdapter adapter = new EmotionGvAdapter(this, emotionNames, itemWidth);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(this);

        return gv;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Object itemAdapter = parent.getAdapter();
        if(itemAdapter instanceof WriteStatusGridImgsAdapter) {
            if(position == statusImgsAdapter.getCount() - 1) {
                ImageUtils.showImagePickDialog(this);
            }
        } else if(itemAdapter instanceof EmotionGvAdapter) {
            EmotionGvAdapter emotionAdapter = (EmotionGvAdapter) itemAdapter;

            if(position == emotionAdapter.getCount() - 1) {
                //删除就比较简单了,这里直接调用系统的 Delete 按钮事件即可
                et_write_status.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
            } else {
                String emotionName = emotionAdapter.getItem(position);

                int curPosition = et_write_status.getSelectionStart();//光标的位置获取
                StringBuilder sb = new StringBuilder(et_write_status.getText().toString());
                sb.insert(curPosition, emotionName);

                SpannableString weiboContent = StringUtils.getWeiboContent(this, et_write_status, sb.toString());
                et_write_status.setText(weiboContent);

                et_write_status.setSelection(curPosition + emotionName.length());//光标应该更新到新添加内容后面,而设置光标位置
            }

        }
    }
}
