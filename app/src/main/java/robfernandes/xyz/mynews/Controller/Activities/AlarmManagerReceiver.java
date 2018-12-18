package robfernandes.xyz.mynews.Controller.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import robfernandes.xyz.mynews.Model.Notifications;

/**
 * Created by Roberto Fernandes on 18/12/2018.
 */
public class AlarmManagerReceiver extends BroadcastReceiver {
    private static final String TAG = "AlarmManagerReceiver";
    private Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        Log.d(TAG, "onReceive: ");
        createNotifications();
    }
    private void createNotifications() {
        Notifications notifications = new Notifications(context);
        notifications.createNotification();
    }
}
