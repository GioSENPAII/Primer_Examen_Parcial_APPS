package com.example.binarioeducativo

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

object ThemeManager {
    private const val PREFS_NAME = "theme_prefs"
    private const val KEY_THEME = "theme_option"

    const val THEME_GUINDA = 0
    const val THEME_AZUL = 1

    fun setTheme(activity: AppCompatActivity, themeOption: Int) {
        saveThemeOption(activity, themeOption)
        applyTheme(activity, themeOption)
    }

    fun applyTheme(activity: AppCompatActivity, themeOption: Int) {
        when (themeOption) {
            THEME_GUINDA -> activity.setTheme(R.style.Theme_BinarioEducativo_Guinda)
            THEME_AZUL -> activity.setTheme(R.style.Theme_BinarioEducativo_Azul)
        }
    }

    fun getThemeOption(context: Context): Int {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(KEY_THEME, THEME_GUINDA)
    }

    private fun saveThemeOption(context: Context, themeOption: Int) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putInt(KEY_THEME, themeOption).apply()
    }
}