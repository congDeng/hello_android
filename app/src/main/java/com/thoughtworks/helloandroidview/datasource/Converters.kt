package com.thoughtworks.helloandroidview.datasource

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thoughtworks.helloandroidview.datasource.entity.Comment
import com.thoughtworks.helloandroidview.datasource.entity.Image
import com.thoughtworks.helloandroidview.datasource.entity.Sender

class Converters {
    @TypeConverter
    fun fromImagesString(value: String?): List<Image> {
        if (value == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<Image>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromImageList(list: List<Image>?): String? {
        return list?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun fromSender(sender: Sender?): String? {
        return sender?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun toSender(value: String?): Sender? {
        return value?.let { Gson().fromJson(it, Sender::class.java) }
    }

    @TypeConverter
    fun fromCommentsString(value: String?): List<Comment> {
        if (value == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<Comment>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromCommentList(list: List<Comment>?): String? {
        return list?.let { Gson().toJson(it) }
    }

}