package robfernandes.xyz.mynews.Model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
    private String today;
    private String queryTerm;
    NotificationMethods notificationMethods;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        Log.d(TAG, "onReceive: ");
        Calendar rightNow = Calendar.getInstance();
        today = DataManager.formatDateToCallAPI(rightNow);
        notificationMethods = new NotificationMethods(context);
        queryTerm = notificationMethods.loadQueryTermFromMemory();
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
        String title;
        String message;
        if (newsList != null && newsList.size() > 0) {
            title = "MyNews: We found new news";
            message = "We found new " + newsList.size() + " news that may interest you";
        } else {
            title = "MyNews: We didn't find new news";
            message = "Please check if you are connected to the internet";
        }
        notificationMethods.createNotification(title, message);
    }

    private void getNews() {
        DataManager mDataManager = new DataManager(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APICall apiCall = retrofit.create(APICall.class);

        Call<APIResponseSearch> call = apiCall.search(queryTerm, today, today);

        call.enqueue(new Callback<APIResponseSearch>() {
            @Override
            public void onResponse(@NonNull Call<APIResponseSearch> call,
                                   @NonNull Response<APIResponseSearch> response) {
                Log.d(TAG, "onResponse: aaa" + response);
                if (response.isSuccessful()) {
                    APIResponseSearch apiResponseSearch = response.body();
                    if (newsList != null) {
                        newsList.clear();
                    }
                    assert apiResponseSearch != null;
                    newsList = mDataManager.createNewsListFiltered(
                            apiResponseSearch.getResponse().getDocs(), sports, arts
                            , travel, politics, business, other);
                }
                createNotifications();
            }

            @Override
            public void onFailure(@NonNull Call<APIResponseSearch> call, @NonNull Throwable t) {
                createNotifications();
            }
        });
    }
}
