package me.tatocaster.marvelapp

import android.content.Context
import android.content.res.Resources
import dagger.Component
import me.tatocaster.marvelapp.data.DataComponent
import me.tatocaster.marvelapp.data.DataModule
import java.util.*
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, DataModule::class))
interface AppComponent : DataComponent{

    fun inject(app: App)

    // expose dependencies to scope graph
    fun exposeAppContext(): Context

    fun exposeResources(): Resources

    fun exposeLocale(): Locale

}