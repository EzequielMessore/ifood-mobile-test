package br.com.ezequiel.twitterhappines.presentation.tweet

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.ezequiel.twitterhappines.R
import br.com.ezequiel.twitterhappines.core.platform.BaseAdapter
import br.com.ezequiel.twitterhappines.databinding.ItemTweetBinding

class TweetListAdapter @javax.inject.Inject constructor() : BaseAdapter<TweetModel, TweetListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemTweetBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_tweet,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
    }


    inner class ViewHolder(private val binding: ItemTweetBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: TweetModel) {
            binding.tweet = model
            binding.executePendingBindings()
        }
    }
}