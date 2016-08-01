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

