package com.stu.com.app2.MagicViewPager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created 2016/5/3. 好用！！
 * 　轮播器最重要的几个特点就是：自动滚动、手动滑动、滚动方向、每个Item显示时间。
 */
public class AutoPlayViewPager extends ViewPager {

    public AutoPlayViewPager(Context context) {
        super(context);
    }

    public AutoPlayViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 播放时间
     */
    private int showTime = 3 * 1000;
    /**
     * 滚动方向
     */
    private Direction direction = Direction.LEFT;

    /**
     * 设置播放时间，默认3秒
     *
     * @param showTimeMillis 毫秒
     */
    public void setShowTime(int showTimeMillis) {
        this.showTime = showTime;
    }

    /**
     * 设置滚动方向，默认向左滚动
     *
     * @param direction 方向
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * 开始
     */
    public void start() {
        stop();
        postDelayed(player, showTime);
    }

    /**
     * 停止
     */
    public void stop() {
        removeCallbacks(player);
    }

    /**
     * 播放上一个
     */
    public void previous() {
        if (direction == Direction.RIGHT) {
            play(Direction.LEFT);
        } else if (direction == Direction.LEFT) {
            play(Direction.RIGHT);
        }
    }

    /**
     * 播放下一个
     */
    public void next() {
        play(direction);
    }

    /**
     * 执行播放
     * @param direction 播放方向
     */
    private synchronized void play(Direction direction) {
        PagerAdapter pagerAdapter = getAdapter();
        if (pagerAdapter != null) {
            int count = pagerAdapter.getCount();
            int currentItem = getCurrentItem();   // ViewPager现在显示的第几个？
            switch (direction) {
                case LEFT:// 如是向左滚动的，currentItem+1播放下一个
                    currentItem++;
                    // 如果+到最后一个了，就到第一个
                    if (currentItem > count)
                        currentItem = 0;
                    break;
                case RIGHT:// 如是向右滚动的，currentItem-1播放上一个
                    currentItem--;
                    // 如果-到低一个了，就到最后一个
                    if (currentItem < 0)// 播放根据方向计算出来的position的item
                        currentItem = count;
                    break;
            }
            setCurrentItem(currentItem);
        }
        start();// 这就是当可以循环播放的重点，每次播放完成后，再次开启一个定时任务
    }

    /**
     * 播放器
     */
    private Runnable player = new Runnable() {
        @Override
        public void run() {
            play(direction);
        }
    };

    public enum Direction {
        /**
         * 向左行动，播放的应该是右边的
         */
        LEFT,

        /**
         * 向右行动，播放的应该是左边的
         */
        RIGHT
    }

    @Override  //是因为这个方法会在view被加载完成后调用
    protected void onFinishInflate() {
        super.onFinishInflate();
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {  //所以我们在手指滑动时停止player，手指松开的时候再次开启player
                if (state == SCROLL_STATE_IDLE)
                    start();
                else if (state == SCROLL_STATE_DRAGGING)
                    stop();
            }
        });
    }

}