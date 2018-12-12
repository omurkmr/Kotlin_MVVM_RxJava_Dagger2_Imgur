package com.omurkumru.mobilab.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.omurkumru.mobilab.R.layout
import com.omurkumru.mobilab.data.model.MainImage
import com.omurkumru.mobilab.utils.Constants
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    lateinit var mainViewModel: MainViewModel

    val nameObserver = Observer<List<MainImage>> { imageList ->
        imageList?.forEach {
            Log.i("MainActivity", "Image url = ${it.url}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        AndroidInjection.inject(this)

        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(
                MainViewModel::class.java)

        mainViewModel.imageResult.observe(this, nameObserver)
        mainViewModel.getGalleryImages(Constants.HOT, true)
    }
}
