package com.example.gistproject

import android.app.Application
import android.content.Context

class ApplicationContext: Application() {
    private var instance: ApplicationContext? = null

    override fun onCreate() {
        super.onCreate()
        ApplicationContext.appContext = applicationContext
    }
    companion object {
        lateinit  var appContext: Context
    }


}