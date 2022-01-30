package com.laughat.funnymemes.base.activity

import androidx.lifecycle.ViewModel
import com.laughat.funnymemes.base.rx.SchedulerImpl
import com.laughat.funnymemes.repository.remote.RepoManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference

open class BaseViewModel<N : BaseNavigator>(
    mrepoManager: RepoManager?,
    mschedulerImpl: SchedulerImpl?
) : ViewModel() {
    private var repoManager: RepoManager? = null
    private var schedulerImpl: SchedulerImpl? = null
    private var mNavigator: WeakReference<N?> = WeakReference(null)

    init {
        mrepoManager?.let { setRepoManager(it) }
        setSchedulerImpl(mschedulerImpl)
    }

    /**
     * sets Navigator, Must not be used Again as It getting Used by Base Components.
     *
     * @param navigator The Navigator Reference
     */
    internal fun setNavigator(navigator: N?) {
        mNavigator = WeakReference(navigator)
    }

    /**
     * Returns Navigator Of Assigned Type by View.
     *
     */
    fun getNavigator() = mNavigator.get()

    /**
     * Generic Method to Execute Computation in ViewModel
     *
     * @param T the Type of Executing Single
     * @param onSuccess Block to Be Executed when There will be a Result with the Result
     * @param onFailed Block to be Executed When There will be an Error
     */
    protected fun <T> Single<T>.executeSilent(
        onSuccess: ((T) -> Unit)?,
        onFailed: ((Throwable) -> Unit)? = null
    ): Disposable? {
        return subscribeOn(Schedulers.computation())?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({
                onSuccess?.invoke(it)
            }, {
                onFailed?.invoke(it)
            })

    }


    /**
     * returns the AppRepoManager
     */
    protected fun getRepoManager() = repoManager

    /**
     * returns the AppScheduler
     */
    protected fun getAppScheduler() = schedulerImpl

    /**
     * Set RepoManager
     */
    internal fun setRepoManager(mRepoManager: RepoManager) {
        this.repoManager = mRepoManager
    }

    /**
     * set SchedulerImpl
     */
    internal fun setSchedulerImpl(mSchedulerImpl: SchedulerImpl?) {
        this.schedulerImpl = mSchedulerImpl
    }


}