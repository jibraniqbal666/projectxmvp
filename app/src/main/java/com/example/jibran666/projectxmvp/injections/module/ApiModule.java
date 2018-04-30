package com.example.jibran666.projectxmvp.injections.module;

import com.example.jibran666.projectxmvp.data.remote.Firebase.ApiService;
import com.example.jibran666.projectxmvp.injections.ApiServiceScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {NetworkModule.class})
public class ApiModule {

    @Provides
    @ApiServiceScope
    public ApiService provideRetrofit(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }
}
