package robfernandes.xyz.mynews.Model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roberto Fernandes on 18/12/2018.
 */
public class DataManager {
    private SharedPreferences sharedPreferences;
    private Context context;
    private List<APIResponseSearch.Doc> newsListFiltered;

    public DataManager(Context context) {
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

    public List<APIResponseSearch.Doc> createNewsListFiltered (List<APIResponseSearch.Doc> newsList,boolean sportsCheckbox, boolean artsCheckbox , boolean travelCheckbox, boolean politicsCheckbox, boolean  businessCheckbox, boolean otherCheckbox) {
        newsListFiltered = new ArrayList<>();
        String categories[] = {"Sports", "Arts", "Travel",  "Politics", "Business"};

        for (APIResponseSearch.Doc news: newsList){
            testNewsCategory(sportsCheckbox, categories[0], news);
            testNewsCategory(artsCheckbox, categories[1], news);
            testNewsCategory(travelCheckbox, categories[2], news);
            testNewsCategory(politicsCheckbox, categories[3], news);
            testNewsCategory(businessCheckbox, categories[4], news);

            if (otherCheckbox) {
                boolean anotherCategory = true;
                String newsSection = news.getSectionName();
                for (String category: categories) {
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

    private void testNewsCategory(boolean isChecked, String sectionName, APIResponseSearch.Doc news) {
        if (isChecked) {
            if (news.getSectionName().equals(sectionName)) {
                newsListFiltered.add(news);
            }
        }
    }
}
