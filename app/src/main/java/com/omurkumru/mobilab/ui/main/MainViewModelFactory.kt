package com.omurkumru.mobilab.ui.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject


class MainViewModelFactory @Inject constructor(
        private val mainViewModel: MainViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java!!)) {
            return mainViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}