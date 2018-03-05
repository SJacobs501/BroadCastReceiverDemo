package com.example.msi.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by MSI on 05/03/2018.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        //StringBuilder sb = new StringBuilder();
        //sb.append("Action: " + intent.getAction() + "\n");
        //sb.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
        //String log = sb.toString();

        String log = "Yoo";
        Log.d(TAG, log);
        Toast.makeText(context, log, Toast.LENGTH_LONG).show();
        System.out.println("IT WORKS!");



        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        MediaPlayer thePlayer = MediaPlayer.create(context.getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        thePlayer.setVolume(10, 10);
        thePlayer.start();


    }
}
