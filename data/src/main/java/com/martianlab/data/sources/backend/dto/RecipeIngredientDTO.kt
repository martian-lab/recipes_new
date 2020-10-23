package com.martianlab.recipes.tools.backend.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class RecipeIngredientDTO(
    @SerializedName("Id")
    @SerialName("Id")
    val id : Long,
    @SerializedName("RecipeId")
    @SerialName("RecipeId")
    val recipeId : Long,
    @SerializedName("Name")
    @SerialName("Name")
    val name : String,
    @SerializedName("HowMany")
    @SerialName("HowMany")
    val quantity : String,
    @SerializedName("HowManyUnit")
    @SerialName("HowManyUnit")
    val measureUnit : String,
    @SerializedName("Position")
    @SerialName("Position")
    val position : Int
)


