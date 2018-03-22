package com.example.svinc.running;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    SharedPreferences sharedSettings;
    public SharedPref(Context context){
        sharedSettings = context.getSharedPreferences("filename",Context.MODE_PRIVATE);

    }
    public void setThemeState(Boolean state){
        SharedPreferences.Editor editor = sharedSettings.edit();
        editor.putBoolean("DarkMode",state);
        editor.commit();
    }
    public Boolean loadThemeState(){
        Boolean state = sharedSettings.getBoolean("DarkMode",false);
        return state;
    }
}
