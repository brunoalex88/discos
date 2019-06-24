package com.obruno.discos.exception;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Exceção disparada quando há erros na validação do intervalo entre períodos.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IntervalDateException extends RuntimeException {
    private static final String MESSAGE = "Data final é menor que a data inicial";

    public IntervalDateException() {
        super(MESSAGE);
    }

}
