package com.example.android.mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by prianca on 5/24/2017.
 */

public class BackgroundService extends Service implements MediaPlayer.OnCompletionListener,MediaPlayer.OnPreparedListener,MediaPlayer.OnErrorListener {

MediaPlayer mp;
    int count = 0;
    int maxCount;
    String LOG_TAG = "service";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
    mp = MediaPlayer.create(this,R.raw.krishna_chanting);
        Log.d(LOG_TAG,"oncreate");
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(count<maxCount){
                    Log.i(LOG_TAG,String.valueOf(count));
                    count++;
                    mp.seekTo(0);
                    mp.start();
                }
            }
        });
        mp.setOnErrorListener(this);
        mp.setOnPreparedListener(this);

    }

    @Override
    public void onDestroy() {
        if(mp.isPlaying()){
            mp.stop();
        }
        mp.release();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        String countString = bundle.getString("count");
        maxCount= Integer.parseInt(countString);
        Log.d(LOG_TAG,countString) ;
            if(!mp.isPlaying()){
                mp.start();
                //mp.setLooping(true);
            }
            return START_STICKY;
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.i("Error while playing","error");
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

        mp.start();

    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        stopSelf();
        Log.d(LOG_TAG,"completion") ;

    }
}

