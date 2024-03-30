package com.thoughtworks.helloandroidview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thoughtworks.helloandroidview.adapter.TweetAdapter
import com.thoughtworks.helloandroidview.model.Tweet
import com.thoughtworks.helloandroidview.utils.Utils.readJsonFromAssets

class TweetsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tweets)
        initUi()
    }

    private fun getTweets(): ArrayList<Tweet> {
        val jsonString = readJsonFromAssets(this, "tweets.json")
        val gson = Gson()
        val listType = object : TypeToken<ArrayList<Tweet>>() {}.type
        val tweets = gson.fromJson<ArrayList<Tweet>>(jsonString, listType)
        return tweets.filter { it.error == null && it.unknownError == null } as ArrayList<Tweet>
    }

    private fun initUi() {
        val recyclerView = findViewById<RecyclerView>(R.id.tweets)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val tweetAdapter = TweetAdapter()
        recyclerView.adapter = tweetAdapter
        tweetAdapter.setTweets(getTweets())
    }

}