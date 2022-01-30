package  com.laughat.funnymemes.base.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.amnix.xtension.extensions.isNetworkAvailable
import org.koin.androidx.viewmodel.ext.android.getViewModel
import com.laughat.funnymemes.utils.guardRun as guardRun1


abstract class BaseActivity<B : ViewDataBinding, N : BaseNavigator, V : BaseViewModel<N>> :
    AppCompatActivity() , BaseViewImpl<B, N, V>, BaseNavigator {

    private var mViewModel: V? = null
    private var viewDataBinding: B? = null
    protected abstract fun getViewModelClass(): Class<V>

    protected abstract fun getBindingVariable(): Int



    abstract fun getLayoutID():Int

    @Suppress("DEPRECATION")
    override fun getViewModel(): V? {
        return this.mViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewDataBindingObj = DataBindingUtil.inflate<B>(
            LayoutInflater.from(this@BaseActivity), getLayoutID(), null, false
        )
        this.mViewModel = getViewModel<V>(null,getViewModelClass().kotlin)
        viewDataBindingObj.setVariable(getBindingVariable(), mViewModel)
        setContentView(viewDataBindingObj.root)
        guardRun1 {
            @Suppress("UNCHECKED_CAST")
            getViewModel()?.setNavigator(this as N?)
        }


    }

    /**
     * Check if Internet is Available or not. Requires ACCESS_NETWORK_STATE Permission.
     */
     override fun isNetworkAvailable() = (this as AppCompatActivity).isNetworkAvailable()


    /**
     * Open Activity as Given By Class With Extras and need toDate be implement
     *
     * @param T type of Activity Class We are about toDate be Open
     * @param cls Activity Class We are about toDate be Open
     * @param extras Optional extras Which will be moved as a Bundle.
     */
     override fun <T> openActivity(cls: Class<T>, extras: Bundle?) {
        Intent(this, cls).apply {
            if (extras != null)
                putExtras(extras)
            startActivity(this)
        }
    }



    override fun finishActivity() {
        finish()
    }

    override fun performDataBinding() {
        FrameLayout(this).apply {
            @Suppress("DEPRECATION")
            viewDataBinding =
                DataBindingUtil.inflate(
                    LayoutInflater.from(this@BaseActivity),
                    getLayoutID(),
                    this,
                    false
                )
            addView(getViewDataBindings()?.root)
            setContentView(this)
    }}

    override fun getViewDataBindings(): B? {
        return this.viewDataBinding
    }

    /**
     * Setup ToolBar with the Flag of enableBackButton
     *
     * @param enableBackButton true if you want to Enable backButton
     */
    protected fun setUpToolBar(@IdRes toolBarId: Int, enableBackButton: Boolean = false) {
        val toolbar = findViewById<Toolbar>(toolBarId)
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(enableBackButton)

            toolbar.setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }




}