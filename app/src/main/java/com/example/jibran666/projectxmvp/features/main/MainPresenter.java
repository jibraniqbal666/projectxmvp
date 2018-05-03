package com.example.jibran666.projectxmvp.features.main;

import android.util.Log;


import com.example.jibran666.projectxmvp.data.DataManager;
import com.example.jibran666.projectxmvp.features.base.BasePresenter;
import com.example.jibran666.projectxmvp.util.rx.scheduler.SchedulerUtils;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainMvp> {

    private static final String TAG = "MainPresenter";
    private final DataManager dataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void getWeather() {
        checkViewAttached();
        dataManager.getWeather("London")
                .compose(SchedulerUtils.ioToMain())
                .subscribe(response -> {
                            if (response.isSuccessful()) {
                                getView().proceed(response.body());
                            }
                        },
                        throwable -> {
                            getView().proceed(null);
                            Log.e(TAG, throwable.getMessage());
                        }
                );
    }
}
