package com.martianlab.data.di

import android.app.Application
import com.martianlab.data.repository.ThemeMapper
import com.martianlab.data.sources.preferences.PreferencesImpl
import com.martianlab.recipes.domain.api.AndroidSettingsApi
import com.martianlab.recipes.domain.api.PreferencesApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun providePreferencesApi(
        androidSettingsApi: AndroidSettingsApi,
        themeMapper: ThemeMapper
    ): PreferencesApi = with(themeMapper) {
        PreferencesImpl(application, androidSettingsApi.systemTheme.toPreference())
    }
}