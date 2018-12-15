package robfernandes.xyz.mynews.Controller.Fragments;

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
import robfernandes.xyz.mynews.Model.APIResponseMostPopular;
import robfernandes.xyz.mynews.R;
import robfernandes.xyz.mynews.Utils.Constants;
import robfernandes.xyz.mynews.View.MostPopularAdapter;

public class MostPopularFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private View view;
    private List<APIResponseMostPopular.Result> mNewsList;
    private static final String TAG = "BaseFragment";
    private RecyclerView mRecyclerView;
    private MostPopularAdapter mMostPopularAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_most_popular, container, false);
        swipeRefreshLayout = view.findViewById(R.id.fragment_most_popular_swipe_refresh_layout);
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
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APICall apiCall = retrofit.create(APICall.class);

        Call<APIResponseMostPopular> call = apiCall.mostPopular();

        call.enqueue(new Callback<APIResponseMostPopular>() {
            @Override
            public void onResponse(Call<APIResponseMostPopular> call, Response<APIResponseMostPopular> response) {
                if (response.isSuccessful()) {
                    APIResponseMostPopular apiResponseMostPopular = response.body();
                    mNewsList = apiResponseMostPopular.getResults();
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
            public void onFailure(Call<APIResponseMostPopular> call, Throwable t) {
                Log.e(TAG, "asd onFailure: on Top APIResponseTopStories API Call.. " + t);
            }
        });
    }

    private void configureRecyclerView() {
        mMostPopularAdapter = new MostPopularAdapter(mNewsList, getContext());
        mRecyclerView = view.findViewById(R.id.fragment_most_popular_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mMostPopularAdapter);

    }

    private void configureSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(() -> setItems(true));
    }

    private void updateUI() {
        swipeRefreshLayout.setRefreshing(false);
        mMostPopularAdapter.notifyDataSetChanged();
    }

}
