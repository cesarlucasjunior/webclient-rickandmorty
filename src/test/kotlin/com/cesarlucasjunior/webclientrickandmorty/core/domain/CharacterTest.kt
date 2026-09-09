package com.cesarlucasjunior.webclientrickandmorty.core.domain

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CharacterTest {

    @Test
    fun `Character Building Test`(){
        //Environment
        val character = mockk<Character>()
        //Action
        every { character.id } returns "1"
        every { character.name } returns "Summer Smith"
        every { character.status } returns "Alive"
        every { character.species } returns "Human"
        every { character.image } returns "https://rickandmortyapi.com/api/character/avatar/3.jpeg"
        every { character.episode } returns listOf("https://rickandmortyapi.com/api/episode/6",
            "https://rickandmortyapi.com/api/episode/7",
            "https://rickandmortyapi.com/api/episode/8")
        //Assert
        assertEquals("1", character.id)
        assertEquals("Summer Smith", character.name)
        assertEquals("Alive", character.status)
        assertEquals("Human", character.species)
        assertEquals("https://rickandmortyapi.com/api/character/avatar/3.jpeg", character.image)
        assertEquals(listOf("https://rickandmortyapi.com/api/episode/6",
            "https://rickandmortyapi.com/api/episode/7",
            "https://rickandmortyapi.com/api/episode/8"), character.episode)
    }

    fun oneCharacterTest() = Character("1", "Summer Smith", "Alive", "Human",
        "https://rickandmortyapi.com/api/character/avatar/3.jpeg", listOf("https://rickandmortyapi.com/api/episode/6",
            "https://rickandmortyapi.com/api/episode/7",
            "https://rickandmortyapi.com/api/episode/8"))
}