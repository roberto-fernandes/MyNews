package robfernandes.xyz.mynews.ui.Fragments.TopStories;


import android.support.v4.app.Fragment;

import robfernandes.xyz.mynews.R;

import static robfernandes.xyz.mynews.Utils.Constants.BUSINESS_SECTION;
import static robfernandes.xyz.mynews.Utils.Constants.API_BASE_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessFragment extends BaseFragment {

    @Override
    protected String getURL() {
        return API_BASE_URL;
    }

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
        return BUSINESS_SECTION;
    }
}
