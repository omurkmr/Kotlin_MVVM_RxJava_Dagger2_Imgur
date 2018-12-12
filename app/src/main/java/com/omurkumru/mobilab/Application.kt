package com.omurkumru.mobilab

import android.app.Activity
import android.app.Application
import com.omurkumru.mobilab.di.component.DaggerAppComponent
import com.omurkumru.mobilab.di.modules.AppModule
import com.omurkumru.mobilab.di.modules.NetModule
import com.omurkumru.mobilab.utils.Constants
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class Application : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule(Constants.BASE_URL))
                .build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}