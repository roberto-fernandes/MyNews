package robfernandes.xyz.mynews.ui.Fragments.TopStories;


import robfernandes.xyz.mynews.R;

import static robfernandes.xyz.mynews.Utils.Constants.SPORTS_SECTION;

public class SportsFragment extends BaseFragment {

    @Override
    protected int getSwipeRefreshLayoutID() {
        return R.id.fragment_sports_swipe_refresh_layout;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_sports;
    }

    @Override
    protected int getRecyclerViewID() {
        return R.id.fragment_sports_recycler_view;
    }

    @Override
    protected String getSection() {
        return SPORTS_SECTION;
    }
}
