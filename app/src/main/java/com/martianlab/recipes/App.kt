package com.martianlab.recipes

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.martianlab.data.di.*
import com.martianlab.recipes.di.AppComponent
import com.martianlab.recipes.di.DaggerAppComponent
import com.martianlab.recipes.di.PresentationModule
import java.lang.ref.WeakReference

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent
            .builder()
            .presentationModule(PresentationModule(this))
            .preferencesModule(PreferencesModule(this))
            .analyticsModule(AnalyticsModule(this))
            .firebaseModule(FirebaseModule(this))
            .dbModule(DbModule(this))
            .androidSettingsModule(AndroidSettingsModule(this))
            .backendModule(BackendModule(this))
            .build()
            .apply { inject(this@App) }

        println("DAGGER:::, comp=" + component)
    }

    fun setFragmentManager(manager: FragmentManager) {
        fragmentManager = WeakReference(manager)
    }

    fun getFragmentManager()  = fragmentManager?.get()

    companion object {
        lateinit var context: Context
        lateinit var component : AppComponent
        var fragmentManager : WeakReference<FragmentManager>? = null
        var fragmentActivity : WeakReference<FragmentActivity>? = null
    }
}