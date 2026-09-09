package com.cesarlucasjunior.webclientrickandmorty.core.usecase

import com.cesarlucasjunior.webclientrickandmorty.core.domain.Character
import com.cesarlucasjunior.webclientrickandmorty.core.ports.`in`.LoadCharactersInputPort
import com.cesarlucasjunior.webclientrickandmorty.core.ports.out.LoadCharactersOutputPort
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class LoadCharactersUseCase(private val loadCharactersOutputPort: LoadCharactersOutputPort): LoadCharactersInputPort {
    override fun loadCharacterById(id: String): Mono<Character> {
        return loadCharactersOutputPort.getCharacterById(id)
    }
}