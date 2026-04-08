package com.example.shiptodoor.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private SharedPreferences prefs;

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences("user_session", Context.MODE_PRIVATE);
    }

    public void saveLogin() {
        prefs.edit().putBoolean("logged_in", true).apply();
    }
    public boolean isLoggedIn() {
        return prefs.getBoolean("logged_in", false);
    }

    public void logout() {
        prefs.edit().clear().apply();
    }
}
