package com.diego.marvel.presentation.character.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.diego.marvel.databinding.ActivityCharacterDetailBinding
import com.diego.marvel.presentation.common.BaseActivity
import com.diego.marvel.presentation.model.CharacterViewModel
import com.diego.marvel.presentation.navigation.Navigator
import com.diego.marvel.util.DeepLinkManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterDetailActivity : BaseActivity<ActivityCharacterDetailBinding>(),
    CharacterDetailView {
    companion object {
        private const val CHARACTER = "com.diego.CHARACTER"

        fun buildIntent(
            context: Context,
            characterViewModel: CharacterViewModel
        ): Intent {
            val intent = Intent(context, CharacterDetailActivity::class.java)
            intent.putExtra(CHARACTER, characterViewModel)
            return intent
        }
    }

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var presenter: CharacterDetailPresenter
    override fun getViewBinding() = ActivityCharacterDetailBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
        presenter.setCharacter(intent.getParcelableExtra(CHARACTER))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        intent.dataString?.let { string ->
            DeepLinkManager.getInstance().parse(string).getCharacter {
                presenter.getCharacterById(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun fillCharacter(character: CharacterViewModel) {
        Glide.with(this)
            .load(character.imageUrl)
            .into(binding.imageCharacter)
        title = character.name
        binding.textDescription.text = character.description

    }

    override fun showLoading() {
        binding.progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progress.visibility = View.GONE
    }
}