package com.diego.marvel.presentation.characterlist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.diego.marvel.databinding.ItemCharacterBinding
import com.diego.marvel.presentation.model.CharacterViewModel

class CharacterViewHolder(private val view: ItemCharacterBinding) :
    RecyclerView.ViewHolder(view.root) {
    fun bind(character: CharacterViewModel, characterClicked: (CharacterViewModel) -> Unit) {
        view.textName.text = character.name
        view.textDescription.text = character.description
        view.root.setOnClickListener {
            characterClicked(character)
        }
    }
}
