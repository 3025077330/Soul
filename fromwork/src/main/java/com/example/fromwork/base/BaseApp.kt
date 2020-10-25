package com.example.fromwork.base

import android.app.Application
import android.content.Context

open class BaseApp : Application() {
    companion object instance {
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }


}