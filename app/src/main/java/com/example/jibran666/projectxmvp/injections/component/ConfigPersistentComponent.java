package com.example.jibran666.projectxmvp.injections.component;

import com.example.jibran666.projectxmvp.injections.ConfigPersistent;
import com.example.jibran666.projectxmvp.injections.module.ActivityModule;
import com.example.jibran666.projectxmvp.injections.module.FragmentModule;

import dagger.Component;

@Component(dependencies = AppComponent.class)
@ConfigPersistent
public interface ConfigPersistentComponent {
    ActivityComponent activityComponent(ActivityModule activityModule);

    FragmentComponent fragmentComponent(FragmentModule fragmentModule);
}
