package com.laughat.funnymemes.splashscreen

import com.laughat.funnymemes.base.activity.BaseViewModel
import com.laughat.funnymemes.base.rx.SchedulerImpl
import com.laughat.funnymemes.repository.remote.RepoManager

class SplashViewModel(repoManager: RepoManager, schedulerImpl: SchedulerImpl) :
    BaseViewModel<SplashNavigator>(repoManager, schedulerImpl) {

}