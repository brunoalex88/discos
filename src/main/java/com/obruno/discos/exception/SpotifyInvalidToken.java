package com.obruno.discos.exception;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Exceção disparada quando o token utilizada para chamadas a API do Spotify for inválida.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class SpotifyInvalidToken extends RuntimeException {
    private static final String MESSAGE = "Token para acesso a Spotify API é inválido. Favor obter novo token";

    public SpotifyInvalidToken() {
        super(MESSAGE);
    }

}
