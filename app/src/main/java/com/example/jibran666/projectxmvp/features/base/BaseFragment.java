package com.example.jibran666.projectxmvp.features.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jibran666.projectxmvp.MvpStarterApplication;
import com.example.jibran666.projectxmvp.injections.component.ConfigPersistentComponent;
import com.example.jibran666.projectxmvp.injections.component.DaggerConfigPersistentComponent;
import com.example.jibran666.projectxmvp.injections.component.FragmentComponent;
import com.example.jibran666.projectxmvp.injections.module.FragmentModule;

import java.util.concurrent.atomic.AtomicLong;

import butterknife.ButterKnife;
import timber.log.Timber;

public abstract class BaseFragment extends Fragment {
    public static final String KEY_FRAGMENT_ID = "KEY_FRAGMENT_ID";
    public static final AtomicLong NEXT_ID = new AtomicLong(0);
    public static final LongSparseArray<ConfigPersistentComponent> componentsArray = new LongSparseArray<>();

    private long fragmentId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_FRAGMENT_ID) :
                NEXT_ID.incrementAndGet();

        ConfigPersistentComponent configPersistentComponent;
        if (componentsArray.get(fragmentId) == null) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", fragmentId);
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .appComponent(MvpStarterApplication.get(getActivity()).getAppComponent())
                    .build();

            componentsArray.put(fragmentId, configPersistentComponent);
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", fragmentId);
            configPersistentComponent = componentsArray.get(fragmentId);
        }

        FragmentComponent fragmentComponent = configPersistentComponent.fragmentComponent((new FragmentModule(this)));
        inject(fragmentComponent);
        attachView();

    }


    public abstract int getLayout();

    public abstract void inject(FragmentComponent fragmentComponent);

    public abstract void attachView();

    public abstract void detachPresenter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_FRAGMENT_ID, fragmentId);
    }

    @Override
    public void onDestroy() {
        if (!getActivity().isChangingConfigurations()) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", fragmentId);
            componentsArray.remove(fragmentId);
        }
        detachPresenter();
        super.onDestroy();
    }
}
