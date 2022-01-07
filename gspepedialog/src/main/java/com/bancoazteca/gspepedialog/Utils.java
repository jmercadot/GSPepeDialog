package com.bancoazteca.gspepedialog;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

class Utils {

    private static final String pregerenceName = GSPepeDialog.class.getSimpleName();
    private final SharedPreferences prefs;

    public Utils(Activity activity) {
         prefs = activity.getSharedPreferences(pregerenceName, Context.MODE_PRIVATE);
    }

    public boolean getNoMostrarDeNuevo(String simpleName) {
        return prefs.getBoolean(simpleName,false);
    }

    public void clearAll(){
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public void clearNoMostrarDeNuevo(String simpleName){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(simpleName,false);
        editor.apply();
    }

    public void setNoMostrarDeNuevo(String simpleName){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(simpleName,true);
        editor.apply();
    }
}
