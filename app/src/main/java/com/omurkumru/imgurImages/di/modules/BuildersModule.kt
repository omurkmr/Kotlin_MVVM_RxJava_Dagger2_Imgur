package com.omurkumru.imgurImages.di.modules

import com.omurkumru.imgurImages.ui.detail.DetailActivity
import com.omurkumru.imgurImages.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity
}