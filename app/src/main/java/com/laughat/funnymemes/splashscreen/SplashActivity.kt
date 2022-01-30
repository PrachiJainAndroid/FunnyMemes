package com.laughat.funnymemes.splashscreen

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.laughat.funnymemes.BR
import com.laughat.funnymemes.R
import com.laughat.funnymemes.base.activity.BaseActivity
import com.laughat.funnymemes.databinding.ActivityMainBinding
import android.content.Intent
import android.os.Handler

import android.os.Looper
import com.amnix.xtension.extensions.startActivity
import com.laughat.funnymemes.dashboard.DashboardActivity


class SplashActivity : BaseActivity<ActivityMainBinding, SplashNavigator, SplashViewModel>(),
    SplashNavigator {
    /*Duration of wait*/
     val SPLASH_DISPLAY_LENGTH = 2000L
    override fun getViewModelClass()= SplashViewModel::class.java


    override fun getBindingVariable()= BR.viewModel

    override fun getLayoutID()=  R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow()
            .setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed(Runnable { /* Create an Intent that will start the MainActivity. */
          openActivity(DashboardActivity::class.java)
            finish()
        }, SPLASH_DISPLAY_LENGTH)




    }
}