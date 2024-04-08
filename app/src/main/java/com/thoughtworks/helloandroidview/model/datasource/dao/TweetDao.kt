package com.thoughtworks.helloandroidview.model.datasource.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thoughtworks.helloandroidview.model.datasource.entity.Tweet

@Dao
interface TweetDao {

    @Query("SELECT * from tweet")
    fun getTweets(): LiveData<List<Tweet>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tweets: List<Tweet>)

    @Query("DELETE FROM tweet")
    suspend fun deleteAll()
}
