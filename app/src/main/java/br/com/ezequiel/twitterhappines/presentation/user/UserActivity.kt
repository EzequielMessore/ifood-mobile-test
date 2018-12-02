package br.com.ezequiel.twitterhappines.presentation.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.design.widget.Snackbar
import br.com.ezequiel.twitterhappines.R
import br.com.ezequiel.twitterhappines.core.extension.contentView
import br.com.ezequiel.twitterhappines.core.platform.InjectableActivity
import br.com.ezequiel.twitterhappines.databinding.ActivityUserBinding
import br.com.ezequiel.twitterhappines.presentation.tweet.TweetActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.base_content.*
import kotlinx.android.synthetic.main.container_error.view.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserActivity : InjectableActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val binding: ActivityUserBinding by contentView(layoutResource())
    private val viewModel by lazy {
        ViewModelProviders
            .of(this, factory)
            .get(UserViewModel::class.java)
    }

    override fun layoutResource(): Int = R.layout.activity_user

    override fun init() {
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)

        bindViewModels()
    }

    override fun insertListener() {
        iv_search.setOnClickListener {
            til_search.editText?.let { edit ->
                if (edit.text.isEmpty()) {
                    til_search.error = getString(R.string.label_required)
                    return@let
                }
                viewModel.getUser(edit.text.toString())
            }
        }
        container_error.btn_try_again.setOnClickListener {
            viewModel.state.value = UserData(null)
        }
    }

    private fun bindViewModels() {
        viewModel.state.observe(this, Observer {
            it?.let { state ->
                handleUserState(state)
            }
        })
    }

    private fun handleUserState(state: UserState) {
        when (state) {
            is UserData -> {
                state.data?.let { user ->
                    Snackbar.make(content, getString(R.string.msg_success_user), Snackbar.LENGTH_LONG).show()

                    Observable
                        .timer(1500, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            startActivity(TweetActivity.newIntent(this, user))
                        }
                }
            }
            is UserError -> {
                if (state.isNotFound()) {
                    til_search.error = getString(R.string.label_user_not_found)
                }
            }
        }
    }
}