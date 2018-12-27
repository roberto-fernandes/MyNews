package robfernandes.xyz.mynews.storage.local;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import robfernandes.xyz.mynews.network.model.APIResponseSearch;

import static robfernandes.xyz.mynews.utils.Constants.*;

/**
 * Created by Roberto Fernandes on 18/12/2018.
 */
public class DataManager {
    private final SharedPreferences sharedPreferences;
    private List<APIResponseSearch.Doc> newsListFiltered;

    public DataManager(Context context) {
        sharedPreferences = context.getSharedPreferences(FilterKeys.NOTIFICATIONS_STATUS_KEY,
                Context.MODE_PRIVATE);
    }

    public void saveBooleanInMemory(String key, boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, status);
        editor.apply();
    }

    public boolean readBooleanFromMemory(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public List<APIResponseSearch.Doc> createNewsListFiltered(List<APIResponseSearch.Doc> newsList,
                                                              boolean sportsCheckbox,
                                                              boolean artsCheckbox,
                                                              boolean travelCheckbox,
                                                              boolean politicsCheckbox,
                                                              boolean businessCheckbox,
                                                              boolean otherCheckbox) {
        newsListFiltered = new ArrayList<>();


        for (APIResponseSearch.Doc news : newsList) {
            filterNewsCategory(sportsCheckbox, NotificationsConstants.NOTIFICATIONS_CATEGORIES[0], news);
            filterNewsCategory(artsCheckbox, NotificationsConstants.NOTIFICATIONS_CATEGORIES[1], news);
            filterNewsCategory(travelCheckbox, NotificationsConstants.NOTIFICATIONS_CATEGORIES[2], news);
            filterNewsCategory(politicsCheckbox, NotificationsConstants.NOTIFICATIONS_CATEGORIES[3], news);
            filterNewsCategory(businessCheckbox, NotificationsConstants.NOTIFICATIONS_CATEGORIES[4], news);

            if (otherCheckbox) {
                boolean anotherCategory = true;
                String newsSection = news.getSectionName();
                for (String category : NotificationsConstants.NOTIFICATIONS_CATEGORIES) {
                    if (newsSection.equals(category)) {
                        anotherCategory = false;
                    }
                }
                if (anotherCategory) {
                    newsListFiltered.add(news);
                }
            }
        }
        return newsListFiltered;
    }

    private void filterNewsCategory(boolean isChecked, String sectionName,
                                    APIResponseSearch.Doc news) {
        if (isChecked) {
            if (news.getSectionName().equals(sectionName)) {
                newsListFiltered.add(news);
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatDateToCallAPI(Calendar date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date.getTime());
    }

}
