package com.raymondqk.week3musicplayer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by 陈其康 raymondchan on 2016/8/1 0001.
 */
public class MusicPlayerActivity extends Activity implements View.OnClickListener {

    private Button mBtn_start_service;
    private Button mBtn_stop_service;
    private Button mBtn_bind_service;
    private Button mBtn_unBind_service;
    private MusicService mMusicService;

    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MyBinder myBinder = (MusicService.MyBinder) service;
            mMusicService = myBinder.getService();
            if (mMusicService != null) {
                Log.i(MusicService.TAG, "Activity onServiceConnected -- 成功连接服务 MusicService引用获取成功");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    private Button mBtn_get_time;
    private Intent mIntentForMusicService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        mBtn_start_service = (Button) findViewById(R.id.btn_start_service);
        mBtn_stop_service = (Button) findViewById(R.id.btn_stop_service);
        mBtn_bind_service = (Button) findViewById(R.id.btn_bind_service);
        mBtn_unBind_service = (Button) findViewById(R.id.btn_unbind_service);
        mBtn_get_time = (Button) findViewById(R.id.btn_get_time);
        mBtn_bind_service.setOnClickListener(this);
        mBtn_start_service.setOnClickListener(this);
        mBtn_stop_service.setOnClickListener(this);
        mBtn_unBind_service.setOnClickListener(this);
        mBtn_get_time.setOnClickListener(this);
        mIntentForMusicService = new Intent(MusicPlayerActivity.this, MusicService.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service:
                startService(mIntentForMusicService);
                break;
            case R.id.btn_stop_service:
                stopService(mIntentForMusicService);
                break;
            case R.id.btn_bind_service:
                bindService(mIntentForMusicService, mServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service:
                if (mMusicService!=null){
                    unbindService(mServiceConnection);
                }else {
                    Toast.makeText(this, "Service未绑定", Toast.LENGTH_SHORT).show();
                }
//                unbindService(mServiceConnection);//发现问题，若服务未bind，就调用unbindService，会强退，提示Service not registered。怎么判断Service已启动
                //unbind之后，会调用Service的onDestroy，和stopService一样。
                break;
            case R.id.btn_get_time:
                if (mMusicService != null) {
                    mBtn_get_time.setText(mMusicService.getMusicTime() + "");
                } else {
                    Toast.makeText(this, "Service未绑定", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //        stopService(new Intent(MusicPlayerActivity.this,MusicService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(MusicService.TAG, "Activity Destroy");
        stopService(mIntentForMusicService);
        if (mMusicService!=null){
            unbindService(mServiceConnection);
        }
    }
}
