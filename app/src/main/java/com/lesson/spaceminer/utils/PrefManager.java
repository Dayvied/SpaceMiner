package com.lesson.spaceminer.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by spaceminer on 25/10/2022.
 */

public class PrefManager {
    // Shared preferences file name
    private static final String PREF_NAME = "spaceminer";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_FIRST_TIME_TURN_ON = "IsFirstTimeTurnOn";
    private static final String CART_ORDER = "CART_ORDER";
    private static final String STORE_ID = "storeId";
    private static final String STORE_NAME = "storeName";
    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;
    // shared pref mode
    private final int PRIVATE_MODE = 0;

    public PrefManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public void setStringItem(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String getStringItem(String key) {
        return pref.getString(key, Constants.EMPTY_TEXT);
    }

    public void removeStringItem(String key) {
        editor.remove(key);
        editor.commit();
    }

    public void setBooleanItem(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public Boolean getBooleanItem(String key) {
        return pref.getBoolean(key,false);
    }


}
