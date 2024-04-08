package com.thoughtworks.helloandroidview.model.network

import com.thoughtworks.helloandroidview.model.datasource.entity.Tweet
import retrofit2.Response
import retrofit2.http.GET

interface TweetService {
    @GET("tweets.json")
    suspend fun fetchTweets(): Response<List<Tweet>>
}