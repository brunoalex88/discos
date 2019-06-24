package com.obruno.discos.exception;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Exceção disparada quando não for possível encontrar algum disco.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DiscoNotFoundException extends RuntimeException {
    private static final String NOT_FOUND_ID = "Disco não encontrado: %s";
    private static final String NOT_FOUND = "Nenhum disco foi encontrado";

    public DiscoNotFoundException() {
        super(NOT_FOUND);
    }

    public DiscoNotFoundException(Long id) {
        super(String.format(NOT_FOUND_ID, id));
    }

}
