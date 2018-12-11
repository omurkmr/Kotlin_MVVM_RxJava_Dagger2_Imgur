package com.omurkumru.mobilab.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.omurkumru.mobilab.R.layout
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        AndroidInjection.inject(this)

        mainViewModel = ViewModelProviders.of(this, mainViewModelFactory).get(
                MainViewModel::class.java)

    }
}
