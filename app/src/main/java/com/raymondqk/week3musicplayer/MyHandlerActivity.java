package com.raymondqk.week3musicplayer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by 陈其康 raymondchan on 2016/8/1 0001.
 */
public class MyHandlerActivity extends Activity {


    public static final int WHAT = 303;
    public static final int DELAY_MILLIS = 1000;
    public static final int INT = 1010;
    private TextView mTextView_arg1;
    private TextView mTextView_obj;
    private MyHandler mMyHandler = new MyHandler(this);


    public TextView getTextView_arg1() {
        return mTextView_arg1;
    }

    public TextView getTextView_obj() {
        return mTextView_obj;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        mTextView_arg1 = (TextView) findViewById(R.id.tv_handler);
        mTextView_obj = (TextView) findViewById(R.id.tv_handler_obj);

        Message msg = Message.obtain();
        msg.what = INT;
        mMyHandler.sendMessage(msg);



    }
}

class Obj{
    private int value = 0;

    public Obj(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

class MyHandler extends Handler{

    private WeakReference<Activity> mActivityWeakReference = null;

    public MyHandler(Activity activity) {
        mActivityWeakReference = new WeakReference<Activity>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        MyHandlerActivity myHandlerActivity = (MyHandlerActivity) mActivityWeakReference.get();

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
                    myHandlerActivity.getTextView_arg1().setText(String.valueOf(arg1));
                }else {
                    myHandlerActivity.getTextView_arg1().setText(R.string.arg1over);
                }
                if (obj_value>0){
                    myHandlerActivity.getTextView_obj().setText(String.valueOf(obj_value));
                }else {
                    myHandlerActivity.getTextView_obj().setText(R.string.obj_over);
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