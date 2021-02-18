package com.diego.marvel.presentation.character.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.diego.marvel.databinding.ActivityCharacterDetailBinding
import com.diego.marvel.presentation.common.BaseActivity
import com.diego.marvel.presentation.model.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterDetailActivity : BaseActivity<ActivityCharacterDetailBinding>(),
    CharacterDetailView {
    companion object {
        private const val CHARACTER = "com.diego.CHARACTER"

        fun buildIntent(context: Context, characterViewModel: CharacterViewModel): Intent {
            val intent = Intent(context, CharacterDetailActivity::class.java)
            intent.putExtra(CHARACTER, characterViewModel)
            return intent
        }
    }

    @Inject
    lateinit var presenter: CharacterDetailPresenter
    override fun getViewBinding() = ActivityCharacterDetailBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attachView(this)
        presenter.setCharacter(intent.getParcelableExtra(CHARACTER))

    }

    override fun fillCharacter(character: CharacterViewModel) {
        Glide.with(this)
            .load(character.imageUrl)
            .into(binding.imageCharacter)
        title = character.name
        binding.textDescription.text = character.description

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