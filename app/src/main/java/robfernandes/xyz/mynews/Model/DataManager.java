package robfernandes.xyz.mynews.Model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static robfernandes.xyz.mynews.Utils.Constants.NOTIFICATIONS_CATEGORIES;
import static robfernandes.xyz.mynews.Utils.Constants.NOTIFICATIONS_STATUS_KEY;

/**
 * Created by Roberto Fernandes on 18/12/2018.
 */
public class DataManager {
    private SharedPreferences sharedPreferences;
    private List<APIResponseSearch.Doc> newsListFiltered;

    public DataManager(Context context) {
        sharedPreferences = context.getSharedPreferences(NOTIFICATIONS_STATUS_KEY,
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
            testNewsCategory(sportsCheckbox, NOTIFICATIONS_CATEGORIES[0], news);
            testNewsCategory(artsCheckbox, NOTIFICATIONS_CATEGORIES[1], news);
            testNewsCategory(travelCheckbox, NOTIFICATIONS_CATEGORIES[2], news);
            testNewsCategory(politicsCheckbox, NOTIFICATIONS_CATEGORIES[3], news);
            testNewsCategory(businessCheckbox, NOTIFICATIONS_CATEGORIES[4], news);

            if (otherCheckbox) {
                boolean anotherCategory = true;
                String newsSection = news.getSectionName();
                for (String category : NOTIFICATIONS_CATEGORIES) {
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

    private void testNewsCategory(boolean isChecked, String sectionName,
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
