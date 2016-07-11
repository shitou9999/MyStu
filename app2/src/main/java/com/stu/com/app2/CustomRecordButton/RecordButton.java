package com.stu.com.app2.CustomRecordButton;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.widget.Button;
import java.lang.ref.WeakReference;

    /**
     * 录音专用Button，可弹出自定义的录音dialog。需要配合{@link #}使用
     * @author kymjs(kymjs123@gmail.com)
     * Android仿微信录音功能
     */


    public class RecordButton extends Button {
        private static final int MIN_INTERVAL_TIME = 700; // 录音最短时间
        private static final int MAX_INTERVAL_TIME = 60000; // 录音最长时间
        private RecordButtonUtil mAudioUtil;
        private Handler mVolumeHandler; // 用于更新录音音量大小的图片
        private Dialog mRecordDialog;

        public RecordButton(Context context) {
            super(context);
            mVolumeHandler = new ShowVolumeHandler(this);
            mAudioUtil = new RecordButtonUtil();
            initSavePath();
        }

    private void initSavePath() {

    }

    @Override
        public boolean onTouchEvent(MotionEvent event) {
//            if (mAudioFile == null) {
//                return false;
//            }
//            switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                initlization();
//                break;
//            case MotionEvent.ACTION_UP:
//                if (event.getY() < -50) {
//                    cancelRecord();
//                } else {
//                    finishRecord();
//                }
//                break;
//            case MotionEvent.ACTION_MOVE:
//               //做一些UI提示
//                break;
//            }
            return true;
        }

        /** 初始化 dialog和录音器 */
        private long mStartTime;
        private void initlization() {
            mStartTime = System.currentTimeMillis();
            if (mRecordDialog == null) {
                mRecordDialog = new Dialog(getContext());
                mRecordDialog.setOnDismissListener(onDismiss);
            }
            mRecordDialog.show();
            startRecording();
        }

        /** 录音完成（达到最长时间或用户决定录音完成） */
        private void finishRecord() {
//            stopRecording();
//            mRecordDialog.dismiss();
//            long intervalTime = System.currentTimeMillis() - mStartTime;
//            if (intervalTime < MIN_INTERVAL_TIME) {
////                AppContext.showToastShort("时间太短");
//                File file = new File(mAudioFile);
//                file.delete();
//                return;
//            }
//            if (mFinishedListerer != null) {
//                mFinishedListerer.onFinishedRecord(mAudioFile,(int) ((System.currentTimeMillis() - mStartTime) / 1000));
//            }
        }
        // 用户手动取消录音
        private void cancelRecord() {
//            stopRecording();
//            mRecordDialog.dismiss();
//            File file = new File(mAudioFile);
//            file.delete();
//            if (mFinishedListerer != null) {
//                mFinishedListerer.onCancleRecord();
//            }
        }

        // 开始录音
    private ObtainDecibelThread mThread;
        private void startRecording() {
//            mAudioUtil.setAudioPath(mAudioFile);
//            mAudioUtil.recordAudio();
//            mThread = new ObtainDecibelThread();
//            mThread.start();

        }
        // 停止录音
        private void stopRecording() {
            if (mThread != null) {
                mThread.exit();
                mThread = null;
            }
            if (mAudioUtil != null) {
                mAudioUtil.stopRecord();
            }
        }

        /******************************* inner class ****************************************/
        private class ObtainDecibelThread extends Thread {
            private volatile boolean running = true;

            public void exit() {
                running = false;
            }
            @Override
            public void run() {
                while (running) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (System.currentTimeMillis() - mStartTime >= MAX_INTERVAL_TIME) {
                        // 如果超过最长录音时间
                        mVolumeHandler.sendEmptyMessage(-1);
                    }
                    if (mAudioUtil != null && running) {
                        // 如果用户仍在录音
                        int volumn = mAudioUtil.getVolumn();
                        if (volumn != 0)
                            mVolumeHandler.sendEmptyMessage(volumn);
                    } else {
                        exit();
                    }
                }
            }
        }
        private final DialogInterface.OnDismissListener onDismiss = new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                stopRecording();
            }
        };

        static class ShowVolumeHandler extends Handler {
            private final WeakReference<RecordButton> mOuterInstance;
            public ShowVolumeHandler(RecordButton outer) {
                mOuterInstance = new WeakReference<RecordButton>(outer);
            }
            @Override
            public void handleMessage(Message msg) {
//                RecordButton outerButton = mOuterInstance.get();
//                if (msg.what != -1) {
//                    // 大于0时 表示当前录音的音量
//                    if (outerButton.mVolumeListener != null) {
//                        outerButton.mVolumeListener.onVolumeChange(mRecordDialog,msg.what);
//                    }
//                } else {
//                    // -1 时表示录音超时
//                    outerButton.finishRecord();
//                }
            }
        }

        /** 音量改变的监听器 */
        private OnVolumeChangeListener mVolumeListener;////////////

        public interface OnVolumeChangeListener {
            void onVolumeChange(Dialog dialog, int volume);
        }
        private OnFinishedRecordListener mFinishedListerer;////////////////

        public interface OnFinishedRecordListener {
            /** 用户手动取消 */
            public void onCancleRecord();
            /** 录音完成 */
            public void onFinishedRecord(String audioPath, int recordTime);
        }

        public void setFinishedListerer(OnFinishedRecordListener finishedListerer) {
            mFinishedListerer = finishedListerer;
        }

        public void setVolumeListener(OnVolumeChangeListener volumeListener) {
            mVolumeListener = volumeListener;
        }

    }