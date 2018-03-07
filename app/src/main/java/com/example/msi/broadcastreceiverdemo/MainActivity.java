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

        Button buttonSet =  findViewById(R.id.buttonSet);
        buttonSet.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

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
                Intent intent = new Intent(MainActivity.this, MyBroadcastReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 123, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.set(AlarmManager.RTC_WAKEUP, myAlarmDate.getTimeInMillis(),pendingIntent);

                System.out.println("Now: " + Calendar.getInstance().getTime().toString());
                System.out.println("Alarm: "+myAlarmDate.getTime().toString());

                String toastText = "Alarm set for " + myAlarmDate.getTime().toString();
                Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_LONG).show();

                TextView tv = findViewById(R.id.textView);
                tv.setText("Alarm set");
            }
        });
    }
}
