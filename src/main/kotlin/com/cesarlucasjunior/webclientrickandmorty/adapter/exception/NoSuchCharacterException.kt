package com.cesarlucasjunior.webclientrickandmorty.adapter.exception

class NoSuchCharacterException: NoSuchElementException {

    constructor() : super() {
        println("Não encontramos os id na API")
    }

}