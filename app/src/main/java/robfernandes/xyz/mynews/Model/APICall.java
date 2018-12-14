package robfernandes.xyz.mynews.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import robfernandes.xyz.mynews.Utils.Keys;

/**
 * Created by Roberto Fernandes on 12/12/2018.
 */
public interface APICall {

    @GET("v2/{section}.json?api_key="+Keys.API_KEY)
    Call<APIResponse> getNews(@Path("section") String section);
}
