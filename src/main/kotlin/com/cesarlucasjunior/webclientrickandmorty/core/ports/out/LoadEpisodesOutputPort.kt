package com.cesarlucasjunior.webclientrickandmorty.core.ports.out

import com.cesarlucasjunior.webclientrickandmorty.core.domain.Episode
import com.cesarlucasjunior.webclientrickandmorty.core.domain.ListOfEpisodes
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface LoadEpisodesOutputPort {
    fun getEpisodeById(id: String): Mono<Episode>

    fun getAllEpisodes(): Flux<ListOfEpisodes>
}