package com.example.jibran666.projectxmvp.features.main;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.example.jibran666.projectxmvp.R;
import com.example.jibran666.projectxmvp.data.model.response.Weather;
import com.example.jibran666.projectxmvp.features.base.BaseActivity;
import com.example.jibran666.projectxmvp.injections.ConfigPersistent;
import com.example.jibran666.projectxmvp.injections.component.ActivityComponent;

import javax.inject.Inject;

import butterknife.BindView;

@ConfigPersistent
public class MainActivity extends BaseActivity implements MainMvp {

    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.weather)
    TextView textViewWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPresenter.getWeather();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    public void attachView() {
        mainPresenter.attachView(this);
    }

    @Override
    public void detachPresenter() {
        mainPresenter.detachView();
    }

    @Override
    public void proceed(Weather weather) {
        if(weather != null)
            textViewWeather.setText(weather.getName() + " " + weather.getWeather().get(0).getDescription());
        else
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
    }
}
