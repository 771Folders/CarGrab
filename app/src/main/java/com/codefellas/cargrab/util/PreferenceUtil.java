package com.codefellas.cargrab.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {

    private final SharedPreferences sharedPreferences;

    public PreferenceUtil(Context context) {
        this.sharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
    }

    public void setData(String data) {
        sharedPreferences.edit().putString("id", data).apply();
    }
}