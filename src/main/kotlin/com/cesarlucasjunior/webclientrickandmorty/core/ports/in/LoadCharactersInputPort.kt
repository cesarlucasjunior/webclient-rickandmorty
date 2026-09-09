package com.cesarlucasjunior.webclientrickandmorty.core.ports.`in`

import com.cesarlucasjunior.webclientrickandmorty.core.domain.Character
import reactor.core.publisher.Mono

interface LoadCharactersInputPort {
    fun loadCharacterById(id: String): Mono<Character>
}