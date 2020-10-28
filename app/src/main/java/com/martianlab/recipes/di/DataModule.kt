package com.martianlab.recipes.di

import android.content.Context
import com.martianlab.data.repository.RecipesRepositoryImpl
import com.martianlab.data.sources.backend.BackendKtorImpl
import com.martianlab.data.sources.db_new.DatabaseDriverFactory
import com.martianlab.recipes.App
import com.martianlab.recipes.domain.RecipesRepository
import com.martianlab.recipes.domain.api.BackendApi
import com.martianlab.recipes.domain.api.DbApi
import com.martianlab.recipes.domain.api.RoutingApi
import com.martianlab.recipes.routing.RouterImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single { provideApiService() }
    single { provideDb(androidContext()) }
    single { provideRepository(get(), get()) }

}

private fun provideRepository(backendApi: BackendApi, dbApi: DbApi) : RecipesRepository =
    RecipesRepositoryImpl(dbApi, backendApi)

private fun provideApiService() : BackendApi
        = BackendKtorImpl()

private fun provideDb(context: Context) : DbApi =
    DatabaseDriverFactory(context)
