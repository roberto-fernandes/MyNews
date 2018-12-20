package robfernandes.xyz.mynews.ui.fragments.topStories;


import android.support.v4.app.Fragment;

import robfernandes.xyz.mynews.R;

import static robfernandes.xyz.mynews.utils.Constants.*;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessFragment extends BaseFragment {

    @Override
    protected int getSwipeRefreshLayoutID() {
        return R.id.fragment_business_swipe_refresh_layout;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_business;
    }

    @Override
    protected int getRecyclerViewID() {
        return R.id.fragment_business_recycler_view;
    }

    @Override
    protected String getSection() {
        return APIConstants.BUSINESS_SECTION;
    }
}
