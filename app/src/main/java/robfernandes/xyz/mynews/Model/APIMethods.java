package robfernandes.xyz.mynews.Model;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;
import static robfernandes.xyz.mynews.Utils.Constants.TOP_STORIES_API_BASE_URL;
import static robfernandes.xyz.mynews.Utils.Keys.API_KEY;

/**
 * Created by Roberto Fernandes on 12/12/2018.
 */
public class APIMethods {
    private List<News> newsList = null;

    public List<News> getTopStories () {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TOP_STORIES_API_BASE_URL + API_KEY)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APICall apiCall = retrofit.create(APICall.class);

        Call<List<News>> call = apiCall.getNews();

        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (!response.isSuccessful()) {
                   newsList = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Log.e(TAG, "onFailure: on Top News API Call");
            }
        });
        return newsList;
    }
}
