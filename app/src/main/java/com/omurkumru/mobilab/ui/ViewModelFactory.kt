package com.omurkumru.mobilab.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.omurkumru.mobilab.ui.detail.DetailViewModel
import com.omurkumru.mobilab.ui.main.MainViewModel
import javax.inject.Inject


class ViewModelFactory @Inject constructor(
        private val mainViewModel: MainViewModel, private val detailViewModel: DetailViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return mainViewModel as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return detailViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}