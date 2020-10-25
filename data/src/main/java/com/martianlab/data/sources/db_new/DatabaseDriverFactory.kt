package com.martianlab.data.sources.db_new

import android.content.Context
import com.martianlab.data.sources.db_new.adapters.Converters.dateAdapter
import com.martianlab.data.sources.db_new.adapters.Converters.listOfStringsAdapter
import com.martianlab.data.sources.db_new.mapper.toRecipe
import com.martianlab.recipes.data.sources.db.Database
import com.martianlab.recipes.domain.api.DbApi
import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.entities.RecipeIngredient
import com.martianlab.recipes.entities.RecipeTag
import com.squareup.sqldelight.android.AndroidSqliteDriver
import commartianlabrecipesdatasourcesdb.RecipeCommentEntity
import commartianlabrecipesdatasourcesdb.RecipeEntity


class DatabaseDriverFactory(private val context: Context) : DbApi {

    val androidSqlDriver = AndroidSqliteDriver(
        schema = Database.Schema,
        context = context,
        name = "recipes.db"
    )

    val db: Database = Database(
        driver = androidSqlDriver,
        recipeCommentEntityAdapter = RecipeCommentEntity.Adapter(photoURLsAdapter = listOfStringsAdapter, dateAdapter = dateAdapter)
    )

    override suspend fun getRecipes(tag: RecipeTag): List<Recipe> =
         db.recipeEntityQueries.getRecipesByTagTitle(tag.title).executeAsList()
            .map { it.toRecipeWithDependenciesEntity().toRecipe() }

    fun RecipeEntity.toRecipeWithDependenciesEntity() : RecipeWithDependenciesEntity{
        val tags = db.recipeEntityQueries.getTagsByRecipe(id).executeAsList().toSet()
        val stages = db.recipeEntityQueries.getStagesByRecipe(id).executeAsList()
        val comments = db.recipeEntityQueries.getCommentsByRecipe(id).executeAsList()
        val ingredients = db.recipeEntityQueries.getIngredientsByRecipe(id).executeAsList()
        return RecipeWithDependenciesEntity( this, tags, stages, ingredients, comments)
    }

    override suspend fun getRecipes(): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipeById(id: Long): Recipe {
        TODO("Not yet implemented")
    }

    override suspend fun insert(recipe: Recipe): Long {
        TODO("Not yet implemented")
    }

    override suspend fun insert(recipeList: List<Recipe>) {
        TODO("Not yet implemented")
    }

    override suspend fun loadCategories(): List<Category> {
        TODO("Not yet implemented")
    }

    override suspend fun insertCategories(categoryList: List<Category>): List<Long> {
        TODO("Not yet implemented")
    }

    override suspend fun searchIngredients(contains: String): List<RecipeIngredient> {
        TODO("Not yet implemented")
    }

    override suspend fun searchRecipes(contains: String): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun setFavorite(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFavorite(recipe: Recipe) {
        TODO("Not yet implemented")
    }

    override suspend fun getFavorites(): List<Recipe> {
        TODO("Not yet implemented")
    }

    override suspend fun getRecipesByIngredient(ingredient: RecipeIngredient): List<Recipe> {
        TODO("Not yet implemented")
    }
}