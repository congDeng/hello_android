package com.thoughtworks.helloandroidview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.helloandroidview.adapter.TweetAdapter
import com.thoughtworks.helloandroidview.datasource.AppDatabase
import com.thoughtworks.helloandroidview.datasource.entity.Tweet
import com.thoughtworks.helloandroidview.network.RetrofitBuilder.retrofit
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
            try {
                val response = retrofit.fetchTweets()
                if (response.isSuccessful) {
                    getTweets(response.body())?.let { database.tweetDao().insertAll(it) }
                }
            } catch (e: Exception) {
                Toast.makeText(this@TweetsActivity, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getTweets(tweets: List<Tweet>?): List<Tweet>? {
        return tweets?.filter { it.error == null && it.unknownError == null }
    }


    private fun initUi() {
        val recyclerView = findViewById<RecyclerView>(R.id.tweets)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = tweetAdapter
    }

}