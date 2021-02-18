package com.diego.marvel.presentation.character.list.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diego.marvel.databinding.ItemCharacterBinding
import com.diego.marvel.presentation.model.CharacterViewModel

class CharacterViewHolder(private val view: ItemCharacterBinding) :
    RecyclerView.ViewHolder(view.root) {
    fun bind(
        character: CharacterViewModel,
        characterClicked: (CharacterViewModel, ImageView) -> Unit
    ) {
        view.textName.text = character.name
        view.textDescription.text = character.description
        view.root.setOnClickListener {
            characterClicked(character, view.imageCharacter)
        }
        Glide.with(view.root.context)
            .load(character.imageUrl)
            .into(view.imageCharacter)
    }
}
