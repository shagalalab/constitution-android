package com.shagalalab.constitution

import android.app.Application
import com.shagalalab.constitution.injectors.dataModule
import com.shagalalab.constitution.injectors.viewModelModule
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
