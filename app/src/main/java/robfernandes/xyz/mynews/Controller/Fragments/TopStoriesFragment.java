package robfernandes.xyz.mynews.Controller.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import robfernandes.xyz.mynews.Model.APIResponse;
import robfernandes.xyz.mynews.R;
import robfernandes.xyz.mynews.View.TopStoriesAdapter;

import static robfernandes.xyz.mynews.Utils.Constants.TOP_STORIES_API_BASE_URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends Fragment {

    private static final String TAG = "TopStoriesFragment";
    private RecyclerView mRecyclerView;
    private TopStoriesAdapter mTopStoriesAdapter;
    private List<APIResponse.Result> mNewsList;
    private View view;

    public TopStoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_top_stories, container, false);
        setTopStories();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setTopStories() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TOP_STORIES_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APICall apiCall = retrofit.create(APICall.class);

        Call<APIResponse> call = apiCall.getNews();

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.isSuccessful()) {
                    APIResponse apiResponse = response.body();
                    mNewsList = apiResponse.getResults();
                    displayNews();
                } else {
                    Log.e(TAG, "asd onResponse: not successful.." + response);
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.e(TAG, "asd onFailure: on Top APIResponse API Call.. " + t);
            }
        });
    }

    private void displayNews() {
        mTopStoriesAdapter = new TopStoriesAdapter(mNewsList, getContext());
        mRecyclerView = view.findViewById(R.id.fragment_top_stories_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mTopStoriesAdapter);
    }
}
