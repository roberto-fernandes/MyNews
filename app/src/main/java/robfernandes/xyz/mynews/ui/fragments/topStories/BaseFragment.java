package robfernandes.xyz.mynews.ui.fragments.topStories;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import robfernandes.xyz.mynews.adapters.TopStoriesAdapter;
import robfernandes.xyz.mynews.network.NewsService;
import robfernandes.xyz.mynews.network.model.APIResponseTopStories;

import static robfernandes.xyz.mynews.utils.Constants.*;

/**
 * Created by Roberto Fernandes on 14/12/2018.
 */
public abstract class BaseFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private View view;
    private List<APIResponseTopStories.Result> mNewsList;
    private static final String TAG = "BaseFragment";
    private TopStoriesAdapter mTopStoriesAdapter;

    protected abstract int getSwipeRefreshLayoutID();

    protected abstract int getFragmentLayout();

    protected abstract int getRecyclerViewID();

    protected abstract String getSection();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(getFragmentLayout(), container, false);
        swipeRefreshLayout = view.findViewById(getSwipeRefreshLayoutID());
        setItems(false);
        configureSwipeRefreshLayout();
        return view;
    }

    private void setItems(final boolean isRefreshing) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConstants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsService newsService = retrofit.create(NewsService.class);

        Call<APIResponseTopStories> call = newsService.TopStories(getSection());

        call.enqueue(new Callback<APIResponseTopStories>() {
            @Override
            public void onResponse(@NonNull Call<APIResponseTopStories> call,
                                   @NonNull Response<APIResponseTopStories> response) {
                if (response.isSuccessful()) {
                    APIResponseTopStories apiResponseTopStories = response.body();
                    assert apiResponseTopStories != null;
                    mNewsList = apiResponseTopStories.getResults();
                    if (isRefreshing) {
                        updateUI();
                    } else {
                        configureRecyclerView();
                    }
                } else {
                    Log.e(TAG, "asd onResponse: not successful.." + response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<APIResponseTopStories> call, @NonNull Throwable t) {
                Log.e(TAG, "asd onFailure: on Top APIResponseTopStories API Call.. " + t + "  ... " + call);
            }
        });
    }

    private void configureRecyclerView() {
        mTopStoriesAdapter = new TopStoriesAdapter(mNewsList, getContext());
        RecyclerView recyclerView = view.findViewById(getRecyclerViewID());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mTopStoriesAdapter);
    }

    private void configureSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(() -> setItems(true));
    }

    private void updateUI() {
        swipeRefreshLayout.setRefreshing(false);
        mTopStoriesAdapter.notifyDataSetChanged();
    }
}
