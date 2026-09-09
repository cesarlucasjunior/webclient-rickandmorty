package com.cesarlucasjunior.webclientrickandmorty.adapter.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.Instant

@ControllerAdvice
class GlobalExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(NoSuchCharacterException::class)
    fun handle4xxStatusCodeException(noSuchCharacterException: NoSuchCharacterException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, noSuchCharacterException.localizedMessage)
        problemDetail.title = "Id não encontrado na API"
        problemDetail.detail = "Entre com um id de personagem válido"
        problemDetail.setProperty("timestamp", Instant.now())
        return problemDetail
    }

    @ExceptionHandler(NoSuchEpisodeException::class)
    fun handleNoSuchEpisodeException(noSuchEpisodeException: NoSuchEpisodeException): ProblemDetail {
        val problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, noSuchEpisodeException.localizedMessage)
        problemDetail.title = "Lista de episódios não encontrado na API"
        problemDetail.detail = "Entre com um id de episódio válido"
        problemDetail.setProperty("timestamp", Instant.now())
        return problemDetail
    }
}