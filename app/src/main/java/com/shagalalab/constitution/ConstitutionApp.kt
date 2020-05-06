package com.shagalalab.constitution

import android.app.Application
import com.shagalalab.constitution.di.dataModule
import com.shagalalab.constitution.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ConstitutionApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val modules = listOf(dataModule, viewModelModule)
        startKoin {
            androidLogger()
            androidContext(this@ConstitutionApp)
            androidFileProperties()
            modules(modules)
        }
    }
}
