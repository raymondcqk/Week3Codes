package com.raymondqk.week3musicplayer;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by 陈其康 raymondchan on 2016/7/30 0030.
 */
public class MainActivity extends Activity {

    private MyFragment mMyFragment;
    private SecondFragment mSecondFragment;
    private Button mBtn_first;
    private Button mBtn_second;
    private View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_first:
                    //重新开启事务
                    mFt = mFg.beginTransaction();
                    mFt.hide(mSecondFragment);
                    mFt.show(mMyFragment);
                    mFt.commit();
                    break;
                case R.id.btn_second:
                    mFt = mFg.beginTransaction();
                    mFt.hide(mMyFragment);
                    mFt.show(mSecondFragment);
                    mFt.commit();
                    break;
            }
        }
    };
    private FragmentManager mFg;
    private FragmentTransaction mFt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFg = getFragmentManager();
        mFt = mFg.beginTransaction();

        mMyFragment = MyFragment.newInstance("不一般的实例化方式");
        mSecondFragment = new SecondFragment();
        mFt.add(R.id.container_fragment, mSecondFragment);
        mFt.add(R.id.container_fragment, mMyFragment);
        mFt.hide(mSecondFragment);
        mFt.commit();


        mBtn_first = (Button) findViewById(R.id.btn_first);
        mBtn_second = (Button) findViewById(R.id.btn_second);
        mBtn_first.setOnClickListener(mListener);
        mBtn_second.setOnClickListener(mListener);

    }
}
