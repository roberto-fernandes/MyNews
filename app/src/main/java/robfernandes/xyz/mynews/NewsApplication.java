package robfernandes.xyz.mynews;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

/**
 * Created by Roberto Fernandes on 18/12/2018.
 */
public class NewsApplication extends Application {
    public static final String CHANNEL_ID = "NotificationsChannel";

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Notifications Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Notifications from MyNews");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}