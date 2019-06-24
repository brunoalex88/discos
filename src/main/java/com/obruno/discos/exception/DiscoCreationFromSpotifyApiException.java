package com.obruno.discos.exception;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Exceção utilizada para erros na criação dos dados iniciais do Disco a partir da Api do Spotify
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class DiscoCreationFromSpotifyApiException extends RuntimeException {

    public DiscoCreationFromSpotifyApiException(String message) {
        super(message);
    }

}
