package robfernandes.xyz.mynews.Model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static robfernandes.xyz.mynews.Utils.Constants.API_BASE_URL;
import static robfernandes.xyz.mynews.Utils.Constants.ARTS_STATUS_KEY;
import static robfernandes.xyz.mynews.Utils.Constants.SPORTS_STATUS_KEY;

/**
 * Created by Roberto Fernandes on 18/12/2018.
 */
public class AlarmManagerReceiver extends BroadcastReceiver {
    private static final String TAG = "AlarmManagerReceiver";
    private Context context;
    private List<APIResponseSearch.Doc> newsList;
    private boolean sports;
    private boolean arts;
    private boolean travel;
    private boolean politics;
    private boolean other;
    private boolean business;
    private Calendar rightNow;
    private String today;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        Log.d(TAG, "onReceive: ");
        rightNow = Calendar.getInstance();
        today = DataManager.formatDateToCallAPI(rightNow);
        getCategoriesStatus();
        getNews();
    }

    private void getCategoriesStatus() {
        DataManager dataManager = new DataManager(context);
        sports = dataManager.readBooleanFromMemory(SPORTS_STATUS_KEY);
        arts = dataManager.readBooleanFromMemory(ARTS_STATUS_KEY);
        travel = dataManager.readBooleanFromMemory(ARTS_STATUS_KEY);
        politics = dataManager.readBooleanFromMemory(ARTS_STATUS_KEY);
        other = dataManager.readBooleanFromMemory(ARTS_STATUS_KEY);
        business = dataManager.readBooleanFromMemory(ARTS_STATUS_KEY);
    }

    private void createNotifications() {
        Notifications notifications = new Notifications(context);

        String title;
        String message;
        if (newsList!=null&&newsList.size()>0) {
            title = "MyNews: We found new news";
            message = "We found new " + newsList.size() + " news that may interest you";
        } else {
            title = "MyNews: We didn't find new news";
            message = "Please check if you are connected to the internet";
        }
        notifications.createNotification(title, message);
    }

    private void getNews() {
        DataManager mDataManager = new DataManager(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APICall apiCall = retrofit.create(APICall.class);

        Call<APIResponseSearch> call = apiCall.search("", today, today);

        call.enqueue(new Callback<APIResponseSearch>() {
            @Override
            public void onResponse(Call<APIResponseSearch> call,
                                   Response<APIResponseSearch> response) {
                Log.d(TAG, "onResponse: aaa" + response);
                if (response.isSuccessful()) {
                    APIResponseSearch apiResponseSearch = response.body();
                    if (newsList != null) {
                        newsList.clear();
                    }
                    newsList = mDataManager.createNewsListFiltered(
                            apiResponseSearch.getResponse().getDocs(), sports, arts
                            , travel, politics, business, other);
                }
                createNotifications();
            }

            @Override
            public void onFailure(Call<APIResponseSearch> call, Throwable t) {
                createNotifications();
            }
        });
    }
}
