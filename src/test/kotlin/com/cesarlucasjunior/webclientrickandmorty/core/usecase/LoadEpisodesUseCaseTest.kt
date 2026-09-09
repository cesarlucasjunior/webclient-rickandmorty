package com.cesarlucasjunior.webclientrickandmorty.core.usecase


import com.cesarlucasjunior.webclientrickandmorty.adapter.exception.NoSuchCharacterException
import com.cesarlucasjunior.webclientrickandmorty.adapter.exception.NoSuchEpisodeException
import com.cesarlucasjunior.webclientrickandmorty.core.domain.EpisodeTest
import com.cesarlucasjunior.webclientrickandmorty.core.domain.ListOfEpisodesTest
import com.cesarlucasjunior.webclientrickandmorty.core.ports.out.LoadEpisodesOutputPort
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class LoadEpisodesUseCaseTest {

    private val loadEpisodesOutputPort = mockk<LoadEpisodesOutputPort>()
    private val loadEpisodesUseCase = LoadEpisodesUseCase(loadEpisodesOutputPort)

    private val idEpisode = "1"
    private val episodes = EpisodeTest().oneEpisodeTest()
    private val listOfEpisodes = ListOfEpisodesTest().listOfEpisodesTest()

    @Test
    fun `should return a episode by id`() {
        every { loadEpisodesOutputPort.getEpisodeById(idEpisode)
        } returns Mono.just(episodes)

        every { loadEpisodesUseCase.loadEpisodeById(idEpisode)
        } returns Mono.just(episodes)

        assertDoesNotThrow {
            loadEpisodesUseCase.loadEpisodeById(idEpisode)
        }
    }

    @Test
    fun `should return a list of episodes`() {
        every { loadEpisodesOutputPort.getAllEpisodes()
        } returns Flux.just(listOfEpisodes)

        every { loadEpisodesUseCase.loadAllEpisodes()
        } returns Flux.just(listOfEpisodes)

        assertDoesNotThrow {
            loadEpisodesUseCase.loadAllEpisodes()
        }
    }

    @Test
    fun `should not found a valid episode`() {
        every { loadEpisodesOutputPort.getAllEpisodes()
        } throws NoSuchEpisodeException()

        assertThrows<NoSuchEpisodeException> { loadEpisodesUseCase.loadAllEpisodes() }
    }


}