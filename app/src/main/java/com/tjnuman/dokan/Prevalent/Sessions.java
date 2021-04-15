package com.tjnuman.dokan.Prevalent;

import android.content.Context;
import android.content.SharedPreferences;

public class Sessions {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;


    public static void isLoginUser(Context context, boolean loginStatus)
    {
        SharedPreferences pref = context.getSharedPreferences("LOGIN_STATUS",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("login_status",loginStatus);
        editor.apply();
    }

    public static boolean getLoginStatusofUser(Context context){
        SharedPreferences pref = context.getSharedPreferences("LOGIN_STATUS",Context.MODE_PRIVATE);
        return pref.getBoolean("login_status",false);
    }


    public static void isLoginAdmin(Context context, boolean loginStatus)
    {
        SharedPreferences pref = context.getSharedPreferences("LOGIN_STATUS",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("login_status",loginStatus);
        editor.apply();
    }

    public static boolean getLoginStatusofAdmin(Context context){
        SharedPreferences pref = context.getSharedPreferences("LOGIN_STATUS",Context.MODE_PRIVATE);
        return pref.getBoolean("login_status",false);
    }

}
