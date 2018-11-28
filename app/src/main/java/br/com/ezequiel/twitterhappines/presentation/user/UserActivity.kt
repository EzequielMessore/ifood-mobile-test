package br.com.ezequiel.twitterhappines.presentation.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import br.com.ezequiel.twitterhappines.R
import br.com.ezequiel.twitterhappines.core.extension.contentView
import br.com.ezequiel.twitterhappines.core.platform.InjectableActivity
import br.com.ezequiel.twitterhappines.databinding.ActivityUserBinding
import kotlinx.android.synthetic.main.activity_user.*
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
    }

    private fun bindViewModels() {
        viewModel.state.observe(this, Observer {
            it?.let { state ->
                handleCategoriesState(state)
            }
        })
    }

    private fun handleCategoriesState(state: UserState) {
        when (state) {
            is UserLoading -> {
                loading.show()
            }
            is UserData -> {
                loading.hide()
                println(state.data)
            }
            is UserError -> {
                if (state.isNotFound()) {
                    til_search.error = getString(R.string.label_user_not_found)
                }
                loading.hide()
            }
        }
    }
}