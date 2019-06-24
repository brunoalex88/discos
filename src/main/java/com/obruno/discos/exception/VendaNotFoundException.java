package com.obruno.discos.exception;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Exceção dispara quando uma Venda não for informada.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VendaNotFoundException extends RuntimeException {
    private static final String NOT_FOUND_ID = "Venda não encontrada: %s";
    private static final String NOT_FOUND_DATES = "Nenhuma venda foi encontrada com o intervalo de %s e %s";

    public VendaNotFoundException(String dataInicial, String dataFinal) {
        super(String.format(NOT_FOUND_DATES, dataInicial, dataFinal));
    }

    public VendaNotFoundException(Long id) {
        super(String.format(NOT_FOUND_ID, id));
    }

}
