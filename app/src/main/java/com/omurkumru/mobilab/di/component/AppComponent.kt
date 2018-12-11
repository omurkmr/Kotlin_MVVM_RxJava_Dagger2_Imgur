package com.omurkumru.mobilab.di.component

import com.omurkumru.mobilab.Application
import com.omurkumru.mobilab.di.modules.AppModule
import com.omurkumru.mobilab.di.modules.BuildersModule
import com.omurkumru.mobilab.di.modules.NetModule
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