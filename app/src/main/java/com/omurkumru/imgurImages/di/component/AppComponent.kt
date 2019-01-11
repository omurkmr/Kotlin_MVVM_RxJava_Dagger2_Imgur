package com.omurkumru.imgurImages.di.component

import com.omurkumru.imgurImages.Application
import com.omurkumru.imgurImages.di.modules.AppModule
import com.omurkumru.imgurImages.di.modules.BuildersModule
import com.omurkumru.imgurImages.di.modules.NetModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [AndroidInjectionModule::class, BuildersModule::class, AppModule::class, NetModule::class]
)
interface AppComponent {
    fun inject(app: Application)
}