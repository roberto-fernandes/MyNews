package robfernandes.xyz.mynews.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import java.util.Calendar;

import robfernandes.xyz.mynews.storage.local.DataManager;
import robfernandes.xyz.mynews.ui.Activities.SearchDisplayActivity;
import robfernandes.xyz.mynews.R;
import robfernandes.xyz.mynews.NewsApplication;

import static robfernandes.xyz.mynews.Utils.Constants.ARTS_STATUS_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.BEGIN_DATE_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.BUSINESS_STATUS_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.END_DATE_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.NOTIFICATIONS_STATUS_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.NOTIFICATION_ID;
import static robfernandes.xyz.mynews.Utils.Constants.OTHER_CATEGORIES_STATUS_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.POLITICS_STATUS_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.QUERY_TERM_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.SPORTS_STATUS_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.TRAVEL_STATUS_KEY;

/**
 * Created by Roberto Fernandes on 18/12/2018.
 */
public class NotifManager {
    private Context context;
    private NotificationManagerCompat notificationManagerCompat;

    public NotifManager(Context context) {
        this.context = context;
        notificationManagerCompat = NotificationManagerCompat.from(context);
    }

    public void createNotification(String title, String message) {
        String today = DataManager.formatDateToCallAPI(Calendar.getInstance());
        DataManager dataManager = new DataManager(context);

        Intent intent = new Intent(context, SearchDisplayActivity.class);
        intent.putExtra(QUERY_TERM_KEY, loadQueryTermFromMemory());
        intent.putExtra(BEGIN_DATE_KEY, today); //YYYYMMDD
        intent.putExtra(END_DATE_KEY, today); //YYYYMMDD
        intent.putExtra(SPORTS_STATUS_KEY,
                dataManager.readBooleanFromMemory(SPORTS_STATUS_KEY));
        intent.putExtra(ARTS_STATUS_KEY,
                dataManager.readBooleanFromMemory(ARTS_STATUS_KEY));
        intent.putExtra(TRAVEL_STATUS_KEY,
                dataManager.readBooleanFromMemory(TRAVEL_STATUS_KEY));
        intent.putExtra(POLITICS_STATUS_KEY,
                dataManager.readBooleanFromMemory(POLITICS_STATUS_KEY));
        intent.putExtra(OTHER_CATEGORIES_STATUS_KEY,
                dataManager.readBooleanFromMemory(OTHER_CATEGORIES_STATUS_KEY));
        intent.putExtra(BUSINESS_STATUS_KEY,
                dataManager.readBooleanFromMemory(BUSINESS_STATUS_KEY));

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 2, intent
                , PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(context, NewsApplication.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_star)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_RECOMMENDATION)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        notificationManagerCompat.notify(NOTIFICATION_ID, notification);
    }

    public void saveQueryTermInMemory(String term) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NOTIFICATIONS_STATUS_KEY,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(QUERY_TERM_KEY, term);
        editor.apply();
    }

    public String loadQueryTermFromMemory() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NOTIFICATIONS_STATUS_KEY,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(QUERY_TERM_KEY, "");
    }
}
