package com.thoughtworks.helloandroidview.model.network

import com.thoughtworks.helloandroidview.model.datasource.entity.Tweet
import com.thoughtworks.helloandroidview.model.network.RetrofitBuilder.retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.GET


object TweetService {
    private val tweetService = retrofit.create(ITweetService::class.java)

    suspend fun allTweets(): List<Tweet> = withContext(Dispatchers.Default) {
        tweetService.getAllTweets().filter { it.error == null && it.unknownError == null }
    }
}

interface ITweetService {
    @GET("tweets.json")
    suspend fun getAllTweets(): List<Tweet>
}
