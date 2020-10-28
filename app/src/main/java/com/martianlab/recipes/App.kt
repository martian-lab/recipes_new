package com.martianlab.recipes

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.martianlab.data.di.*
import com.martianlab.recipes.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.lang.ref.WeakReference

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, dataModule, domainModule)
        }
    }

    companion object {
        lateinit var context: Context
        var fragmentManager : WeakReference<FragmentManager>? = null
        var fragmentActivity : WeakReference<FragmentActivity>? = null
    }
}