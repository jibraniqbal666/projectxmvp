package com.example.jibran666.projectxmvp.injections.component;

import android.app.Application;
import android.content.Context;

import com.example.jibran666.projectxmvp.data.DataManager;
import com.example.jibran666.projectxmvp.injections.ApiServiceScope;
import com.example.jibran666.projectxmvp.injections.ApplicationContext;
import com.example.jibran666.projectxmvp.injections.module.AppModule;

import dagger.Component;

@ApiServiceScope
@Component(modules = {AppModule.class})
public interface AppComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager apiManager();

}
