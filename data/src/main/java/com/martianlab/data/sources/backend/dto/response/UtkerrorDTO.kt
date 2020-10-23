package com.martianlab.recipes.tools.backend.dto.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class UtkerrorDTO(

    @SerializedName("Code")
    @SerialName("Code")
    val code: Int,

    @SerializedName("Description")
    @SerialName("Description")
    val message: String
)