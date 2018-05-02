package com.example.jibran666.projectxmvp.features.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.LongSparseArray;
import android.view.MenuItem;

import com.example.jibran666.projectxmvp.MvpStarterApplication;
import com.example.jibran666.projectxmvp.injections.component.ActivityComponent;
import com.example.jibran666.projectxmvp.injections.component.ConfigPersistentComponent;
import com.example.jibran666.projectxmvp.injections.component.DaggerConfigPersistentComponent;
import com.example.jibran666.projectxmvp.injections.module.ActivityModule;

import java.util.concurrent.atomic.AtomicLong;

import butterknife.ButterKnife;
import timber.log.Timber;

public abstract class BaseActivity extends AppCompatActivity {
    public static final String KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID";
    public static final AtomicLong NEXT_ID = new AtomicLong(0);
    public static final LongSparseArray<ConfigPersistentComponent> componentsArray = new LongSparseArray<>();

    private long activityId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);

        activityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) :
                NEXT_ID.incrementAndGet();

        ConfigPersistentComponent configPersistentComponent;
        if (componentsArray.get(activityId) == null) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", activityId);
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .appComponent(MvpStarterApplication.get(this).getAppComponent())
                    .build();

            componentsArray.put(activityId, configPersistentComponent);
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", activityId);
            configPersistentComponent = componentsArray.get(activityId);
        }

        ActivityComponent activityComponent = configPersistentComponent.activityComponent(new ActivityModule(this));
        inject(activityComponent);
        attachView();

    }

    public abstract int getLayout();

    public abstract void inject(ActivityComponent activityComponent);

    public abstract void attachView();

    public abstract void detachPresenter();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, activityId);
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", activityId);
            componentsArray.remove(activityId);
        }
        detachPresenter();
        super.onDestroy();
    }
}
