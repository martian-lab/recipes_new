package com.martianlab.recipes.tools.backend.dto.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class UtkoresponseDTO<B>(
    @SerializedName("Head")
    @SerialName("Head")
    val head: Head,

    @SerializedName("Body")
    @SerialName("Body")
    val body: B
) {
    @Serializable
    class Head(
        @SerializedName("SessionToken")
        @SerialName("SessionToken")
        val accessToken: String?= null,

        @SerializedName("SessionGroups")
        @SerialName("SessionGroups")
        val sessionGroups: List<String>? = null


    )

    @Serializable
    class BaseBody(
        @SerializedName("ErrorList")
        @SerialName("ErrorList")
        val errors: List<UtkerrorDTO>?= null
    )
}