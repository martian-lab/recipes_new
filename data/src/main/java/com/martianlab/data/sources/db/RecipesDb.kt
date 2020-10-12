package com.martianlab.recipes.tools.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.martianlab.recipes.tools.db.dao.CategoryDao
import com.martianlab.recipes.tools.db.dao.RecipeDao
import com.martianlab.recipes.tools.db.dao.UserDao
import com.martianlab.recipes.tools.db.entities.*


@Database(
    entities = [
        User::class,
        RecipeEntity::class,
        CategoryEntity::class,
        RecipeTagEntity::class,
        RecipeCommentEntity::class,
        RecipeIngredientEntity::class,
        RecipeStageEntity::class
    ],
    version = 9,
    exportSchema = true
)
abstract class RecipesDb : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val categoryDao: CategoryDao
    abstract val recipeDao: RecipeDao
}
