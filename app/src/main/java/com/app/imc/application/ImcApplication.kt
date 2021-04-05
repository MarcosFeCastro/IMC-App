package com.app.imc.application

import android.app.Application
import com.app.imc.helpers.HelperDB

class ImcApplication : Application() {

    var helperDB: HelperDB? = null
        private set

    companion object {
        lateinit var instance: ImcApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        helperDB = HelperDB(this)
    }

}