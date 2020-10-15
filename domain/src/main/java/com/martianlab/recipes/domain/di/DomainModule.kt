package com.martianlab.recipes.domain.di

import com.martianlab.recipes.domain.RecipesInteractor
import com.martianlab.recipes.domain.RecipesInteractorImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DomainModule {

    @Binds
    @Singleton
    internal abstract fun provideRecipesInteractor(recipesInteractorImpl: RecipesInteractorImpl): RecipesInteractor
}