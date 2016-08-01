package com.raymondqk.week3musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 陈其康 raymondchan on 2016/8/1 0001.
 */
public class MusicService extends Service {

    public static final String TAG = MusicService.class.getSimpleName();
    //音乐播放类
    private MediaPlayer mMediaPlayer;
    private MyBinder mMyBinder = new MyBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"onBind_服务绑定 MediaPlayer播放");
        mMediaPlayer.start();
        return mMyBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.stop();
        mMediaPlayer.release();
        Log.i(TAG,"onDestroy_服务停止 MediaPlayer停止");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mMediaPlayer.start();
        Log.i(TAG,"onStartCommand_服务启动 MediaPlayer播放");
        //默认当程序关闭后(未执行stopService()和unbindService()操作，直接关掉APP的情况)，服务随之被停止，当内存足够空闲，服务会重新启动
        // ---导致我们关闭APP后音乐会停一下，然后重头开始播放
//        return super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY; //该返回值表示App被销毁后（是整个Application，而不仅是Activity被销毁），服务也随之销毁并不会重新启动
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = MediaPlayer.create(this,R.raw.stillalive);
        Log.i(TAG,"onCreate_服务创建 MediaPlayer初始化");
    }

    public int getMusicTime(){
        return 50;
    }

    class MyBinder extends Binder {
        public MusicService getService(){
            //因为是内部类，所以要用MusicService.this --- 否则this指代该内部类实例
            return MusicService.this;
        }
    }
}
