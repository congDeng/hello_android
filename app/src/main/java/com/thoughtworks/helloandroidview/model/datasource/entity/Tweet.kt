package com.thoughtworks.helloandroidview.model.datasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tweet")
data class Tweet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val content: String?,
    val images: List<Image>?,
    val sender: Sender?,
    val comments: List<Comment>?,
    val error: String?,
    val unknownError: String?,
    val date: String
)