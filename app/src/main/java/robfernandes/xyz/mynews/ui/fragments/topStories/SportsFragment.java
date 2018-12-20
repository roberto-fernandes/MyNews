package robfernandes.xyz.mynews.ui.fragments.topStories;


import robfernandes.xyz.mynews.R;

import static robfernandes.xyz.mynews.utils.Constants.*;

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
        return APIConstants.SPORTS_SECTION;
    }
}
