package com.example.jibran666.projectxmvp.injections.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.jibran666.projectxmvp.Constants;
import com.example.jibran666.projectxmvp.injections.ApplicationContext;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ApiModule.class})
public class AppModule {
    private Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Provides
    @ApplicationContext
    public Context provideContext(Application application){
        return application;
    }

    @Provides
    public Application provideApplication(){
        return application;
    }

    @Provides
    public SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences(Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);
    }
}
