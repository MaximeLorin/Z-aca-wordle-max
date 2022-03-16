package com.zenika.academy.barbajavas.wordle.domain.controller;

import com.zenika.academy.barbajavas.wordle.domain.service.BadLengthException;
import com.zenika.academy.barbajavas.wordle.domain.service.IllegalWordException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({BadLengthException.class, IllegalWordException.class})
    public final ResponseEntity<ApiError> handleException(Exception e, WebRequest request){
        HttpHeaders headers=new HttpHeaders();

        if(e instanceof BadLengthException){
            HttpStatus status = HttpStatus.NOT_FOUND;
            BadLengthException badle = (BadLengthException) e;

            return wordWrongLengthException(badle, headers, status, request);
        }else if(e instanceof IllegalWordException){
            HttpStatus status = HttpStatus.NOT_FOUND;
            IllegalWordException illwe = (IllegalWordException) e;

            return illegalWordException(illwe, headers, status, request);
        }else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(e, null, headers, status, request);
        }
    }

    private ResponseEntity<ApiError> handleExceptionInternal(Exception e, ApiError o, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, e, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(o, headers, status);
    }

    protected ResponseEntity<ApiError> illegalWordException(IllegalWordException illwe, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(illwe.getMessage());
        return handleExceptionInternal(illwe, new ApiError(status.value(),"Mot inconnu"), headers, status, request);
    }

    protected ResponseEntity<ApiError> wordWrongLengthException(BadLengthException badle, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(badle.getMessage());
        return handleExceptionInternal(badle, new ApiError(status.value(),"Mot de la mauvaise longueur"), headers, status, request);
    }
}
