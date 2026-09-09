package com.cesarlucasjunior.webclientrickandmorty.core.usecase

import com.cesarlucasjunior.webclientrickandmorty.adapter.exception.NoSuchCharacterException
import com.cesarlucasjunior.webclientrickandmorty.core.domain.Character
import com.cesarlucasjunior.webclientrickandmorty.core.domain.CharacterTest
import com.cesarlucasjunior.webclientrickandmorty.core.ports.out.LoadCharactersOutputPort
import io.github.serpro69.kfaker.Faker
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import reactor.core.publisher.Mono

class LoadCharactersUseCaseTest {

    private val loadCharactersOutputPort = mockk<LoadCharactersOutputPort>()
    private val loadCharactersUseCase = LoadCharactersUseCase(loadCharactersOutputPort)

    private val idCharacter = 1
    private val character = CharacterTest().oneCharacterTest()

    @Test
    fun `should return a character by id`() {
        //Environment
        every { loadCharactersOutputPort.getCharacterById(idCharacter.toString())
        } returns Mono.just(character)

        every { loadCharactersUseCase.loadCharacterById(idCharacter.toString())
        } returns Mono.just(character)
        //Action

        //Assert
        assertDoesNotThrow {
            loadCharactersUseCase.loadCharacterById(idCharacter.toString())
        }
        verify(exactly = 1) {
            loadCharactersOutputPort.getCharacterById(idCharacter.toString())
        }
    }

    @Test
    fun `should not found a valid character`(){
        every { loadCharactersOutputPort.getCharacterById(idCharacter.toString())
        } throws NoSuchCharacterException()
        assertThrows<NoSuchCharacterException> { loadCharactersUseCase.loadCharacterById(idCharacter.toString()) }
    }
}