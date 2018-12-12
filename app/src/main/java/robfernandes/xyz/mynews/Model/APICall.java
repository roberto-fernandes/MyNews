package robfernandes.xyz.mynews.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Roberto Fernandes on 12/12/2018.
 */
public interface APICall {
    @GET("posts")
    Call<List<News>> getNews();
}
