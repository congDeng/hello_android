package com.thoughtworks.helloandroidview.datasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thoughtworks.helloandroidview.datasource.entity.Tweet
import kotlinx.coroutines.flow.Flow

@Dao
interface TweetDao {

    @Query("SELECT * from tweet")
    fun fetchTweets(): Flow<List<Tweet>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tweets: List<Tweet>)

    @Query("DELETE FROM tweet")
    suspend fun deleteAll()
}
