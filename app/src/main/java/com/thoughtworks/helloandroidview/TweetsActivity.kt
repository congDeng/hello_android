package com.thoughtworks.helloandroidview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thoughtworks.helloandroidview.adapter.TweetAdapter
import com.thoughtworks.helloandroidview.datasource.AppDatabase
import com.thoughtworks.helloandroidview.datasource.entity.Tweet
import com.thoughtworks.helloandroidview.utils.Utils.readFileFromRaw
import kotlinx.coroutines.launch

class TweetsActivity : AppCompatActivity() {
    private val database: AppDatabase by lazy { AppDatabase.getInstance(this) }
    private val tweetAdapter: TweetAdapter by lazy { TweetAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tweets)

        initData()
        initUi()
    }

    private fun initData() {
        insertDataIntoDatabase()
        lifecycleScope.launch {
            database.tweetDao().fetchTweets().collect { data ->
                tweetAdapter.setTweets(ArrayList(data))
            }
        }
    }

    private fun insertDataIntoDatabase() {
        lifecycleScope.launch {
            database.tweetDao().insertAll(getTweets())
        }
    }

    private fun getTweets(): List<Tweet> {
        val jsonString = readFileFromRaw(this, R.raw.tweets)
        val gson = Gson()
        val listType = object : TypeToken<List<Tweet>>() {}.type
        val tweets = gson.fromJson<List<Tweet>>(jsonString, listType)
        return tweets.filter { it.error == null && it.unknownError == null }
    }


    private fun initUi() {
        val recyclerView = findViewById<RecyclerView>(R.id.tweets)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = tweetAdapter
    }

}