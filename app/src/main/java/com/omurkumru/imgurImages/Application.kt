package com.omurkumru.imgurImages

import android.app.Activity
import android.app.Application
import com.omurkumru.imgurImages.di.component.DaggerAppComponent
import com.omurkumru.imgurImages.di.modules.AppModule
import com.omurkumru.imgurImages.di.modules.NetModule
import com.omurkumru.imgurImages.utils.ImgurConstants
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
                .netModule(NetModule(ImgurConstants.BASE_URL))
                .build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}