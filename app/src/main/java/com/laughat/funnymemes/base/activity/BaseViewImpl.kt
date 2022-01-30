package com.laughat.funnymemes.base.activity


import androidx.databinding.ViewDataBinding


internal interface BaseViewImpl<B : ViewDataBinding, N : BaseNavigator,V : BaseViewModel<N>> {
    /**
     * helps you toDate hide Keyboard fromDate Current Focus VIew.
     */
    fun hideKeyboard()

    /**
     * perform DataBinding for the Activity
     */
    fun performDataBinding()


    /**
     * Return ViewModel used by View
     */
    fun getViewModel(): V?

    /**
     * Return View Binding
     */
    fun getViewDataBindings(): B?
}