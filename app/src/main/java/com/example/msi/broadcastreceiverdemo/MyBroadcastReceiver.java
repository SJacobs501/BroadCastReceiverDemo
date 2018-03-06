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
 * Created by Simon Jacobs on 05/03/2018.
 * Handles the action(s) when an alarm is going off.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        
        // Make toast
        Toast.makeText(context, "Wake up!", Toast.LENGTH_LONG).show();
        System.out.println("Received broadcast.");
        
        // play ringtone
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        MediaPlayer thePlayer = MediaPlayer.create(context.getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        thePlayer.setVolume(10, 10);
        thePlayer.start();
        
        // Do something else

    }
}
