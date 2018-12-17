package robfernandes.xyz.mynews.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import robfernandes.xyz.mynews.Utils.Keys;

/**
 * Created by Roberto Fernandes on 12/12/2018.
 */
public interface APICall {

    @GET("topstories/v2/{section}.json?api_key=" + Keys.API_KEY)
    Call<APIResponseTopStories> TopStories(@Path("section") String section);

    @GET("mostpopular/v2/mostviewed/all-sections/7.json?api_key=" + Keys.API_KEY)
    Call<APIResponseMostPopular> mostPopular();

    @GET("search/v2/articlesearch.json?api_key=" + Keys.API_KEY)
    Call<APIResponseSearch> search(@Query("q") String term, @Query("begin_date") String beginDate, @Query("end_date") String endDate);
}
