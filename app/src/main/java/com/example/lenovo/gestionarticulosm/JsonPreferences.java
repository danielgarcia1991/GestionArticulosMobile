package com.example.lenovo.gestionarticulosm;

import android.content.Context;
import android.content.SharedPreferences;

public class JsonPreferences {

    //- TAG for logging
    private static final String TAG = "JsonPreferences";

    //- Shared preferences instance
    private SharedPreferences sharedPreferences;

    //- Singleton instance
    private static JsonPreferences instance = null;

    //- File name for shared file
    private static final String SHARED_FILE = "SHRD_FILE_GESTION";

    //- Attributes in shared file
    private static final String USER = "USER";
    private static final String EMPTY = "";


    /**
     * Singleton pattern: create new instance for manipulating
     * data application
     * @param context for actual activity
     * @return a instance for manager shared preferences
     */
    public static JsonPreferences getInstance(Context context) {

        if ( instance == null ) {
            instance = new JsonPreferences(context);
        }
        return instance;
    }

    /**
     * Constructor
     * @param context for actual activity
     */
    public JsonPreferences(Context context) {
        sharedPreferences =
                context.getSharedPreferences(SHARED_FILE, Context.MODE_PRIVATE);
    }

    /**
     * Get saved version on shared file
     * @return Version saved on shared file got from JSON streaming.
     * Empty string if key does not exists
     */
    public String getUser() {
        return sharedPreferences.getString(USER, EMPTY);
    }


    public void setUser(String userEmail) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER, userEmail);
        editor.apply();
    }



}
