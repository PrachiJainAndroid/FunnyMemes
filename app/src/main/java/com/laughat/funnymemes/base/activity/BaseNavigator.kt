package com.laughat.funnymemes.base.activity


import android.os.Bundle

/**
 * BaseNavigator is the Thin Layer Between View and ViewModel
 *
 * ViewModel ~~~~~~~~ View --------- Model
 *
 */
interface BaseNavigator {
    /**
     * Open Activity as Given By Class With Extras and need toDate be implement
     *
     * @param T type of Activity Class We are about toDate be Open
     * @param cls Activity Class We are about toDate be Open
     * @param extras Optional extras Which will be moved as a Bundle.
     */
    fun <T> openActivity(cls: Class<T>, extras: Bundle? = null)

    /**
     * Function Which Helps to finishActivity from a UI Element Like Activity ar Fragment
     */
    fun finishActivity()

    /**
     * Checks Id Network is Available or Not.
     *
     * @return true is Network is Available
     */
    fun isNetworkAvailable(): Boolean

}