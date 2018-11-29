package br.com.ezequiel.twitterhappines.presentation.tweet

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import br.com.ezequiel.twitterhappines.R
import br.com.ezequiel.twitterhappines.core.extension.contentView
import br.com.ezequiel.twitterhappines.core.platform.InjectableActivity
import br.com.ezequiel.twitterhappines.databinding.ActivityTweetBinding
import br.com.ezequiel.twitterhappines.presentation.user.TweetData
import br.com.ezequiel.twitterhappines.presentation.user.UserModel
import br.com.ezequiel.twitterhappines.presentation.user.UserState
import br.com.ezequiel.twitterhappines.presentation.user.UserViewModel
import kotlinx.android.synthetic.main.activity_tweet.*
import javax.inject.Inject

class TweetActivity : InjectableActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    @Inject
    lateinit var adapter: TweetListAdapter

    private val binding: ActivityTweetBinding by contentView(layoutResource())
    private val viewModel by lazy {
        ViewModelProviders
            .of(this, factory)
            .get(UserViewModel::class.java)
    }

    override fun layoutResource(): Int = R.layout.activity_tweet

    override fun init() {
        binding.viewModel = viewModel
        binding.user = getUser()

        title = getUser().name
        rv_tweets.adapter = adapter

        viewModel.getTweetsById(getUser().id)
        bindViewModels()
    }

    override fun insertListener() {

    }

    private fun bindViewModels() {
        viewModel.state.observe(this, Observer {
            it?.let { state ->
                handleState(state)
            }
        })
    }

    private fun handleState(state: UserState) {
        when (state) {
//            is CategoriesLoading -> {
//                loading.show()
//            }
            is TweetData -> {
//                loading.hide()
                adapter.items = state.data
            }
//            is CategoriesError -> {
//                loading.hide()
//            }
        }
    }

    private fun getUser() = intent.getParcelableExtra<UserModel>(USER_MODEL)

    companion object {
        private const val USER_MODEL = "USER_MODEL"

        fun newIntent(context: Context, userModel: UserModel) =
            Intent(context, TweetActivity::class.java).putExtra(USER_MODEL, userModel)
    }
}
