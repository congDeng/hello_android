package com.thoughtworks.helloandroidview.model


data class Tweet(
    val content: String,
    val images: List<Image>?,
    val sender: Sender?,
    val comments: List<Comment>?,
    val error: String?,
    val unknownError: String?
)
