package robfernandes.xyz.mynews.ui.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import robfernandes.xyz.mynews.network.NewsService;
import robfernandes.xyz.mynews.network.model.APIResponseSearch;
import robfernandes.xyz.mynews.storage.local.DataManager;
import robfernandes.xyz.mynews.R;
import robfernandes.xyz.mynews.adapters.SearchAdapter;

import static robfernandes.xyz.mynews.Utils.Constants.API_BASE_URL;
import static robfernandes.xyz.mynews.Utils.Constants.ARTS_STATUS_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.BEGIN_DATE_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.BUSINESS_STATUS_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.END_DATE_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.OTHER_CATEGORIES_STATUS_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.POLITICS_STATUS_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.QUERY_TERM_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.SPORTS_STATUS_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.TRAVEL_STATUS_KEY;

public class SearchDisplayActivity extends AppCompatActivity {
    private String term;
    private String beginDate;
    private String endDate;
    private List<APIResponseSearch.Doc> mNewsList;
    private static final String TAG = "SearchDisplayActivity";
    private DataManager mDataManager;
    private boolean sportsCheckbox;
    private boolean artsCheckbox;
    private boolean travelCheckbox;
    private boolean politicsCheckbox;
    private boolean otherCheckbox;
    private boolean businessCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_display);

        mDataManager = new DataManager(SearchDisplayActivity.this);
        getSearchData();
        setItems();
    }

    private void getSearchData() {
        Intent intent = getIntent();

        term = intent.getStringExtra(QUERY_TERM_KEY);
        beginDate = intent.getStringExtra(BEGIN_DATE_KEY);
        endDate = intent.getStringExtra(END_DATE_KEY);
        sportsCheckbox = intent.getBooleanExtra(SPORTS_STATUS_KEY, false);
        artsCheckbox = intent.getBooleanExtra(ARTS_STATUS_KEY, false);
        travelCheckbox = intent.getBooleanExtra(TRAVEL_STATUS_KEY, false);
        politicsCheckbox = intent.getBooleanExtra(POLITICS_STATUS_KEY, false);
        otherCheckbox = intent.getBooleanExtra(OTHER_CATEGORIES_STATUS_KEY, false);
        businessCheckbox = intent.getBooleanExtra(BUSINESS_STATUS_KEY, false);
    }

    private void setItems() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NewsService newsService = retrofit.create(NewsService.class);

        Call<APIResponseSearch> call = newsService.search(term, beginDate, endDate);

        call.enqueue(new Callback<APIResponseSearch>() {
            @Override
            public void onResponse(@NonNull Call<APIResponseSearch> call,
                                   @NonNull Response<APIResponseSearch>
                                           response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: sss" + response);
                    APIResponseSearch apiResponseSearch = response.body();
                    if (mNewsList != null) {
                        mNewsList.clear();
                    }
                    assert apiResponseSearch != null;
                    mNewsList = mDataManager.createNewsListFiltered(
                            apiResponseSearch.getResponse().getDocs(), sportsCheckbox, artsCheckbox
                            , travelCheckbox, politicsCheckbox, businessCheckbox, otherCheckbox);
                    configureRecyclerView();
                } else {
                    Log.d(TAG, "sss onResponse: noo " + response);
                }
            }

            @Override
            public void onFailure(@NonNull Call<APIResponseSearch> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: sss" + t);
            }
        });
    }

    private void configureRecyclerView() {
        SearchAdapter searchAdapter = new SearchAdapter(mNewsList,
                SearchDisplayActivity.this);
        RecyclerView recyclerView = findViewById(R.id.activity_search_display_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchDisplayActivity.this));
        recyclerView.setAdapter(searchAdapter);
    }
}
