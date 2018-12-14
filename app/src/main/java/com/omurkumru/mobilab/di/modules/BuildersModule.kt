package com.omurkumru.mobilab.di.modules

import com.omurkumru.mobilab.ui.detail.DetailActivity
import com.omurkumru.mobilab.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity
}