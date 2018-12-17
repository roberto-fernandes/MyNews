package robfernandes.xyz.mynews.Controller.Fragments.TopStories;


import android.support.v4.app.Fragment;

import robfernandes.xyz.mynews.R;
import robfernandes.xyz.mynews.Utils.Constants;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends BaseFragment {


    @Override
    protected String getURL() {
        return Constants.API_BASE_URL;
    }

    @Override
    protected int getSwipeRefreshLayoutID() {
        return R.id.fragment_top_stories_swipe_refresh_layout;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_top_stories;
    }

    @Override
    protected int getRecyclerViewID() {
        return R.id.fragment_top_stories_recycler_view;
    }

    @Override
    protected String getSection() {
        return Constants.TOP_STORIES_SECTION;
    }
}
