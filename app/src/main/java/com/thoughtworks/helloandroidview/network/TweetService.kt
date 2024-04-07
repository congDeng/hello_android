package com.thoughtworks.helloandroidview.network

import com.thoughtworks.helloandroidview.datasource.entity.Tweet
import retrofit2.Response
import retrofit2.http.GET

interface TweetService {
    @GET("tweets.json")
    suspend fun fetchTweets(): Response<List<Tweet>>
}