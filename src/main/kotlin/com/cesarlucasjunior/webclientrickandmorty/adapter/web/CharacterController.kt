package com.cesarlucasjunior.webclientrickandmorty.adapter.web

import com.cesarlucasjunior.webclientrickandmorty.adapter.integration.CharacterService
import com.cesarlucasjunior.webclientrickandmorty.core.domain.Character
import com.cesarlucasjunior.webclientrickandmorty.core.ports.`in`.LoadCharactersInputPort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/webclient")
class CharacterController(private val loadCharactersInputPort: LoadCharactersInputPort) {

    @GetMapping("/character/{id}")
    fun getCharacters(@PathVariable id:String): Mono<Character> {
        return loadCharactersInputPort.loadCharacterById(id)
    }
}