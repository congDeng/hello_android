package com.thoughtworks.helloandroidview.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.thoughtworks.helloandroidview.R
import com.thoughtworks.helloandroidview.model.Tweet

class TweetAdapter : RecyclerView.Adapter<ViewHolder>() {
    private val tweets = arrayListOf<Tweet>()

    companion object {
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_NO_MORE_DATA = 1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTweets(newTweets: ArrayList<Tweet>) {
        tweets.clear()
        tweets.addAll(newTweets)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                TweetViewHolder(layoutInflater.inflate(R.layout.tweet_item_layout, parent, false))
            }

            VIEW_TYPE_NO_MORE_DATA -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.no_more_data_item_layout, parent, false)
                NoMoreDataViewHolder(view)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }

    }

    override fun getItemCount(): Int {
        return if (tweets.isEmpty()) {
            0
        } else {
            tweets.size + 1
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is TweetViewHolder) {
            holder.bind(tweets[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < tweets.size) {
            VIEW_TYPE_ITEM
        } else {
            VIEW_TYPE_NO_MORE_DATA
        }
    }


    internal class TweetViewHolder(itemView: View) : ViewHolder(itemView) {
        private val nickname: TextView by lazy { itemView.findViewById(R.id.nickname) }
        private val content: TextView by lazy { itemView.findViewById(R.id.content) }
        private val avatar: ImageView by lazy { itemView.findViewById(R.id.avatar) }

        fun bind(tweet: Tweet) {
            nickname.text = tweet.sender?.nick
            content.text = tweet.content
            avatar.load(tweet.sender?.avatar)
        }
    }

    internal class NoMoreDataViewHolder(itemView: View) : ViewHolder(itemView)

}