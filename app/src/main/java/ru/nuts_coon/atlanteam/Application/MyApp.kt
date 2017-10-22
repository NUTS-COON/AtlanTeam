package ru.nuts_coon.atlanteam.Application

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import ru.nuts_coon.atlanteam.di.ApplicationComponent
import ru.nuts_coon.atlanteam.di.ApplicationModule
import ru.nuts_coon.atlanteam.di.DaggerApplicationComponent


class MyApp : Application() {

    companion object {
        lateinit var component: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeRealm()
        initializeDagger()
    }

    private fun initializeRealm() {
        Realm.init(this)

        val configBuilder= RealmConfiguration.Builder().name("MyRealm").build()

        Realm.setDefaultConfiguration(configBuilder)
    }

    fun initializeDagger(){
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule())
                .build()
    }

}