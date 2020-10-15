package com.martianlab.recipes.di

import com.martianlab.data.di.DataModule
import com.martianlab.recipes.App
import com.martianlab.recipes.domain.di.DomainModule
import com.martianlab.recipes.presentation.MainActivity
import com.martianlab.recipes.presentation.fragments.mainPage.MainPageViewModel
import com.martianlab.recipes.presentation.fragments.splash.SplashFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, PresentationModule::class, DomainModule::class])
interface AppComponent {

//    @Component.Builder
//    interface Builder {
//        @BindsInstance
//        fun application(application: Application): Builder
//        fun build(): AppComponent
//    }

    fun inject(app: App)
    fun inject(mainActivity: SplashFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(mainPageViewModel: MainPageViewModel)
}