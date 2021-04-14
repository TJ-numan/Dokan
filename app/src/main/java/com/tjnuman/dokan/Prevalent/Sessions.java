package com.tjnuman.dokan.Prevalent;

import android.content.Context;
import android.content.SharedPreferences;

public class Sessions {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public Sessions() {
    }

    public Sessions(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("userLoginSassion",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void isLogin(Context context, boolean loginStatus)
    {
        SharedPreferences pref = context.getSharedPreferences("LOGIN_STATUS",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("login_status",loginStatus);
        editor.apply();
    }

    public static boolean getLoginStatus(Context context){
        SharedPreferences pref = context.getSharedPreferences("LOGIN_STATUS",Context.MODE_PRIVATE);
        return pref.getBoolean("login_status",false);
    }
}
