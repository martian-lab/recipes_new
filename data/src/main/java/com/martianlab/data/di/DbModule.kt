package com.martianlab.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.martianlab.recipes.domain.api.DbApi
import com.martianlab.recipes.tools.db.DbImpl
import com.martianlab.recipes.tools.db.RecipesDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DB_NAME = "recipes_database"

@Module
class DbModule(application: Application) {

    @Singleton
    @Provides
    internal fun provideDb(context: Context): RecipesDb {
        return Room.databaseBuilder(context, RecipesDb::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    internal fun provideDbApi(db : RecipesDb): DbApi =
        with(db) {
            DbImpl(recipeDao, categoryDao)
        }


}