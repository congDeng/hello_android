package com.thoughtworks.helloandroidview.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://raw.githubusercontent.com/TW-Android-Junior-Training/android_training_practice/main/json/"

    val retrofit: TweetService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(TweetService::class.java)
}