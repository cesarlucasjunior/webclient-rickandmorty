package com.cesarlucasjunior.webclientrickandmorty.core.domain

//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
data class Character(
    val id: String,
    val name: String,
    val status: String,
    val species: String,
    val image: String,
    val episode: List<String>
)
