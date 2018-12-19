package robfernandes.xyz.mynews.Controller.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Switch;

import java.util.Calendar;

import robfernandes.xyz.mynews.Model.DataManager;
import robfernandes.xyz.mynews.R;
import robfernandes.xyz.mynews.Utils.Constants;

public class NotificationsActivity extends AppCompatActivity {

    private DataManager mDataManager;
    private boolean isNotificationsActive;
    private Switch notificationsSwitch;
    private static final String TAG = "NotificationsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        mDataManager = new DataManager(NotificationsActivity.this);
        isNotificationsActive = mDataManager.readNotificationsStatusFromMemory();
        notificationsSwitch = findViewById(R.id.activity_notifications_switch);
        notificationsSwitch.setChecked(isNotificationsActive);

        changeAlarmManagerStatus();
        notificationsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isNotificationsActive = isChecked;
            mDataManager.saveNotificationsStatusInMemory(isNotificationsActive);
            changeAlarmManagerStatus();
        });
    }

    private void changeAlarmManagerStatus() {
        if (isNotificationsActive) {
            startAlarmManager();
        } else {
            cancelAlarmManager();
        }
    }


    private void startAlarmManager() {
        Log.d(TAG, "startAlarmManager: ");
        Calendar notificationTime = Calendar.getInstance(); //gets right now
        notificationTime.set(Calendar.HOUR_OF_DAY, Constants.HOUR_NOTIFICATION);
        notificationTime.set(Calendar.MINUTE, Constants.MINUTES_NOTIFICATION);
        notificationTime.add(Calendar.DATE, 1);    //tomorrow

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), AlarmManagerReceiver.class);
        PendingIntent pendingIntent = PendingIntent
                .getBroadcast(getApplicationContext(), 0, intent, 0);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis()+1000,
                5000, pendingIntent); //just for test


/*        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP
                , notificationTime.getTimeInMillis()
                , AlarmManager.INTERVAL_DAY
                , pendingIntent);*/
    }

    private void cancelAlarmManager() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), AlarmManagerReceiver.class);
        PendingIntent pendingIntent = PendingIntent
                .getBroadcast(getApplicationContext(), 0, intent, 0);

        alarmManager.cancel(pendingIntent);
        Log.d(TAG, "cancelAlarmManager: ");
    }

}
