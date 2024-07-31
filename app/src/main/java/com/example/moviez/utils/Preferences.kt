package com.example.moviez.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    fun isFirstLaunch(): Boolean {
        return preferences.getBoolean("is_first_launch", true)
    }

    fun setFirstLaunch(isFirst: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean("is_first_launch", isFirst)
        editor.apply() // Apply changes asynchronously
    }
}
