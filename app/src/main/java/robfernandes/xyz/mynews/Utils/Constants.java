package robfernandes.xyz.mynews.Utils;

import android.support.v4.app.Fragment;

import robfernandes.xyz.mynews.Controller.Fragments.ArtsFragment;
import robfernandes.xyz.mynews.Controller.Fragments.BusinessFragment;
import robfernandes.xyz.mynews.Controller.Fragments.MostPopularFragment;
import robfernandes.xyz.mynews.Controller.Fragments.SportsFragment;
import robfernandes.xyz.mynews.Controller.Fragments.TopStoriesFragment;
import robfernandes.xyz.mynews.Controller.Fragments.TravelFragment;

/**
 * Created by Roberto Fernandes on 05/12/2018.
 */
public final class Constants {
    public static final String[] PAGE_TITLE = {"TOP STORIES", "MOST POPULAR", "SPORTS", "BUSINESS", "ARTS", "TRAVEL"};
    public static final Fragment[] FRAGMENTS = {new TopStoriesFragment(), new MostPopularFragment(), new SportsFragment(), new BusinessFragment(), new ArtsFragment(), new TravelFragment()};
    public static final int TOP_STORIES_INDEX = 0;
    public static final int MOST_POPULAR_INDEX = 1;
    public static final int SPORTS_INDEX = 2;
    public static final int BUSINESS_INDEX = 3;
    public static final int ARTS_INDEX = 4;
    public static final int TRAVEL_INDEX = 5;
    public static final String TOP_STORIES_API_BASE_URL = "https://api.nytimes.com/svc/topstories/v2/home.json?api_key=";
    public static final String ERROR_MESSAGE = "We were not able to load the news, please try again later";
}
