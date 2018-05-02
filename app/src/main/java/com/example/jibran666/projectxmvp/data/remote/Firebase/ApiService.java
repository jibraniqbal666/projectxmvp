package com.example.jibran666.projectxmvp.data.remote.Firebase;

import com.example.jibran666.projectxmvp.data.model.response.Weather;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("weather")
    Single<Response<Weather>> getWeather(@Query("q") String city, @Query("appid") String api);

}
