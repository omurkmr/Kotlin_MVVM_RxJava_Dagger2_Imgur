package com.omurkumru.imgurImages.di.modules

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import com.omurkumru.imgurImages.ui.ViewModelFactory
import com.omurkumru.imgurImages.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideViewModelFactory(
            factory: ViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(app)
}