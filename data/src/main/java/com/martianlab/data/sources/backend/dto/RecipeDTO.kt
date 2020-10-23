package com.martianlab.recipes.tools.backend.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RecipeDTO(
    @SerializedName("Id")
    @SerialName("Id")
    val id : Int,
    @SerializedName("Url")
    @SerialName("Url")
    val url : String, //"/recipe/3517",
    @SerializedName("Name")
    @SerialName("Name")
    val name : String,
    @SerializedName("Complexity")
    @SerialName("Complexity")
    val complexity : Int,
    @SerializedName("NumberPersons")
    @SerialName("NumberPersons")
    val numberPersons : Int,
    @SerializedName("Image")
    @SerialName("Image")
    val imageURL : String,
    @SerializedName("ViewImage")
    @SerialName("ViewImage")
    val viewImageURL : String,
    @SerializedName("Description")
    @SerialName("Description")
    val description : String,// "",
    @SerializedName("Comment")
    @SerialName("Comment")
    val comments : List<CommentDTO>,
    @SerializedName("Rating")
    @SerialName("Rating")
    val rating : RatingDTO? = null
)

@Serializable
class RatingDTO(
    @SerializedName("Rate")
    @SerialName("Rate")
    val rate : Int,
    @SerializedName("Votes")
    @SerialName("Votes")
    val votes : Int
)
