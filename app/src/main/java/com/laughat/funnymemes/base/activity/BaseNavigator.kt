package com.laughat.funnymemes.base.activity


import android.content.res.AssetManager
import android.content.res.Resources
import android.os.Bundle
import retrofit2.Response

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
     * Invokes when There is An Error From Network Call.
     * ViewModel is Responsible to Call it so Far.
     *
     * @param error the Error Message From Invoke Source
     * @param t Nullable Throwable
     *
     */
    @Deprecated(
        "Use BaseViewModel.onApiFailed",
        ReplaceWith("BaseViewModel.OnError"),
        DeprecationLevel.WARNING
    )
    fun onError(error: String, t: Throwable? = null)

    /**
     * Invokes when There is An Error From Network Call.
     * ViewModel is Responsible to Call it so Far.
     *
     * @param response the Response From Server Which is Nullable
     * @param t Nullable Throwable
     */
    @Deprecated(
        "Use BaseViewModel.onApiFailed, Will be Remove In Next release",
        ReplaceWith("BaseViewModel.onApiFailed"),
        DeprecationLevel.WARNING
    )
    fun onApiFailed(response: Response<*>?, t: Throwable)



    /**
     * Returns a Resources instance for the application's package.
     * <p>
     * <strong>Note:</strong> Implementations of this method should return
     * a Resources instance that is consistent with the AssetManager instance
     * returned by {@link #getAssets()}. For example, they should share the
     * same {@link Configuration} object.
     *
     * @return a Resources instance for the application's package
     * @see #getAssets()
     */
    fun getAppResources(): Resources?

    /**
     * Retrieve underlying AssetManager storage for these resources.
     */
    fun getAppAssets(): AssetManager?

    /**
     * Want to Confirm the User Action? Just call showConfirmationDialog with required + optional params to do so.
     */
    fun showConfirmationDialog(
        msg: String,
        onResponse: (result: Boolean) -> Unit,
        positiveText: String = "Yes",
        negativedText: String = "No",
        cancelable: Boolean = false
    )

    /**
     * Request Permission WithOut Waiting For Any OnPermissionResult Callback.
     *
     * Get The Result in a Callback Easily.
     * No need to check if the Permission Grated Already Or Not, We Will do it for you. Just Place the code in [callback] Block, We will Execute it SomeHow.
     * Its Based on LifeCycleObserver So Supported FragmentActivity+
     *
     * @property permission is the Permission you want to Request For
     * @property callback is the Block Which Will be Executed On Permission Granted.
     *
     *
     * * Only Supports One Permission at a time. Contributors will be welcomed
     */
    fun requestPermission(permission: String, callback: (Boolean) -> Unit)

    /**
     * Checks Id Network is Available or Not.
     *
     * @return true is Network is Available
     */
    fun isNetworkAvailable(): Boolean

}