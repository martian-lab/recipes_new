package com.martianlab.recipes.tools.backend.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class CommentDTO(

    @SerializedName("Author")
    @SerialName("Author")
    val author : String, // "Ольга Владимировна",
    @SerializedName("Created")
    @SerialName("Created")
    val  createdAt : String, //"2015-07-26 14:19:52",
    @SerializedName("Id")
    @SerialName("Id")
    val id : Long, // "58679",
    @SerializedName("ParentId")
    @SerialName("ParentId")
    val parentId : Long, // "0",
    @SerializedName("RateUp")
    @SerialName("RateUp")
    val rateUp : Int, // "3",
    @SerializedName("RateDown")
    @SerialName("RateDown")
    val rateDown : Int, // "0",
    @SerializedName("Text")
    @SerialName("Text")
    val text : String, // "Попробовала приготовить- очень вкусно получилось, прикупила бульон в желе и гренки... Я только немного твердого тертого сыра добавила в самом конце готовки.",
    @SerializedName("UserPic")
    @SerialName("UserPic")
    val userPic : String?= null,
    @SerializedName("SelfCommentRate")
    @SerialName("SelfCommentRate")
    val selfCommentRate : String
)