package com.diego.marvel.presentation.character.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.diego.marvel.databinding.ItemCharacterBinding
import com.diego.marvel.presentation.model.CharacterViewModel

class CharacterAdapter(
    private val characterList: List<CharacterViewModel>,
    private val characterClicked: (CharacterViewModel, ImageView) -> Unit
) : RecyclerView.Adapter<CharacterViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: CharacterViewHolder,
        position: Int
    ) {
        holder.bind(characterList[position], characterClicked)
    }

    override fun getItemCount() = characterList.size
}