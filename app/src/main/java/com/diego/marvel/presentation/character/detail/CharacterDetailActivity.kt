package com.diego.marvel.presentation.character.list

import android.os.Bundle
import com.diego.marvel.databinding.ActivityCharacterListBinding
import com.diego.marvel.presentation.character.list.adapter.CharacterAdapter
import com.diego.marvel.presentation.common.BaseActivity
import com.diego.marvel.presentation.model.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListActivity : BaseActivity<ActivityCharacterListBinding>(), CharacterListView {
    override fun getViewBinding() = ActivityCharacterListBinding.inflate(layoutInflater)

    @Inject
    lateinit var presenter: CharacterListPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
    }

    override fun fillCharacters(characterList: List<CharacterViewModel>) {
        binding.recyclerCharacters.adapter = CharacterAdapter(characterList) {
            presenter.characterClicked(it)
        }
    }

    override fun showLoading() {//TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {//TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showConnectionError() {//TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDefaultError() {//TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(errorDescription: String) {//TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}