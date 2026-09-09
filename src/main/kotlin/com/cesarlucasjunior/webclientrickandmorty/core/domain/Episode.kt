package com.cesarlucasjunior.webclientrickandmorty.core.domain

data class Episode(
    val id: String,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)