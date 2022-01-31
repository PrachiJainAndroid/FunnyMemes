package com.prachi.chatmessenger.di.module


import com.laughat.funnymemes.dashboard.DashBoardActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        DashBoardActivityViewModel(get(),get(),get())


    }


}