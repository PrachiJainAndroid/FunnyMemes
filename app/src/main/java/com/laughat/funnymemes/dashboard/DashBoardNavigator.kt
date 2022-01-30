package  com.laughat.funnymemes.dashboard

import com.laughat.funnymemes.base.activity.BaseNavigator
import com.laughat.funnymemes.base.models.MemesItem

interface DashBoardNavigator : BaseNavigator {
     fun setMemesListAdapter(memesListAdapter: MemesListAdapter, onImageViewClick: (MemesItem) -> Unit)
    fun notifyAdapter()
     fun showFullImageDialog(it: MemesItem)
}