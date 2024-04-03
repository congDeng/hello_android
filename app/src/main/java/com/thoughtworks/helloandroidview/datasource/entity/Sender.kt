package com.thoughtworks.helloandroidview.datasource.entity

import androidx.room.Entity

@Entity(tableName = "sender")
data class Sender(
    val userName: String?,
    val nick: String?,
    val avatar: String?
)