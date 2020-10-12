package com.hsy.thisdb.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by hsy on 2019/12/09.
 */
public class SPutils {
    private static final String FILE_NAME = "this_db_info";

    public static void saveData(Context context, String key, Object data) {

        String type = data.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if ("Integer".equals(type)) {
            edit.putInt(key, (Integer) data);
        } else if ("Boolean".equals(type)) {
            edit.putBoolean(key, (Boolean) data);
        } else if ("String".equals(type)) {
            edit.putString(key, (String) data);
        } else if ("Float".equals(type)) {
            edit.putFloat(key, (Float) data);
        } else if ("Long".equals(type)) {
            edit.putLong(key, (Long) data);
        }

        edit.commit();
    }

    public static Object getData(Context context, String key, Object defValue) {

        String type = defValue.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences
                (FILE_NAME, Context.MODE_PRIVATE);
        if ("Integer".equals(type)) {
            return sharedPreferences.getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return sharedPreferences.getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(type)) {
            return sharedPreferences.getString(key, (String) defValue);
        } else if ("Float".equals(type)) {
            return sharedPreferences.getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return sharedPreferences.getLong(key, (Long) defValue);
        }

        return null;
    }
}
