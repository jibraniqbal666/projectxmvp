package com.example.jibran666.projectxmvp.injections.module;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    public Fragment provideFragment(){
        return fragment;
    }

    @Provides
    public Activity provideActivity(){
        return fragment.getActivity();
    }


    @Provides
    public Context provideContext(){
        return fragment.getContext();
    }
}
