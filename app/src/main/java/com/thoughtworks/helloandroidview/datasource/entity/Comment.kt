package com.thoughtworks.helloandroidview.datasource.entity

import androidx.room.Entity

@Entity(tableName = "comment")
data class Comment(
    var content: String?,
    var sender: Sender?
)