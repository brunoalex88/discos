package com.obruno.discos.exception;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Exceção disparada quando nenhum item for informado para a venda.
 */

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VendaEmptyException extends RuntimeException {
    private static final String VENDA_EMPTY = "Nenhum item para venda foi informado!";

    public VendaEmptyException() {
        super(VENDA_EMPTY);
    }

}
