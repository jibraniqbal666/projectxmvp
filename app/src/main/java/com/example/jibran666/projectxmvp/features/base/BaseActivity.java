package com.example.jibran666.projectxmvp.features.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.LongSparseArray;

import com.example.jibran666.projectxmvp.injections.component.ActivityComponent;
import com.example.jibran666.projectxmvp.injections.component.ConfigPersistentComponent;

import java.util.concurrent.atomic.AtomicLong;

public abstract class BaseActivity extends AppCompatActivity {
    public static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    public static final AtomicLong NEXT_ID = new AtomicLong(0);
    public static final LongSparseArray<ConfigPersistentComponent> componentArray = new LongSparseArray<>();

    private long activityId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
    }

    public abstract int getLayout();

    public abstract void inject(ActivityComponent activityComponent);

    public abstract void attachView();

    public abstract void detachPresenter();

}
