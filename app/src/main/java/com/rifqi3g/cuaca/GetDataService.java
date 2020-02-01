package com.rifqi3g.cuaca;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("search")
    Call<ResponseBody> getSearch(
            @Query("query") String query
    );

    @GET("location/{woeid}")
    Call<ResponseBody> getLocation(
            @Path("woeid") Integer woeid);

    @GET("location/{woeid}/{date}")
    Call<ResponseBody> getDate(
            @Path("woeid") Integer woeid,
            @Path("date") String date
    );
}
