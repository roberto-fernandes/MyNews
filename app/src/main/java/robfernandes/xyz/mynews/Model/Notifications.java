package robfernandes.xyz.mynews.Model;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import robfernandes.xyz.mynews.R;
import robfernandes.xyz.mynews.Utils.Channels;

import static robfernandes.xyz.mynews.Utils.Constants.NOTIFICATION_ID;

/**
 * Created by Roberto Fernandes on 18/12/2018.
 */
public class Notifications {
    private Context context;
    private NotificationManagerCompat notificationManagerCompat;

    public Notifications(Context context) {
        this.context = context;
        notificationManagerCompat = NotificationManagerCompat.from(context);
    }

    public void createNotification() {
        String title = "some title";
        String message = "some mesaage";

        Notification notification = new NotificationCompat.Builder(context, Channels.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_star)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_RECOMMENDATION)
                .build();

        notificationManagerCompat.notify(NOTIFICATION_ID, notification);
    }
}
