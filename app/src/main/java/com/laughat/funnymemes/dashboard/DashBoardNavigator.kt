package  com.laughat.funnymemes.dashboard

import com.laughat.funnymemes.base.activity.BaseNavigator

interface DashBoardNavigator : BaseNavigator {
     fun setMemesListAdapter(memesListAdapter: MemesListAdapter, function: () -> Unit)
    fun notifyAdapter()
}