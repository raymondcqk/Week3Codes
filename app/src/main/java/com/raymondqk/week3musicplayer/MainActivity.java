package com.raymondqk.week3musicplayer;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by 陈其康 raymondchan on 2016/7/30 0030.
 */
public class MainActivity extends Activity {

    private MyFragment mMyFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fg =  getFragmentManager();
        FragmentTransaction ft = fg.beginTransaction();

        mMyFragment = new MyFragment();
        ft.add(R.id.container_fragment, mMyFragment);
        ft.commit();

        Fragment fragment = fg.findFragmentById(R.id.myFragment);
        if (fragment instanceof MyFragment){
            //向下造型
            MyFragment myFragment = (MyFragment) fragment;
            myFragment.setText("通过FindFragmentById获得该fragment对象");
        }
    }
}
