package com.martianlab.recipes.tools.backend.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class CategoryDTO(

    @SerializedName("Id")
    @SerialName("Id")
    val id : Long,

    @SerializedName("Category")
    @SerialName("Category")
    val category : String,

    @SerializedName("Sort")
    @SerialName("Sort")
    val sort : Int,

    @SerializedName("imageURL")
    @SerialName("imageURL")
    val imageURL : String?= null
)