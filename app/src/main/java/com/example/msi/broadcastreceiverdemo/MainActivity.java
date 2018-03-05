package com.example.msi.broadcastreceiverdemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent intent = new Intent();
        //intent.setAction("com.example.broadcast.MY_NOTIFICATION");
       // intent.putExtra("data","Notice me senpai!");
        //sendBroadcast(intent);


        Button buttonSet =  findViewById(R.id.buttonSet);
        buttonSet.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {



                // for Alarm 25/12/2012 at 12.00
                // for alarm 5-3-2018 at 14:55
                //Calendar myAlarmDate = Calendar.getInstance();
                Calendar myAlarmDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                myAlarmDate.setTimeInMillis(System.currentTimeMillis());

                // month 2 = march
                // hourofday 14 = 15:00
                // rest is normal

                //myAlarmDate.set(2018, 2, 5, 14, 16, 0);
                DatePicker datePicker = findViewById(R.id.datePicker);
                TimePicker timePicker = findViewById(R.id.timePicker);

                int hour;
                int minute;
                if(Build.VERSION.SDK_INT < 23){
                    hour = timePicker.getCurrentHour();
                    minute = timePicker.getCurrentMinute();
                } else{
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();
                }

                myAlarmDate.set(datePicker.getYear(),
                                datePicker.getMonth(),
                                datePicker.getDayOfMonth(),
                                hour - 1,
                                minute);

                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent _myIntent = new Intent(MainActivity.this, MyBroadcastReceiver.class);
                _myIntent.putExtra("MyMessage","HERE I AM PASSING THEPERTICULAR MESSAGE WHICH SHOULD BE SHOW ON RECEIVER OF ALARM");
                PendingIntent _myPendingIntent = PendingIntent.getBroadcast(MainActivity.this, 123, _myIntent, PendingIntent.FLAG_UPDATE_CURRENT);


                // USE TIME
                alarmManager.set(AlarmManager.RTC_WAKEUP, myAlarmDate.getTimeInMillis(),_myPendingIntent);
                // USE 10 seconds.
                //alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+20000,_myPendingIntent);



                System.out.println("Now: " + Calendar.getInstance().getTime().toString());
                System.out.println("Alarm: "+myAlarmDate.getTime().toString());

                String log = "Alarm set for " + myAlarmDate.getTime().toString();
                Log.d("?", log);
                Toast.makeText(getApplicationContext(), log, Toast.LENGTH_LONG).show();

                TextView tv = findViewById(R.id.textView);
                tv.setText("Alarm set");

                /*
                System.out.println("Button clicked");
                int time = 8000;
                Intent intent = new Intent(MainActivity.this, MyBroadcastReceiver.class);
                intent.setAction("com.example.broadcast.MY_NOTIFICATION");
                PendingIntent pIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);

                AlarmManager mgr = (AlarmManager) getSystemService(ALARM_SERVICE);
                mgr.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+time+1000, pIntent);
                */





            }
        });
    }
}
