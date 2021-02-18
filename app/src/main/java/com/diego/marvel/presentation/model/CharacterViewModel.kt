package com.diego.marvel.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterViewModel(
    val name: String,
    val description: String,
    val imageUrl: String
) : Parcelable