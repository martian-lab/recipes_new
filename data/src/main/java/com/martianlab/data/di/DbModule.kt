package com.martianlab.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.martianlab.recipes.domain.api.DbApi
import com.martianlab.recipes.tools.db.DbImpl
import com.martianlab.data.sources.db.RecipesDb
import com.martianlab.data.sources.db_new.DatabaseDriverFactory
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

    @Singleton
    @Provides
    internal fun provideDbApi(context: Context): DbApi {
        return DatabaseDriverFactory(context)
    }


//    @Provides
//    @Singleton
//    internal fun provideDbApi(db : RecipesDb): DbApi =
//        with(db) {
//            DbImpl(recipeDao, categoryDao)
//        }


}