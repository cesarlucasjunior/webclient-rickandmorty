package com.cesarlucasjunior.webclientrickandmorty.core.usecase

import com.cesarlucasjunior.webclientrickandmorty.core.domain.Episode
import com.cesarlucasjunior.webclientrickandmorty.core.domain.ListOfEpisodes
import com.cesarlucasjunior.webclientrickandmorty.core.ports.`in`.LoadEpisodesInputPort
import com.cesarlucasjunior.webclientrickandmorty.core.ports.out.LoadEpisodesOutputPort
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class LoadEpisodesUseCase(private val loadEpisodesOutputPort: LoadEpisodesOutputPort): LoadEpisodesInputPort {
    override fun loadEpisodeById(id: String): Mono<Episode> {
        return loadEpisodesOutputPort.getEpisodeById(id)
    }

    override fun loadAllEpisodes(): Flux<ListOfEpisodes> {
        return loadEpisodesOutputPort.getAllEpisodes()
    }
}