package com.martianlab.recipes.tools.backend.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RecipeCookingDTO (
    @SerializedName("Id")
    @SerialName("Id")
    val id : Long,
    @SerializedName("RecipeId")
    @SerialName("RecipeId")
    val recipeId : Long,
    @SerializedName("Image")
    @SerialName("Image")
    val imageURL : String,
    @SerializedName("Description")
    @SerialName("Description")
    val description : String,
    @SerializedName("Position")
    @SerialName("Position")
    val position : Int
)