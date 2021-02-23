package com.diego.marvel.presentation.character.list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.diego.marvel.databinding.ActivityCharacterListBinding
import com.diego.marvel.presentation.character.list.adapter.CharacterAdapter
import com.diego.marvel.presentation.common.BaseActivity
import com.diego.marvel.presentation.model.CharacterViewModel
import com.diego.marvel.presentation.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListActivity : BaseActivity<ActivityCharacterListBinding>(), CharacterListView {
    override fun getViewBinding() = ActivityCharacterListBinding.inflate(layoutInflater)

    @Inject
    lateinit var presenter: CharacterListPresenter

    @Inject
    lateinit var navigator: Navigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
        binding.recyclerCharacters.addItemDecoration(
            DividerItemDecoration(
                this,
                RecyclerView.VERTICAL
            )
        )
    }

    override fun fillCharacters(characterList: List<CharacterViewModel>) {
        binding.recyclerCharacters.visibility = View.VISIBLE
        binding.recyclerCharacters.adapter = CharacterAdapter(characterList) { character, image ->
            navigator.showHeroDetails(this, character, image)
        }
    }

    override fun showLoading() {
        binding.progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progress.visibility = View.GONE
    }
}