package com.martianlab.data.di

import android.app.Application
import com.martianlab.recipes.domain.api.AndroidSettingsApi
import dagger.Module
import dagger.Provides
import com.martianlab.data.sources.android_settings.AndroidSettingsImpl
import javax.inject.Singleton

@Module
class AndroidSettingsModule(private val application: Application)  {

    @Provides
    @Singleton
    fun provideAndroidSettingsApi(): AndroidSettingsApi = AndroidSettingsImpl(application)
}