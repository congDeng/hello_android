package com.thoughtworks.helloandroidview.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.thoughtworks.helloandroidview.R
import com.thoughtworks.helloandroidview.model.Tweet

class TweetAdapter : RecyclerView.Adapter<ViewHolder>() {
    private val tweets = arrayListOf<Tweet>()

    @SuppressLint("NotifyDataSetChanged")
    fun setTweets(newTweets: ArrayList<Tweet>){
        tweets.clear()
        tweets.addAll(newTweets)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TweetViewHolder(layoutInflater.inflate(R.layout.tweet_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return tweets.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tweet = tweets[position]
        val tweetViewHolder = holder as TweetViewHolder
        tweetViewHolder.nicknmae.text = tweet.sender.nick
    }


    internal class TweetViewHolder(itemView: View) : ViewHolder(itemView) {
        var nicknmae: TextView

        init {
            nicknmae = itemView.findViewById(R.id.nickname)
        }
    }

}