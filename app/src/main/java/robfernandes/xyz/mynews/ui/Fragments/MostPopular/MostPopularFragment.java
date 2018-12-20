package robfernandes.xyz.mynews.ui.Fragments.MostPopular;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import robfernandes.xyz.mynews.R;
import robfernandes.xyz.mynews.Utils.Constants;
import robfernandes.xyz.mynews.adapters.MostPopularAdapter;
import robfernandes.xyz.mynews.network.NewsService;
import robfernandes.xyz.mynews.network.model.APIResponseMostPopular;

public class MostPopularFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private View view;
    private List<APIResponseMostPopular.Result> mNewsList;
    private MostPopularAdapter mMostPopularAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_most_popular, container, false);
        setItems(false);
        configureSwipeRefreshLayout();
        return view;
    }

    private void setItems(final boolean isRefreshing) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsService newsService = retrofit.create(NewsService.class);

        Call<APIResponseMostPopular> call = newsService.mostPopular();

        call.enqueue(new Callback<APIResponseMostPopular>() {
            @Override
            public void onResponse(@NonNull Call<APIResponseMostPopular> call,
                                   @NonNull Response<APIResponseMostPopular> response) {
                if (response.isSuccessful()) {
                    APIResponseMostPopular apiResponseMostPopular = response.body();
                    assert apiResponseMostPopular != null;
                    mNewsList = apiResponseMostPopular.getResults();
                    if (isRefreshing) {
                        updateUI();
                    } else {
                        configureRecyclerView();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<APIResponseMostPopular> call,
                                  @NonNull Throwable t) {
            }
        });
    }

    private void configureRecyclerView() {
        mMostPopularAdapter = new MostPopularAdapter(mNewsList, getContext());
        RecyclerView recyclerView = view.findViewById(R.id.fragment_most_popular_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mMostPopularAdapter);
    }

    private void configureSwipeRefreshLayout() {
        swipeRefreshLayout = view.findViewById(R.id.fragment_most_popular_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(() -> setItems(true));
    }

    private void updateUI() {
        swipeRefreshLayout.setRefreshing(false);
        mMostPopularAdapter.notifyDataSetChanged();
    }

}
