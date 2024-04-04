package com.thoughtworks.helloandroidview.datasource.entity

import androidx.room.Entity

@Entity(tableName = "image")
data class Image(var url: String?)