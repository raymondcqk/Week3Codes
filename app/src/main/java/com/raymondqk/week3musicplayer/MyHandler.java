package com.raymondqk.week3musicplayer;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.ref.WeakReference;

public  class MyHandler extends Handler {

    private  WeakReference<Activity> mActivityWeakReference = null;
    private  MyHandlerActivity mMyHandlerActivity;

    public MyHandler(Activity activity) {
        mActivityWeakReference = new WeakReference<Activity>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        mMyHandlerActivity = (MyHandlerActivity) mActivityWeakReference.get();

        switch (msg.what){
            case MyHandlerActivity.INT:
                Message message = Message.obtain();
                message.what = MyHandlerActivity.WHAT;
                message.arg1 = 20;
                message.obj = new Obj(30);
                sendMessageDelayed(message,2500);
                break;
            case MyHandlerActivity.WHAT:
                int arg1 = msg.arg1;
                Obj obj = (Obj) msg.obj;
                int obj_value = obj.getValue();
                if (arg1>0){
                    mMyHandlerActivity.getTextView_arg1().setText(String.valueOf(arg1));
                }else {
                    mMyHandlerActivity.getTextView_arg1().setText(R.string.arg1over);
                }
                if (obj_value>0){
                    mMyHandlerActivity.getTextView_obj().setText(String.valueOf(obj_value));
                }else {
                    mMyHandlerActivity.getTextView_obj().setText(R.string.obj_over);
                }

                if (arg1>0&&obj_value>0) {
                    msg = Message.obtain();
                    msg.what = MyHandlerActivity.WHAT;
                    msg.arg1 = --arg1;
                    obj.setValue(--obj_value);
                    msg.obj = obj;
                    sendMessageDelayed(msg, MyHandlerActivity.DELAY_MILLIS);
                }else if (obj_value>0){
                    msg = Message.obtain();
                    msg.what = MyHandlerActivity.WHAT;
                    obj.setValue(--obj_value);
                    msg.obj = obj;
                    sendMessageDelayed(msg, MyHandlerActivity.DELAY_MILLIS);
                }
                Log.i("Test","倒计时中...若不在出现则表示倒计时结束，不在进入handleMessage");
                break;

        }
    }
}
