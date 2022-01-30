package com.prachi.airqualityindexcheck.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.amnix.xtension.AmniXtension
import com.google.gson.GsonBuilder
import com.laughat.funnymemes.base.App
import com.laughat.funnymemes.base.rx.AppSchedulers
import com.laughat.funnymemes.base.rx.SchedulerImpl
import com.laughat.funnymemes.base.toolkit.AppToolKit
import com.laughat.funnymemes.base.toolkit.ToolKit
import com.laughat.funnymemes.database.MemesDatabase
import com.laughat.funnymemes.database.MemesDatabaseDao
import com.laughat.funnymemes.repository.local.AppSecureSharedPreferences
import com.laughat.funnymemes.repository.remote.ApiPool
import com.laughat.funnymemes.repository.remote.AppRepoManager
import com.laughat.funnymemes.repository.remote.AqiRepository
import com.laughat.funnymemes.repository.remote.RepoManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit


val dataRepoModule = module {

    single {
        GsonBuilder().setLenient().create()
    }
    single<SchedulerImpl> {
        AppSchedulers()
    }

    single<SharedPreferences> {
        androidApplication().getSharedPreferences(
            androidApplication().packageName.replace(".", "_") + "_basePreferences",
            Context.MODE_PRIVATE
        )
    }


    single {
        AppSecureSharedPreferences(WeakReference(get()))
    }
    single { provideDatabase(androidApplication()) }
    single { provideAQIDao(get()) }
    single { provideAqiRepository(get(), get()) }
    single<ToolKit> {
        AppToolKit(get())
    }

    single<RepoManager> {
        AppRepoManager(
            get(named("CustomSignedRetrofit")), //Retrofit With Custom Signed SSL Cert
            get(),//RetrofitApiPool
            get()

        )
    }


    single(named("CustomSignedRetrofit")) {
        Retrofit.Builder().apply {
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            client(get(named("CustomSignedOkHttp")))
            addConverterFactory(GsonConverterFactory.create(get()))
        }
    }

    single(named("CustomSignedOkHttp")) {
        OkHttpClient.Builder().apply {
            val baseApp = androidApplication() as App
            readTimeout(baseApp.getReadTimeOutInSeconds(), TimeUnit.SECONDS)
            writeTimeout(baseApp.getWriteTimeOutInSeconds(), TimeUnit.SECONDS)
            connectTimeout(baseApp.getConnectionTimeOutInSeconds(), TimeUnit.SECONDS)
            (if (AmniXtension.isLoggingEnabled) HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("RetroFit", it)
            }).setLevel(HttpLoggingInterceptor.Level.BODY) else null)?.let {
                addInterceptor(it)
            }

        }.build()
    }

    single {
        ApiPool()
    }



}

fun provideDatabase(application: Application): MemesDatabase {
    return MemesDatabase.getInstance(application)
}

fun provideAQIDao(database: MemesDatabase): MemesDatabaseDao {
    return database.memesDatabaseDao
}

fun provideAqiRepository(
    sharedPreferences: AppSecureSharedPreferences,
    memesDatabaseDao: MemesDatabaseDao
) = AqiRepository(sharedPreferences, memesDatabaseDao)


