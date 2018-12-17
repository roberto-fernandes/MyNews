package robfernandes.xyz.mynews.Controller.Fragments.TopStories;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import robfernandes.xyz.mynews.Model.APICall;
import robfernandes.xyz.mynews.Model.APIResponseTopStories;
import robfernandes.xyz.mynews.View.TopStoriesAdapter;

/**
 * Created by Roberto Fernandes on 14/12/2018.
 */
public abstract class BaseFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private View view;
    private List<APIResponseTopStories.Result> mNewsList;
    private static final String TAG = "BaseFragment";
    private RecyclerView mRecyclerView;
    private TopStoriesAdapter mTopStoriesAdapter;

    protected abstract String getURL();
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setItems(final boolean isRefreshing) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APICall apiCall = retrofit.create(APICall.class);

        Call<APIResponseTopStories> call = apiCall.TopStories(getSection());

        call.enqueue(new Callback<APIResponseTopStories>() {
            @Override
            public void onResponse(Call<APIResponseTopStories> call, Response<APIResponseTopStories> response) {
                if (response.isSuccessful()) {
                    APIResponseTopStories apiResponseTopStories = response.body();
                    mNewsList = apiResponseTopStories.getResults();
                    if(isRefreshing) {
                        updateUI();
                    } else {
                        configureRecyclerView();
                    }
                } else {
                    Log.e(TAG, "asd onResponse: not successful.." + response);
                }
            }

            @Override
            public void onFailure(Call<APIResponseTopStories> call, Throwable t) {
                Log.e(TAG, "asd onFailure: on Top APIResponseTopStories API Call.. " + t);
            }
        });
    }

    private void configureRecyclerView() {
        mTopStoriesAdapter = new TopStoriesAdapter(mNewsList, getContext());
        mRecyclerView = view.findViewById(getRecyclerViewID());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mTopStoriesAdapter);
    }

    private void configureSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setItems(true);
            }
        });
    }

    private void updateUI() {
        swipeRefreshLayout.setRefreshing(false);
        mTopStoriesAdapter.notifyDataSetChanged();
    }

}
