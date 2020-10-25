package com.martianlab.data.sources.db_new

import android.content.Context
import com.martianlab.data.sources.db_new.converters.Converters.dateAdapter
import com.martianlab.data.sources.db_new.converters.Converters.listOfStringsAdapter
import com.martianlab.data.sources.db_new.entities.RecipeWithDependenciesEntity
import com.martianlab.data.sources.db_new.mapper.toEntity
import com.martianlab.data.sources.db_new.mapper.toModel
import com.martianlab.data.sources.db_new.mapper.toRecipe
import com.martianlab.data.sources.db_new.mapper.toRecipeIngredient
import com.martianlab.recipes.data.sources.db.Database
import com.martianlab.recipes.domain.api.DbApi
import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.entities.RecipeIngredient
import com.martianlab.recipes.entities.RecipeTag
import com.squareup.sqldelight.Query
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

    val recipesDb = db.recipeEntityQueries
    val categoryDb = db.categoryEntityQueries

    private fun RecipeEntity.toRecipeWithDependenciesEntity() : RecipeWithDependenciesEntity {

        val tags = recipesDb.getTagsByRecipe(id).executeAsList().toSet()
        val stages = recipesDb.getStagesByRecipe(id).executeAsList()
        val comments = recipesDb.getCommentsByRecipe(id).executeAsList()
        val ingredients = recipesDb.getIngredientsByRecipe(id).executeAsList()

        return RecipeWithDependenciesEntity( this, tags, stages, ingredients, comments)
    }

    private fun Query<RecipeEntity>.toRecipeList() =
        this.executeAsList().map { it.toRecipeWithDependenciesEntity().toRecipe() }


    override suspend fun getRecipes(tag: RecipeTag): List<Recipe> =
        recipesDb.getRecipesByTagTitle(tag.title).toRecipeList()

    override suspend fun getRecipes(): List<Recipe> =
        recipesDb.getRecipes().toRecipeList()


    override suspend fun getRecipeById(id: Long): Recipe =
        recipesDb
            .getById(id)
            .executeAsOne()
            .toRecipeWithDependenciesEntity()
            .toRecipe()


    override suspend fun insert(recipe: Recipe): Long {
        recipesDb.insertRecipe(recipe.toEntity())
        return 1
    }

    override suspend fun insert(recipeList: List<Recipe>) {
        recipeList.forEach { insert(it) }
    }

    override suspend fun loadCategories(): List<Category> =
        categoryDb.getAll().executeAsList().map { it.toModel() }


    override suspend fun insertCategories(categoryList: List<Category>): List<Long> {
        categoryList.forEach { categoryDb.insert(it.toEntity()) }
        return emptyList()
    }

    override suspend fun searchIngredients(contains: String): List<RecipeIngredient> =
        recipesDb.searchIngredients(contains).executeAsList().map { it.toRecipeIngredient() }

    override suspend fun searchRecipes(contains: String): List<Recipe> =
        recipesDb.searchRecipes(contains).toRecipeList()

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