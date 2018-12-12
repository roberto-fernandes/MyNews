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

/**
 * Created by Roberto Fernandes on 12/12/2018.
 */
public class APIMethods {
    private List<APIResponse.Result> mNewsList = null;

    public List<APIResponse.Result> getTopStories() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TOP_STORIES_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APICall apiCall = retrofit.create(APICall.class);

        Call<APIResponse> call = apiCall.getNews();

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (!response.isSuccessful()) {
                    APIResponse apiResponse = response.body();
                    mNewsList = apiResponse.getResults();
                } else {
                    Log.d(TAG, "asd onResponse: not successful");
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Log.d(TAG, "asd onFailure: on Top APIResponse API Call.. "+t);
            }
        });
        return mNewsList;
    }
}
