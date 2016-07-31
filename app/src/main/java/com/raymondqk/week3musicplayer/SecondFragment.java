package com.raymondqk.week3musicplayer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 陈其康 raymondchan on 2016/7/31 0031.
 */
public class SecondFragment extends Fragment {

    public static final String TAG = SecondFragment.class.getSimpleName();
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"Second-onDestroy");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"Second-onPause");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"Second-onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG,"Second-onCreateView");
        View view = inflater.inflate(R.layout.fragment_second,container,false);
        return view;
    }
}
