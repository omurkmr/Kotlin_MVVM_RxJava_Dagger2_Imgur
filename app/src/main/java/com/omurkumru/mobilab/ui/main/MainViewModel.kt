package com.omurkumru.mobilab.ui.main

import android.arch.lifecycle.ViewModel
import com.omurkumru.mobilab.data.source.MainRepository
import javax.inject.Inject


class MainViewModel @Inject constructor(
        private val mainRepository: MainRepository) : ViewModel() {

}