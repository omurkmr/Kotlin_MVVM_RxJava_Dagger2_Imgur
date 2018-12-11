package com.omurkumru.mobilab

import android.app.Activity
import android.app.Application
import com.omurkumru.mobilab.di.component.DaggerAppComponent
import com.omurkumru.mobilab.di.modules.AppModule
import com.omurkumru.mobilab.di.modules.NetModule
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
                .netModule(NetModule("http://www.test.com"))
                .build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}