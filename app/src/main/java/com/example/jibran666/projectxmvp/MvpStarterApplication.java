package com.example.jibran666.projectxmvp;

import android.app.Application;
import android.content.Context;

import com.example.jibran666.projectxmvp.injections.component.AppComponent;
import com.example.jibran666.projectxmvp.injections.component.DaggerAppComponent;
import com.example.jibran666.projectxmvp.injections.module.AppModule;
import com.example.jibran666.projectxmvp.injections.module.NetworkModule;
import com.facebook.stetho.Stetho;
import com.singhajit.sherlock.core.Sherlock;
import com.squareup.leakcanary.LeakCanary;
import com.tspoon.traceur.Traceur;

import timber.log.Timber;

public class MvpStarterApplication extends Application {
    AppComponent appComponent;

    public static MvpStarterApplication get(Context context) {
        return (MvpStarterApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
            LeakCanary.install(this);
            Sherlock.init(this);
            Traceur.enableLogging();
        }

    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .networkModule(new NetworkModule(BuildConfig.BASE_API_URL, this))
                    .build();
        }
        return appComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }
}
