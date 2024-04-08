package com.thoughtworks.helloandroidview.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.helloandroidview.R
import com.thoughtworks.helloandroidview.adapter.TweetAdapter
import com.thoughtworks.helloandroidview.viewmodel.TweetViewModel
import androidx.activity.viewModels

class TweetsActivity : AppCompatActivity() {
    private val tweetAdapter: TweetAdapter by lazy { TweetAdapter() }
    private val tweetViewModel: TweetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tweets)

        initData()
        initUi()
    }

    private fun initData() {
        tweetViewModel.tweets.observe(this) { tweets ->
            tweetAdapter.setTweets(ArrayList(tweets))
        }
        tweetViewModel.fetchTweets()
    }

    private fun initUi() {
        val recyclerView = findViewById<RecyclerView>(R.id.tweets)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = tweetAdapter
    }

}