package com.example.jibran666.projectxmvp.injections.component;

import com.example.jibran666.projectxmvp.features.main.MainActivity;
import com.example.jibran666.projectxmvp.injections.PerActivity;
import com.example.jibran666.projectxmvp.injections.module.ActivityModule;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
