package com.cesarlucasjunior.webclientrickandmorty.adapter.integration

import com.cesarlucasjunior.webclientrickandmorty.adapter.exception.NoSuchEpisodeException
import com.cesarlucasjunior.webclientrickandmorty.adapter.conf.WebConfig
import com.cesarlucasjunior.webclientrickandmorty.core.domain.Episode
import com.cesarlucasjunior.webclientrickandmorty.core.domain.ListOfEpisodes
import com.cesarlucasjunior.webclientrickandmorty.core.ports.out.LoadEpisodesOutputPort
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class EpisodeService(private val webConfig: WebConfig): LoadEpisodesOutputPort {

    override fun getEpisodeById(id: String): Mono<Episode> {
        return webConfig.webClient()
            .get()
            .uri("/episode/$id")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { Mono.error(NoSuchEpisodeException()) }
            .bodyToMono(Episode::class.java)
    }

    override fun getAllEpisodes(): Flux<ListOfEpisodes> {
        return webConfig.webClient()
            .get()
            .uri("/episode/")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError) { Mono.error(NoSuchEpisodeException()) }
            .bodyToFlux(ListOfEpisodes::class.java)
    }
}