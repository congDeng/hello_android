package com.thoughtworks.helloandroidview.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.thoughtworks.helloandroidview.model.datasource.AppDatabase
import com.thoughtworks.helloandroidview.model.datasource.entity.Tweet
import com.thoughtworks.helloandroidview.model.network.TweetService
import com.thoughtworks.helloandroidview.model.repository.TweetRepository
import com.thoughtworks.helloandroidview.utils.Utils.formatTimestamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TweetViewModel(application: Application) : AndroidViewModel(application) {
    private val tweetRepository: TweetRepository
    var tweets: LiveData<List<Tweet>>

    init {
        val tweetDao = AppDatabase.getInstance(application).tweetDao()
        tweetRepository = TweetRepository(tweetDao, TweetService)
        val newTweets = tweetRepository.allTweets.map { tweetList ->
            tweetList.sortedByDescending { it.date }
                .map {
                    it.copy(date = formatTimestamp(it.date))
                }
        }
        tweets = newTweets
    }


    fun fetchTweets() {
        viewModelScope.launch(Dispatchers.IO) {
            tweetRepository.getAndSaveTweets()
        }
    }

}