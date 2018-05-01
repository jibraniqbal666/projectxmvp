package com.example.jibran666.projectxmvp.injections.module;

import android.content.Context;

import com.example.jibran666.projectxmvp.BuildConfig;
import com.example.jibran666.projectxmvp.injections.ApiServiceScope;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.readystatesoftware.chuck.ChuckInterceptor;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

@Module
public class NetworkModule {

    String baseUrl;
    Context context;

    public NetworkModule(String baseUrl, Context context) {
        this.baseUrl = baseUrl;
        this.context = context;
    }

    @Provides
    @ApiServiceScope
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @ApiServiceScope
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, ChuckInterceptor chuckInterceptor, StethoInterceptor stethoInterceptor) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(chuckInterceptor)
                    .addNetworkInterceptor(stethoInterceptor);
        }
        return builder.build();

    }

    @Provides
    @ApiServiceScope
    public HttpLoggingInterceptor provideHttpLoggingIntercepter() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.d(message);
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    @ApiServiceScope
    public ChuckInterceptor provideChuckInterceptor() {
        return new ChuckInterceptor(context);
    }

    @Provides
    @ApiServiceScope
    public StethoInterceptor provideStethoInterceptor() {
        return new StethoInterceptor();
    }

    @Provides
    @ApiServiceScope
    public Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

}
