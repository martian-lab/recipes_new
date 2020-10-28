package com.martianlab.data.di

import com.martianlab.data.repository.RecipesRepositoryImpl
import dagger.Module
import com.martianlab.recipes.domain.RecipesRepository
import dagger.Binds
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    internal abstract fun provideRecipesRepository(recipesRepositoryImpl: RecipesRepositoryImpl): RecipesRepository
}