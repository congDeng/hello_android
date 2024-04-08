package com.thoughtworks.helloandroidview.model.repository

import androidx.lifecycle.LiveData
import com.thoughtworks.helloandroidview.model.datasource.dao.TweetDao
import com.thoughtworks.helloandroidview.model.datasource.entity.Tweet
import com.thoughtworks.helloandroidview.model.network.TweetService

class TweetRepository(
    private val tweetDao: TweetDao,
    private val tweetService: TweetService
) {

    val allTweets: LiveData<List<Tweet>> = tweetDao.getTweets()

     suspend fun getAndSaveTweets() {
        val tweets = tweetService.allTweets()
        tweetDao.insertAll(tweets)
    }
}