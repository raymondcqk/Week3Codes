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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"MyFragment---onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState) {
        Log.i(TAG,"MyFragment---onCreateView()");
        View view = inflater.inflate(R.layout.fragment_item,container,false);
        TextView tv = (TextView) view.findViewById(R.id.fg_text);
        Toast.makeText(getActivity(),tv.getText().toString(),Toast.LENGTH_SHORT).show();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
