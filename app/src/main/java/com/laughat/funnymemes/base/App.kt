package com.laughat.funnymemes.base

import android.app.Application
import com.prachi.airqualityindexcheck.di.module.dataRepoModule
import com.prachi.chatmessenger.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App:Application() {
    override fun onCreate() {
        super.onCreate()
     startKoin {
            androidContext(this@App)
           modules(listOf(viewModelModule,dataRepoModule))
        }
    }

    /**
     *  get Read TimeOut In Seconds
     */
    open fun getReadTimeOutInSeconds() = 90L

    /**
     *  get Write TimeOut In Seconds
     */
    open fun getWriteTimeOutInSeconds() = 90L

    /**
     *  get Connection TimeOut In Seconds
     */
    open fun getConnectionTimeOutInSeconds() = 90L
}