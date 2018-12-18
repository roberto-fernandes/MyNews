package robfernandes.xyz.mynews.Controller.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Roberto Fernandes on 18/12/2018.
 */
public class AlarmManagerReceiver extends BroadcastReceiver {
    private static final String TAG = "AlarmManagerReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");

    }
}
