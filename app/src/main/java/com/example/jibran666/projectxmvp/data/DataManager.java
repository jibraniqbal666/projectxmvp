package com.example.jibran666.projectxmvp.data;

import com.example.jibran666.projectxmvp.data.local.PreferencesHelper;
import com.example.jibran666.projectxmvp.data.remote.Firebase.ApiService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by shivam on 29/5/17.
 */
@Singleton
public class DataManager {

    @Inject
    PreferencesHelper preferencesHelper;
    private ApiService apiService;

    @Inject
    public DataManager(ApiService apiService) {
        this.apiService = apiService;
    }
}
