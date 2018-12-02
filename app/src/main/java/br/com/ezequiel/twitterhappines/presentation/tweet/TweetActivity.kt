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
import br.com.ezequiel.twitterhappines.presentation.user.UserModel
import br.com.ezequiel.twitterhappines.presentation.user.UserViewModel
import kotlinx.android.synthetic.main.activity_tweet.*
import kotlinx.android.synthetic.main.base_content.*
import kotlinx.android.synthetic.main.container_error.view.*
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

        rv_tweets.adapter = adapter

        viewModel.getTweetsById(getUser().id)
        bindViewModels()
    }

    override fun insertListener() {
        adapter.listener = {
            viewModel.analyseText(it)
        }
        container_error.btn_try_again.setOnClickListener {
            viewModel.getTweetsById(getUser().id)
        }
    }

    private fun bindViewModels() {
        viewModel.tweets.observe(this, Observer {
            it?.let { list ->
                adapter.items = list
            }
        })
    }

    private fun getUser() = intent.getParcelableExtra<UserModel>(USER_MODEL)

    companion object {
        private const val USER_MODEL = "USER_MODEL"

        fun newIntent(context: Context, userModel: UserModel): Intent =
            Intent(context, TweetActivity::class.java).putExtra(USER_MODEL, userModel)
    }
}
