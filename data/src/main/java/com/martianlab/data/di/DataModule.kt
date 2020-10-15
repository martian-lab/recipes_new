package com.martianlab.data.di

import dagger.Module

@Module(
    includes = [
        RepositoryModule::class,
        AndroidSettingsModule::class,
        BackendModule::class,
        DbModule::class,
        PreferencesModule::class,
        AnalyticsModule::class,
        FirebaseModule::class,
    ]
)
class DataModule