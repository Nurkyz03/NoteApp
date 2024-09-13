package com.geeks.noteapp12

import android.app.Application
import com.geeks.noteapp12.utils.PreferenceHelper

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceHelper()
        sharedPreferences.unit(this)
    }
}