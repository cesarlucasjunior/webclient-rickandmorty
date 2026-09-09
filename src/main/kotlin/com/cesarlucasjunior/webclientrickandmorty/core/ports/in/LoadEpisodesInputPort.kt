package com.cesarlucasjunior.webclientrickandmorty.core.ports.`in`

import com.cesarlucasjunior.webclientrickandmorty.core.domain.Episode
import com.cesarlucasjunior.webclientrickandmorty.core.domain.ListOfEpisodes
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface LoadEpisodesInputPort {

    fun loadEpisodeById(id: String): Mono<Episode>

    fun loadAllEpisodes(): Flux<ListOfEpisodes>
}