package com.raymondqk.week3musicplayer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 陈其康 raymondchan on 2016/7/31 0031.
 */
public class MyFragment extends Fragment {

    public static final String TAG = MyFragment.class.getSimpleName();
    private View mView;
    private TextView mTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"MyFragment---onCreate()");
        //执行顺序 1
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
        //创建并管理Fragment的视图，通过LayoutInflater解析fragment_item.xml得到一个View
        // activity将该view呈现到屏幕上
        Log.i(TAG,"MyFragment---onCreateView()");
        mView = inflater.inflate(R.layout.fragment_item,container,false);
        mTv = (TextView) mView.findViewById(R.id.fg_text);
        Toast.makeText(getActivity(), mTv.getText().toString(),Toast.LENGTH_SHORT).show();
        return mView;
        //执行顺序 2
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG,"MyFragment---onPause() ");
        //宿主activity被遮挡或退出时先执行
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"MyFragment---onDestroy() ");
        //Activity退出时，在onPause后执行
    }

    public void setText(String s) {
        mTv.setText(s);
    }
}
