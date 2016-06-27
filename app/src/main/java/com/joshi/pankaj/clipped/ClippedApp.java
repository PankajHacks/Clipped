package com.joshi.pankaj.clipped;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Pankaj on 26-06-2016.
 */
public class ClippedApp extends Application {
    // Used to set offline persistenceb of database for whole app
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
