package com.obruno.discos.exception;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Exceção disparada quando não for possível encontrar uma Taxa com os
 * parâmetros informados
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.DayOfWeek;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaxaNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Não foi possível encontrar uma taxa para o gênero %s e o dia da semana %s";

    public TaxaNotFoundException(String genero, DayOfWeek diaDaSemana) {
        super(String.format(MESSAGE, genero, diaDaSemana));
    }

}
