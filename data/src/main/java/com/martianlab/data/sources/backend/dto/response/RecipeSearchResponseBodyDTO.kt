package com.martianlab.recipes.tools.backend.dto.response

import com.google.gson.annotations.SerializedName
import com.martianlab.recipes.tools.backend.dto.CategoryDTO
import com.martianlab.recipes.tools.backend.dto.RecipeCookingDTO
import com.martianlab.recipes.tools.backend.dto.RecipeDTO
import com.martianlab.recipes.tools.backend.dto.RecipeIngredientDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RecipeSearchResponseBodyDTO(

    @SerializedName("RecipeList")
    @SerialName("RecipeList")
    val recipeList: List<RecipeDTO>? = null,

    @SerializedName("RecipeCookingList")
    @SerialName("RecipeCookingList")
    val recipeCookingList: List<RecipeCookingDTO>?= null,

    @SerializedName("Category")
    @SerialName("Category")
    val categoryList: List<CategoryDTO>?= null,

    @SerializedName("Total")
    @SerialName("Total")
    val total: Int,

    @SerializedName("ErrorList")
    @SerialName("ErrorList")
    val errors: Array<UtkerrorDTO>?= null,

    @SerializedName("RecipeIngredientList")
    @SerialName("RecipeIngredientList")
    val ingredientList: List<RecipeIngredientDTO>?= null
)