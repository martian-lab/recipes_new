package com.martianlab.data.di

import android.app.Application
import dagger.Module
import dagger.Provides
import com.martianlab.data.sources.firebase.FirebaseImpl
import com.martianlab.recipes.domain.api.FirebaseApi
import javax.inject.Singleton

@Module
class FirebaseModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideFirebaseApi(): FirebaseApi = FirebaseImpl(application)
}