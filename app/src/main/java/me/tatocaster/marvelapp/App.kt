package me.tatocaster.marvelapp

import android.app.Application
import android.content.Context
import com.facebook.soloader.SoLoader

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)

        initComponents()
    }

    private fun initComponents() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        appComponent.inject(this)
    }

    companion object {
        @JvmStatic
        fun getAppContext(context: Context): App = context.applicationContext as App
    }
}