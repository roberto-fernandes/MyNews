package robfernandes.xyz.mynews.utils;

import android.support.v4.app.Fragment;

import robfernandes.xyz.mynews.ui.fragments.topStories.ArtsFragment;
import robfernandes.xyz.mynews.ui.fragments.topStories.BusinessFragment;
import robfernandes.xyz.mynews.ui.fragments.mostPopular.MostPopularFragment;
import robfernandes.xyz.mynews.ui.fragments.topStories.SportsFragment;
import robfernandes.xyz.mynews.ui.fragments.topStories.TopStoriesFragment;
import robfernandes.xyz.mynews.ui.fragments.topStories.TravelFragment;

/**
 * Created by Roberto Fernandes on 05/12/2018.
 */
public final class Constants {
    public interface FilterKeys {
        String NOTIFICATIONS_STATUS_KEY = "NotificationStatusKey";
        String SPORTS_STATUS_KEY = "SportsStatusKey";
        String ARTS_STATUS_KEY = "ArtsStatusKey";
        String TRAVEL_STATUS_KEY = "TravelStatusKey";
        String POLITICS_STATUS_KEY = "PoliticsStatusKey";
        String BUSINESS_STATUS_KEY = "BusinessStatusKey";
        String OTHER_CATEGORIES_STATUS_KEY = "OCStatusKey";
        String QUERY_TERM_KEY = "QueryTermKey";
        String BEGIN_DATE_KEY = "BeginDateKey";
        String END_DATE_KEY = "EndDateKey";
    }

    public interface FragmentsConstants {
        String[] PAGE_TITLES = {"TOP STORIES", "MOST POPULAR", "SPORTS",
                "BUSINESS", "ARTS", "TRAVEL"};
        Fragment[] FRAGMENTS = {new TopStoriesFragment(),
                new MostPopularFragment(), new SportsFragment(), new BusinessFragment(),
                new ArtsFragment(), new TravelFragment()};
        int TOP_STORIES_INDEX = 0;
        int MOST_POPULAR_INDEX = 1;
        int SPORTS_INDEX = 2;
        int BUSINESS_INDEX = 3;
        int ARTS_INDEX = 4;
        int TRAVEL_INDEX = 5;
    }

    public interface APIConstants {
        String API_BASE_URL = "https://api.nytimes.com/svc/";
        String TOP_STORIES_SECTION = "home";
        String SPORTS_SECTION = "sports";
        String ARTS_SECTION = "arts";
        String TRAVEL_SECTION = "travel";
        String BUSINESS_SECTION = "business";
    }

    public interface NotificationsConstants {
        int HOUR_NOTIFICATION = 19;
        int MINUTES_NOTIFICATION = 0;
        int NOTIFICATION_ID = Integer.MIN_VALUE;
        String[] NOTIFICATIONS_CATEGORIES = {"Sports", "Arts", "Travel",
                "Politics", "Business"};
    }
}
