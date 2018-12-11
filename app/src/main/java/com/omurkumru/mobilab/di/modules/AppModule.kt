package com.omurkumru.mobilab.di.modules

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import com.omurkumru.mobilab.ui.main.MainViewModelFactory
import com.omurkumru.mobilab.utils.Utils
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
    fun provideMainViewModelFactory(
            factory: MainViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(app)
}