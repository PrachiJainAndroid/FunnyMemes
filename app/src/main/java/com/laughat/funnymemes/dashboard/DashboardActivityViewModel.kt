package com.laughat.funnymemes.dashboard


import android.util.Log
import com.laughat.funnymemes.base.activity.BaseViewModel
import com.laughat.funnymemes.base.models.MemesItem
import com.laughat.funnymemes.base.rx.SchedulerImpl
import com.laughat.funnymemes.database.MemesDatabaseDao
import com.laughat.funnymemes.repository.remote.RepoManager
import com.laughat.funnymemes.repository.remote.network.APIInterface
import kotlinx.coroutines.*
import java.util.*

@FlowPreview
@ExperimentalCoroutinesApi
class DashBoardActivityViewModel(
    repoManager: RepoManager, schedulerImpl: SchedulerImpl,
    val dataBase: MemesDatabaseDao
) : BaseViewModel<DashBoardNavigator>(repoManager, schedulerImpl) {

    val allMemesList = dataBase.getAllValues()
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    fun fetchMemes() {
        getRepoManager()?.getApi("https://api.imgflip.com/", APIInterface::class.java)
            ?.getMemes()
            ?.subscribeOn(getAppScheduler()?.io())
            ?.observeOn(getAppScheduler()?.main())
            ?.subscribe({
                it?.let {
                    val memesList = it.body()?.data?.memes
                    uiScope.launch {
                        saveRecordstoDB(memesList!! as List<MemesItem>)
                    }

                    Log.d("Success", it.toString())
                }
            }, { Log.d("Error", it.toString()) })
    }

    private suspend fun saveRecordstoDB(memesList: List<MemesItem>) {
        withContext(Dispatchers.IO) {
            launch {
                dataBase.insertList(memesList)
            }

        }
    }

    fun setMemeListAdapter() {
        getNavigator()?.setMemesListAdapter(MemesListAdapter(allMemesList.value))
        {
            getNavigator()?.showFullImageDialog(it)


        }
    }

    fun reArrangeItems() {
        Collections.shuffle(allMemesList.value)
        getNavigator()?.notifyAdapter()
    }
}