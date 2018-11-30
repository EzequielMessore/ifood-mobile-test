package br.com.ezequiel.twitterhappines.presentation.tweet

import android.databinding.DataBindingUtil
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.ezequiel.twitterhappines.R
import br.com.ezequiel.twitterhappines.core.platform.BaseAdapter
import br.com.ezequiel.twitterhappines.databinding.ItemTweetBinding
import javax.inject.Inject


class TweetListAdapter @Inject constructor() : BaseAdapter<TweetModel, TweetListAdapter.ViewHolder>() {

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

    var listener: ((TweetModel) -> Unit) = {}

    inner class ViewHolder(private val binding: ItemTweetBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: TweetModel) = with (binding) {
            val context = binding.root.context
            tweet = model
            vAnalise.setOnClickListener {
                listener(model)
            }
            val humor = model.humor
            if(humor != null) {
                gpAnalyse.visibility = View.GONE
                tvHappiness.visibility = View.VISIBLE

                val (color, resourceText) = when (humor) {
                    Humor.HAPPY_EMOTION -> R.color.happy_color to R.string.label_happy
                    Humor.NEUTRAL_EMOTION -> R.color.neutral_color to R.string.label_neutral
                    Humor.SAD_EMOTION -> R.color.sad_color to R.string.label_sad
                }
                tvHappiness.text = context.getString(resourceText, humor.emoji)
                tvHappiness.setTextColor(ContextCompat.getColor(context, color))
                container.background.setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_ATOP)

            } else {
                gpAnalyse.visibility = View.VISIBLE
                tvHappiness.visibility = View.GONE
                container.background.setColorFilter(ContextCompat.getColor(context, android.R.color.black), PorterDuff.Mode.SRC_ATOP)
            }
            executePendingBindings()
        }
    }
}