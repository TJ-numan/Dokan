package com.tjnuman.dokan.Prevalent;

import android.content.Context;
import android.content.SharedPreferences;

public class Sessions {


    public static void isLoginUser(Context context, boolean loginStatusofUser)
    {
        SharedPreferences pref = context.getSharedPreferences("LOGIN_STATUS",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("login_status",loginStatusofUser);
        editor.apply();
    }

    public static void isLoginAdmin(Context context, boolean loginStatusofAdmin)
    {
        SharedPreferences prefOfAdmin = context.getSharedPreferences("LOGIN_STATUS_OF_ADMIN",Context.MODE_PRIVATE);
        SharedPreferences.Editor editorofAdmin = prefOfAdmin.edit();
        editorofAdmin.putBoolean("login_status_of_admin",loginStatusofAdmin);
        editorofAdmin.apply();
    }

    public static boolean getLoginStatusofUser(Context context){
        SharedPreferences pref = context.getSharedPreferences("LOGIN_STATUS",Context.MODE_PRIVATE);
        return pref.getBoolean("login_status",false);
    }

    public static boolean getLoginStatusofAdmin(Context context){
        SharedPreferences prefOfAdmin = context.getSharedPreferences("LOGIN_STATUS_OF_ADMIN",Context.MODE_PRIVATE);
        return prefOfAdmin.getBoolean("LOGIN_STATUS_OF_ADMIN",false);
    }

}
