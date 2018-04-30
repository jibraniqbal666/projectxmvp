package com.example.jibran666.projectxmvp.injections.module;


import android.app.Activity;
import android.content.Context;

import com.example.jibran666.projectxmvp.injections.ActivityContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    public Context provideContext(){
        return activity;
    }

    @Provides
    public Activity provideActivity(){
        return activity;
    }
}
