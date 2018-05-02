package com.example.jibran666.projectxmvp.data.local;

import android.content.SharedPreferences;

import com.example.jibran666.projectxmvp.injections.ApiServiceScope;

import javax.annotation.Nonnull;
import javax.inject.Inject;

@ApiServiceScope
public class PreferencesHelper {

    private static final String TAG = "PreferencesHelper";
    private final SharedPreferences preferences;


    @Inject
    PreferencesHelper(SharedPreferences sharedPreferences) {
        preferences = sharedPreferences;

    }

    public void putString(@Nonnull String key, @Nonnull String value) {
        preferences.edit().putString(key, value).apply();
    }

    public String getString(@Nonnull String key) {
        return preferences.getString(key, "");
    }

    public void putBoolean(@Nonnull String key, @Nonnull boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(@Nonnull String key) {
        return preferences.getBoolean(key, false);
    }

    public void putInt(@Nonnull String key, @Nonnull boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    public int getInt(@Nonnull String key) {
        return preferences.getInt(key, -1);
    }

    public void clear() {
        preferences.edit().clear().apply();
    }

    public void removeKey(String key) {
        preferences.edit().remove(key).apply();
    }

    public boolean exists(@Nonnull String key) {
        return preferences.contains(key);
    }

}
