package com.example.jibran666.projectxmvp.data;

import com.example.jibran666.projectxmvp.Constants;
import com.example.jibran666.projectxmvp.data.local.PreferencesHelper;
import com.example.jibran666.projectxmvp.data.model.response.Weather;
import com.example.jibran666.projectxmvp.data.remote.Firebase.ApiService;
import com.example.jibran666.projectxmvp.injections.ApiServiceScope;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

/**
 * Created by shivam on 29/5/17.
 */
@ApiServiceScope
public class DataManager {

    @Inject
    PreferencesHelper preferencesHelper;
    private ApiService apiService;

    @Inject
    public DataManager(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<Response<Weather>> getWeather(String city) {
        return apiService.getWeather(city, Constants.API_KEY);
    }

}
