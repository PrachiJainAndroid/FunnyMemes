package com.laughat.funnymemes.utils

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.RequiresPermission



/**
 * get Connectivity Manager
 */
fun Context.getConnectivityManager() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
/**
 * Check if Internet is Available or not. Requires ACCESS_NETWORK_STATE Permission.
 */
@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isNetworkAvailable(): Boolean {
    val info = getConnectivityManager().activeNetworkInfo
    return info != null && info.isConnected
}

/**
 * guardRun will run your code and returns True if it executed Properly else false.
 */
fun guardRun(runnable: () -> Unit): Boolean = try {
    runnable()
    true
} catch (ignore: Exception) {
    ignore.printStackTrace()
    false
}
