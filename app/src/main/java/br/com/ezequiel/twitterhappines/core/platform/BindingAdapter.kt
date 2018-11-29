package br.com.ezequiel.twitterhappines.core.platform

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import br.com.ezequiel.twitterhappines.core.extension.getParentActivity
import br.com.ezequiel.twitterhappines.core.extension.hide
import br.com.ezequiel.twitterhappines.core.extension.show
import br.com.ezequiel.twitterhappines.presentation.user.UserData
import br.com.ezequiel.twitterhappines.presentation.user.UserError
import br.com.ezequiel.twitterhappines.presentation.user.UserState
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_user.view.*

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("userState")
    fun setUserState(view: View, state: MutableLiveData<UserState>) {
        val parentActivity: AppCompatActivity? = view.getParentActivity()
        parentActivity?.let { act ->
            val content = view.content
            val error = view.container_error

            state.observe(act, Observer {
                when (it) {
                    is UserError -> {
                        if(it.isNotFound()) {
                            content.show()
                            return@Observer
                        }
                        error.show()
                        content.hide()
                    }
                    is UserData -> {
                        error.hide()
                        content.show()
                    }
                    else -> {
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