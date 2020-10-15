package com.martianlab.recipes.di

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.martianlab.recipes.App
import com.martianlab.recipes.domain.api.RoutingApi
import com.martianlab.recipes.routing.RouterImpl
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference
import javax.inject.Singleton


@Module
class PresentationModule(private val application: Application )  {

    @Provides
    fun provideApplicationContext(): Context = application

    @Provides
    fun provideActivity() : WeakReference<FragmentActivity>?{
        return App.fragmentActivity
    }

    @Provides
    fun provideFragmentManager() : FragmentManager?{
        return App.fragmentManager?.get()
    }

    @Provides
    fun getRouter(router: RouterImpl) : RoutingApi
            = router

}