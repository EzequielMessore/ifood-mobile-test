package br.com.ezequiel.twitterhappines.core.platform

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import br.com.ezequiel.twitterhappines.R
import br.com.ezequiel.twitterhappines.core.extension.getParentActivity
import br.com.ezequiel.twitterhappines.core.extension.hide
import br.com.ezequiel.twitterhappines.core.extension.show
import br.com.ezequiel.twitterhappines.presentation.tweet.TweetData
import br.com.ezequiel.twitterhappines.presentation.tweet.TweetError
import br.com.ezequiel.twitterhappines.presentation.tweet.TweetLoadingItem
import br.com.ezequiel.twitterhappines.presentation.tweet.TweetState
import br.com.ezequiel.twitterhappines.presentation.user.UserData
import br.com.ezequiel.twitterhappines.presentation.user.UserError
import br.com.ezequiel.twitterhappines.presentation.user.UserState
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("userState")
    fun setUserState(view: View, state: MutableLiveData<UserState>) {
        val parentActivity: AppCompatActivity? = view.getParentActivity()
        parentActivity?.let { act ->
            val content = view.findViewById<View>(R.id.content)
            val error = view.findViewById<View>(R.id.container_error)
            val loading = view.findViewById<View>(R.id.loading)

            state.observe(act, Observer {
                when (it) {
                    is UserError -> {
                        loading.hide()
                        if (it.isNotFound()) {
                            content.show()
                            return@Observer
                        }
                        error.show()
                        content.hide()
                    }
                    is UserData -> {
                        loading.hide()
                        error.hide()
                        content.show()
                    }
                    else -> {
                        loading.show()
                        content.hide()
                        error.hide()
                    }
                }
            })
        }
    }

    @JvmStatic
    @BindingAdapter("tweetState")
    fun setTweetState(view: View, state: MutableLiveData<TweetState>) {
        val parentActivity: AppCompatActivity? = view.getParentActivity()
        parentActivity?.let { act ->
            val content = view.findViewById<View>(R.id.content)
            val error = view.findViewById<View>(R.id.container_error)
            val loading = view.findViewById<View>(R.id.loading)

            state.observe(act, Observer {
                when (it) {
                    is TweetError -> {
                        loading.hide()
                        content.hide()
                        error.show()
                    }
                    is TweetData -> {
                        loading.hide()
                        error.hide()
                        content.show()
                    }
                    is TweetLoadingItem -> {
                        loading.show()
                    }
                    else -> {
                        loading.show()
                        content.hide()
                        error.hide()
                    }
                }
            })
        }
    }

    @JvmStatic
    @BindingAdapter("roundedImage")
    fun setImageRoundedUrl(imageView: ImageView, url: String) {
        Glide.with(imageView.context).load(url).apply(RequestOptions.circleCropTransform()).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("image")
    fun setImageUrl(imageView: ImageView, url: String) {
        Glide.with(imageView.context).load(url).into(imageView)
    }

}