package robfernandes.xyz.mynews.Controller.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Switch;

import robfernandes.xyz.mynews.Model.DataStorage;
import robfernandes.xyz.mynews.R;

public class NotificationsActivity extends AppCompatActivity {

    private DataStorage mDataStorage;
    private boolean isNotificationsActive;
    private Switch notificationsSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        mDataStorage = new DataStorage(NotificationsActivity.this);
        isNotificationsActive = mDataStorage.readNotificationsStatusFromMemory();
        notificationsSwitch = findViewById(R.id.activity_notifications_switch);
        notificationsSwitch.setChecked(isNotificationsActive);
        notificationsSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isNotificationsActive = isChecked;
            mDataStorage.saveNotificationsStatusInMemory(isNotificationsActive);
        });
    }
}
