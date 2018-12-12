package robfernandes.xyz.mynews.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import robfernandes.xyz.mynews.Utils.Keys;

/**
 * Created by Roberto Fernandes on 12/12/2018.
 */
public interface APICall {
    String getURL = "v2/home.json?api_key="+Keys.API_KEY;
    @GET(getURL)
    Call<APIResponse> getNews();
}
