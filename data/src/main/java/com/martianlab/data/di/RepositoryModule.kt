package com.martianlab.data.di

import android.app.Application
import com.martianlab.data.repository.RecipesRepositoryImpl
import dagger.Module
import dagger.Provides
import com.martianlab.data.sources.firebase.FirebaseImpl
import com.martianlab.recipes.domain.RecipesInteractor
import com.martianlab.recipes.domain.RecipesInteractorImpl
import com.martianlab.recipes.domain.RecipesRepository
import com.martianlab.recipes.domain.api.BackendApi
import com.martianlab.recipes.domain.api.DbApi
import com.martianlab.recipes.domain.api.FirebaseApi
import dagger.Binds
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun provideRecipesRepository(recipesRepositoryImpl: RecipesRepositoryImpl): RecipesRepository
}