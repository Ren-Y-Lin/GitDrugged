package com.example.gitdrugged;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import static com.example.gitdrugged.App.CHANNEL_1_ID;

public class MainActivity extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    public static String pid = "";
    public static boolean exist = false;
    public static ArrayList<UserObject> user = new ArrayList<UserObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManager = NotificationManagerCompat.from(this);


    }
    public void returnToMain(View view){
        setContentView(R.layout.activity_main);
    }

    public void moveToDoctorView(View view){

        setContentView(R.layout.activity_main_doc);
    }

    public void moveToCalender(View view){
        setContentView(R.layout.calender);
    }

    public void sendOnChannel1(View v) {
        String title = "Git Drugged reminder";
        String message = "Let's do drugs now!";

        Intent activityIntent = new Intent(this, ReminderReturn.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0);
        Intent broadcastIntent = new Intent(this, NotifReceiver.class);
        broadcastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.gitdruggedlogo)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.parseColor("#FFA31A"))
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(1, notification);

        AlarmManager alarmManager = (AlarmManager) v.getContext().getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*6 , contentIntent);

    }


    public String getData(){

        return "drug1,d,14,drug2,w,2";

    }

    public void onAddClicked(android.view.View view) {
        Intent intent = new Intent(this, AddPatientActivity.class);
        startActivity(intent);
    }

    public void onManageClicked(android.view.View view) {
        Intent intent = new Intent(this, ManagePatientActivity.class);
        startActivity(intent);
    }

    public void onUserClicked(android.view.View view) {
        Intent intent = new Intent(this, CheckUserActivity.class);
        startActivity(intent);
    }

    public static void rick() {
        exist = true;
    }

    public static void rickrick() {
        exist = true;
    }

    public static void obama() {
        exist = true;
    }
}
