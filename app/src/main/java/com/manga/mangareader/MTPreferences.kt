package com.manga.mangareader

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.manga.mangareader.MTConstants

object MTPreferences {
    private fun getEditor(context: Context): SharedPreferences.Editor {
        val sharedPreferences = context.getSharedPreferences(
            MTConstants.PACKAGE_NAME, Context.MODE_PRIVATE
        )
        return sharedPreferences.edit()
    }

    private fun getSharedPref(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            MTConstants.PACKAGE_NAME, Context.MODE_PRIVATE
        )
    }

    fun storeTheme(context: Context, theme: Int) {
        getEditor(context).putInt(MTConstants.SETTINGS_THEME, theme).apply()
    }

    fun getTheme(context: Context): Int {
        return getSharedPref(context).getInt(MTConstants.SETTINGS_THEME, R.color.blue)
    }

    fun storeThemeMode(context: Context, theme: Int) {
        getEditor(context).putInt(MTConstants.SETTINGS_THEME_MODE, theme).apply()
    }

    fun getThemeMode(context: Context): Int {
        return getSharedPref(context).getInt(
            MTConstants.SETTINGS_THEME_MODE,
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        )
    }
}