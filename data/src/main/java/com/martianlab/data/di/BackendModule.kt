package com.martianlab.data.di

import android.app.Application
import com.martianlab.recipes.domain.api.BackendApi
import com.martianlab.data.sources.backend.BackendImpl
import com.martianlab.data.sources.backend.BackendKtorImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BackendModule(
    private val application: Application
) {

    @Singleton
    @Provides
    internal fun provideBackendApi(): BackendApi {
        return BackendKtorImpl()
    }
}