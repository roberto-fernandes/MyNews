package robfernandes.xyz.mynews.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import java.util.Calendar;

import robfernandes.xyz.mynews.NewsApplication;
import robfernandes.xyz.mynews.R;
import robfernandes.xyz.mynews.storage.local.DataManager;
import robfernandes.xyz.mynews.ui.activities.SearchDisplayActivity;

import static robfernandes.xyz.mynews.utils.Constants.FilterKeys;
import static robfernandes.xyz.mynews.utils.Constants.NotificationsConstants;

/**
 * Created by Roberto Fernandes on 18/12/2018.
 */
@SuppressWarnings("SpellCheckingInspection")
public class NotifManager {
    private final Context context;
    private final NotificationManagerCompat notificationManagerCompat;

    public NotifManager(Context context) {
        this.context = context;
        notificationManagerCompat = NotificationManagerCompat.from(context);
    }

    public void createNotification(String title, String message) {
        String today = DataManager.formatDateToCallAPI(Calendar.getInstance());
        DataManager dataManager = new DataManager(context);

        Intent intent = new Intent(context, SearchDisplayActivity.class);
        intent.putExtra(FilterKeys.QUERY_TERM_KEY, loadQueryTermFromMemory());
        intent.putExtra(FilterKeys.BEGIN_DATE_KEY, today); //YYYYMMDD
        intent.putExtra(FilterKeys.END_DATE_KEY, today); //YYYYMMDD
        intent.putExtra(FilterKeys.SPORTS_STATUS_KEY,
                dataManager.readBooleanFromMemory(FilterKeys.SPORTS_STATUS_KEY));
        intent.putExtra(FilterKeys.ARTS_STATUS_KEY,
                dataManager.readBooleanFromMemory(FilterKeys.ARTS_STATUS_KEY));
        intent.putExtra(FilterKeys.TRAVEL_STATUS_KEY,
                dataManager.readBooleanFromMemory(FilterKeys.TRAVEL_STATUS_KEY));
        intent.putExtra(FilterKeys.POLITICS_STATUS_KEY,
                dataManager.readBooleanFromMemory(FilterKeys.POLITICS_STATUS_KEY));
        intent.putExtra(FilterKeys.OTHER_CATEGORIES_STATUS_KEY,
                dataManager.readBooleanFromMemory(FilterKeys.OTHER_CATEGORIES_STATUS_KEY));
        intent.putExtra(FilterKeys.BUSINESS_STATUS_KEY,
                dataManager.readBooleanFromMemory(FilterKeys.BUSINESS_STATUS_KEY));

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

        notificationManagerCompat.notify(NotificationsConstants.NOTIFICATION_ID, notification);
    }

    public void saveQueryTermInMemory(String term) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FilterKeys.NOTIFICATIONS_STATUS_KEY,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FilterKeys.QUERY_TERM_KEY, term);
        editor.apply();
    }

    public String loadQueryTermFromMemory() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FilterKeys.NOTIFICATIONS_STATUS_KEY,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(FilterKeys.QUERY_TERM_KEY, "");
    }
}
