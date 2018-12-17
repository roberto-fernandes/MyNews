package robfernandes.xyz.mynews.Controller.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import robfernandes.xyz.mynews.Model.APICall;
import robfernandes.xyz.mynews.Model.APIResponseSearch;
import robfernandes.xyz.mynews.R;
import robfernandes.xyz.mynews.Utils.Constants;
import robfernandes.xyz.mynews.View.SearchAdapter;

public class SearchDisplayActivity extends AppCompatActivity {
    private String term;
    private String beginDate;
    private String endDate;
    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<APIResponseSearch.Doc> mNewsList;
    private List<APIResponseSearch.Doc> mNewsListFiltered;
    private static final String TAG = "SearchDisplayActivity";
    private boolean sportsCheckbox;
    private boolean artsCheckbox;
    private boolean travelCheckbox;
    private boolean politicsCheckbox;
    private boolean othersCheckbox;
    private boolean businessCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_display);

        configureSwipeRefreshLayout();
        getSearchData();
        setItems(false);
    }

    private void getSearchData() {
        Intent intent = getIntent();
        term = intent.getStringExtra("term");
        beginDate = intent.getStringExtra("begin_date");
        endDate = intent.getStringExtra("end_date");
        sportsCheckbox = intent.getBooleanExtra("sportsCheckbox", false);
        artsCheckbox = intent.getBooleanExtra("artsCheckbox", false);
        travelCheckbox = intent.getBooleanExtra("travelCheckbox", false);
        politicsCheckbox = intent.getBooleanExtra("politicsCheckbox", false);
        othersCheckbox = intent.getBooleanExtra("othersCheckbox", false);
        businessCheckbox = intent.getBooleanExtra("businessCheckbox", false);
    }

    private void setItems(final boolean isRefreshing) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APICall apiCall = retrofit.create(APICall.class);

        Call<APIResponseSearch> call = apiCall.search(term, beginDate, endDate);

        call.enqueue(new Callback<APIResponseSearch>() {
            @Override
            public void onResponse(Call<APIResponseSearch> call, Response<APIResponseSearch> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: sss" + response);
                    APIResponseSearch apiResponseSearch = response.body();
                    mNewsList = apiResponseSearch.getResponse().getDocs();
                    createNewsListFiltered ();
                    if (isRefreshing) {
                        updateUI();
                    } else {
                        configureRecyclerView();
                    }
                } else {
                    Log.d(TAG, "sss onResponse: noo " + response);
                }
            }

            @Override
            public void onFailure(Call<APIResponseSearch> call, Throwable t) {
                Log.d(TAG, "onFailure: sss" + t);
            }
        });
    }

    private void configureRecyclerView() {
        searchAdapter = new SearchAdapter(mNewsListFiltered, SearchDisplayActivity.this);
        recyclerView = findViewById(R.id.activity_search_display_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchDisplayActivity.this));
        recyclerView.setAdapter(searchAdapter);
    }
    private void configureSwipeRefreshLayout() {
        swipeRefreshLayout = findViewById(R.id.activity_search_display_swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(() -> setItems(true));
    }

    private void updateUI() {
        swipeRefreshLayout.setRefreshing(false);
        searchAdapter.notifyDataSetChanged();
    }

    private void createNewsListFiltered () {
        mNewsListFiltered = new ArrayList<>();
        String categories[] = {"Sports", "Arts", "Travel",  "Politics", "Business"};

        for (APIResponseSearch.Doc news: mNewsList){
            testNewsCategory(sportsCheckbox, categories[0], news);
            testNewsCategory(artsCheckbox, categories[1], news);
            testNewsCategory(travelCheckbox, categories[2], news);
            testNewsCategory(politicsCheckbox, categories[3], news);
            testNewsCategory(businessCheckbox, categories[4], news);

            if (othersCheckbox) {
                boolean anotherCatefory = true;
                String newsSection = news.getSectionName();
                for (String category: categories) {
                    if (newsSection.equals(category)) {
                        anotherCatefory = false;
                    }
                }
                if (anotherCatefory) {
                    mNewsListFiltered.add(news);
                }
            }
        }
    }

    private void testNewsCategory(boolean isChecked, String sectionName, APIResponseSearch.Doc news) {
        if (isChecked) {
            if (news.getSectionName().equals(sectionName)) {
                mNewsListFiltered.add(news);
            }
        }
    }
}
