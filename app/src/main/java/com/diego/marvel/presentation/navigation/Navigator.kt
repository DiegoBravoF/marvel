package com.diego.marvel.presentation.navigation

import android.app.Activity
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.diego.marvel.presentation.character.detail.CharacterDetailActivity
import com.diego.marvel.presentation.model.CharacterViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator
@Inject constructor() {
    fun showHeroDetails(
        activity: Activity,
        characterViewModel: CharacterViewModel,
        view: ImageView
    ) {
        val intent = CharacterDetailActivity.buildIntent(activity, characterViewModel)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            activity,
            view,
            view.transitionName
        )
        activity.startActivity(intent, options.toBundle())
    }
}