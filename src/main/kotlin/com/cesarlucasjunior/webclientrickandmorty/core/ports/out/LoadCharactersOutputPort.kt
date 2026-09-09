package com.cesarlucasjunior.webclientrickandmorty.core.ports.out

import com.cesarlucasjunior.webclientrickandmorty.core.domain.Character
import reactor.core.publisher.Mono

interface LoadCharactersOutputPort {
    fun getCharacterById(id: String): Mono<Character>
}