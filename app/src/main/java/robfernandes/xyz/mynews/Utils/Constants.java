package robfernandes.xyz.mynews.Utils;

import android.support.v4.app.Fragment;

import robfernandes.xyz.mynews.ui.Fragments.TopStories.ArtsFragment;
import robfernandes.xyz.mynews.ui.Fragments.TopStories.BusinessFragment;
import robfernandes.xyz.mynews.ui.Fragments.MostPopular.MostPopularFragment;
import robfernandes.xyz.mynews.ui.Fragments.TopStories.SportsFragment;
import robfernandes.xyz.mynews.ui.Fragments.TopStories.TopStoriesFragment;
import robfernandes.xyz.mynews.ui.Fragments.TopStories.TravelFragment;

/**
 * Created by Roberto Fernandes on 05/12/2018.
 */
public final class Constants {
    public static final String[] PAGE_TITLE = {"TOP STORIES", "MOST POPULAR", "SPORTS",
            "BUSINESS", "ARTS", "TRAVEL"};
    public static final Fragment[] FRAGMENTS = {new TopStoriesFragment(),
            new MostPopularFragment(), new SportsFragment(), new BusinessFragment(),
            new ArtsFragment(), new TravelFragment()};
    public static final int TOP_STORIES_INDEX = 0;
    public static final int MOST_POPULAR_INDEX = 1;
    public static final int SPORTS_INDEX = 2;
    public static final int BUSINESS_INDEX = 3;
    public static final int ARTS_INDEX = 4;
    public static final int TRAVEL_INDEX = 5;
    public static final String API_BASE_URL = "https://api.nytimes.com/svc/";
    public static final String TOP_STORIES_SECTION = "home";
    public static final String SPORTS_SECTION = "sports";
    public static final String ARTS_SECTION = "arts";
    public static final String TRAVEL_SECTION = "travel";
    public static final String BUSINESS_SECTION = "business";
    public static final int HOUR_NOTIFICATION = 19;
    public static final int MINUTES_NOTIFICATION = 0;
    public static final int NOTIFICATION_ID = Integer.MIN_VALUE;
    public static final String[] NOTIFICATIONS_CATEGORIES = {"Sports", "Arts", "Travel",
            "Politics", "Business"};
    public static final String NOTIFICATIONS_STATUS_KEY = "NotificationStatusKey";
    public static final String SPORTS_STATUS_KEY = "SportsStatusKey";
    public static final String ARTS_STATUS_KEY = "ArtsStatusKey";
    public static final String TRAVEL_STATUS_KEY = "TravelStatusKey";
    public static final String POLITICS_STATUS_KEY = "PoliticsStatusKey";
    public static final String BUSINESS_STATUS_KEY = "BusinessStatusKey";
    public static final String OTHER_CATEGORIES_STATUS_KEY = "OCStatusKey";
    public static final String QUERY_TERM_KEY = "QueryTermKey";
    public static final String BEGIN_DATE_KEY = "BeginDateKey";
    public static final String END_DATE_KEY = "EndDateKey";
    public interface FilterKeys {
        String NOTIFICATIONS_STATUS_KEY = "NotificationStatusKey";
    }
}
