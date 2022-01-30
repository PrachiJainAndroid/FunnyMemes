package com.laughat.funnymemes.dashboard

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.laughat.funnymemes.BR
import com.laughat.funnymemes.R
import com.laughat.funnymemes.base.activity.BaseActivity
import com.laughat.funnymemes.base.models.MemesItem
import com.laughat.funnymemes.databinding.ActivityMainBinding
import com.laughat.funnymemes.utils.BigImageDialog
import kotlinx.android.synthetic.main.activity_main.*


class DashboardActivity : BaseActivity<ActivityMainBinding, DashBoardNavigator, DashBoardActivityViewModel>(),
    DashBoardNavigator  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getViewModel()?.fetchMemes()
        getViewModel()?.allMemesList?.observe(this,Observer<List<MemesItem>?>{
            Log.d("ALL Memes in DB are",it.toString())
            getViewModel()?.setMemeListAdapter()
        })

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            getViewModel()?.reArrangeItems()
        }

    }

    override fun setMemesListAdapter(
        memesListAdapter: MemesListAdapter,
        onImageViewClick: (MemesItem) -> Unit
    ) {
        memesListAdapter.setOnImageViewClickListener (onImageViewClick)
        rv_memes.adapter=memesListAdapter
    }

    override fun notifyAdapter() {
       rv_memes.adapter?.notifyDataSetChanged()
    }

    override fun getViewModelClass()=
        DashBoardActivityViewModel::class.java


    override fun getBindingVariable()= BR.viewModel

    override fun getLayoutID()=  R.layout.activity_main
    override fun showFullImageDialog(it: MemesItem) {
        BigImageDialog.newInstance(it?.url!!).show(supportFragmentManager,"")

    }
}