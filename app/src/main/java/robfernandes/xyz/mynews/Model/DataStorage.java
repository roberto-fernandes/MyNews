package robfernandes.xyz.mynews.Model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Roberto Fernandes on 18/12/2018.
 */
public class DataStorage {
    private SharedPreferences sharedPreferences;
    private Context context;

    public DataStorage(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("notifications", Context.MODE_PRIVATE);
    }

    public boolean readNotificationsStatusFromMemory() {
        boolean isActive;
        isActive = sharedPreferences.getBoolean("notifications", false);
        return isActive;
    }

    public void saveNotificationsStatusInMemory (boolean isActive) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("notifications", isActive);
        editor.apply();
    }

}
