package com.laughat.funnymemes.repository.remote

import androidx.lifecycle.LiveData
import com.laughat.funnymemes.database.MemesDatabaseDao
import com.google.gson.Gson
import com.laughat.funnymemes.base.models.MemesItem
import com.laughat.funnymemes.repository.local.AppSecureSharedPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.time.ExperimentalTime

class AqiRepository(
    private val sharedPreferences: AppSecureSharedPreferences,
    private val memesDatabaseDao: MemesDatabaseDao
) {

    private val gson = Gson()

    @ExperimentalCoroutinesApi
    @FlowPreview
    suspend fun getAqiDataForHome(): LiveData<List<MemesItem>?> {

        return memesDatabaseDao.getAllValues()
    }

    @ExperimentalTime
    @ExperimentalCoroutinesApi
    @FlowPreview
    suspend fun getAqiDataForCity() {
        //check later why this isnt working with higher values of debounce
        /*AqiSocket().start().sample(30000)
            .map { gson.fromJson(it, Array<AQIModel>::class.java).asList() }.collect {
                val time = Calendar.getInstance().time
                it.forEach { model -> if (model.time == null) model.time = time }
                aqiDatabaseDao.insertList(it)
            }*/
    }

    fun getAqiSubscription() = memesDatabaseDao.getAllValues()

}