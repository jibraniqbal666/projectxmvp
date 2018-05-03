package com.example.jibran666.projectxmvp.features.main;


import com.example.jibran666.projectxmvp.data.model.response.Weather;
import com.example.jibran666.projectxmvp.features.base.MvpView;

public interface MainMvp extends MvpView {
    void proceed(Weather weather);
}
