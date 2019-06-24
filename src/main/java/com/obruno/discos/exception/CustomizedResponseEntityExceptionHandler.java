package com.obruno.discos.exception;

/**
 * @author Bruno Oliveira
 * @version 1.0
 * @since 24/06/2019
 * <p>
 * Controller responsável por rastrear as exceções e gerando um retorno adequado.
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetailsResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetailsResponse errorDetailsResponse = new ErrorDetailsResponse(new Date(), ex.getMessage(),
                request.getDescription(true));
        return new ResponseEntity<>(errorDetailsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DiscoCreationFromSpotifyApiException.class)
    public final ResponseEntity<ErrorDetailsResponse> handleDiscoCreationFromSpotifyApiException(DiscoCreationFromSpotifyApiException ex, WebRequest request) {
        ErrorDetailsResponse errorDetailsResponse = new ErrorDetailsResponse(new Date(), ex.getLocalizedMessage(),
                request.getDescription(true));
        return new ResponseEntity<>(errorDetailsResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DiscoNotFoundException.class)
    public final ResponseEntity<ErrorDetailsResponse> handleDiscoNotFoundException(DiscoNotFoundException ex, WebRequest request) {
        ErrorDetailsResponse errorDetailsResponse = new ErrorDetailsResponse(new Date(), ex.getMessage(),
                request.getDescription(true));
        return new ResponseEntity<>(errorDetailsResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VendaNotFoundException.class)
    public final ResponseEntity<ErrorDetailsResponse> handleVendaEmptyException(VendaNotFoundException ex, WebRequest request) {
        ErrorDetailsResponse errorDetailsResponse = new ErrorDetailsResponse(new Date(), ex.getMessage(),
                request.getDescription(true));
        return new ResponseEntity<>(errorDetailsResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaxaNotFoundException.class)
    public final ResponseEntity<ErrorDetailsResponse> handleTaxaNotFoundException(TaxaNotFoundException ex, WebRequest request) {
        ErrorDetailsResponse errorDetailsResponse = new ErrorDetailsResponse(new Date(), ex.getMessage(),
                request.getDescription(true));
        return new ResponseEntity<>(errorDetailsResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IntervalDateException.class)
    public final ResponseEntity<ErrorDetailsResponse> handleIntervalDateException(IntervalDateException ex, WebRequest request) {
        ErrorDetailsResponse errorDetailsResponse = new ErrorDetailsResponse(new Date(), ex.getMessage(),
                request.getDescription(true));
        return new ResponseEntity<>(errorDetailsResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SpotifyInvalidToken.class)
    public final ResponseEntity<ErrorDetailsResponse> handleSpotifyInvalidToken(SpotifyInvalidToken ex, WebRequest request) {
        ErrorDetailsResponse errorDetailsResponse = new ErrorDetailsResponse(new Date(), ex.getMessage(),
                request.getDescription(true));
        return new ResponseEntity<>(errorDetailsResponse, HttpStatus.NOT_FOUND);
    }

}