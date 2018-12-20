package robfernandes.xyz.mynews.ui.Fragments.TopStories;


import android.support.v4.app.Fragment;

import robfernandes.xyz.mynews.R;

import static robfernandes.xyz.mynews.Utils.Constants.TRAVEL_SECTION;

/**
 * A simple {@link Fragment} subclass.
 */
public class TravelFragment extends BaseFragment {

    @Override
    protected int getSwipeRefreshLayoutID() {
        return R.id.fragment_travel_swipe_refresh_layout;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_travel;
    }

    @Override
    protected int getRecyclerViewID() {
        return R.id.fragment_travel_recycler_view;
    }

    @Override
    protected String getSection() {
        return TRAVEL_SECTION;
    }
}