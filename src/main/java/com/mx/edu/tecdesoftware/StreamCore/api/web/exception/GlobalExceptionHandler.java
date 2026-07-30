package com.mx.edu.tecdesoftware.StreamCore.api.web.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // 400 - ej. mandar "abc" en un path variable que espera un int
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = "El parámetro '" + ex.getName() + "' debe ser de tipo " +
                (ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "válido") + ".";
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad Request", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // 400 - JSON mal formado o tipos de datos primitivos incorrectos en el body
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad Request",
                "El cuerpo de la petición contiene datos inválidos o mal formados.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // 400 - validaciones de negocio manuales (si usas IllegalArgumentException en algún service)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException ex) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // 404 - recurso no encontrado (ej. delete de un contenido que ya no existe)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiError> handleNotFound(NoSuchElementException ex) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // 409 - fallas de integridad referencial: FK inexistente, duplicados, etc.
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrity(DataIntegrityViolationException ex) {
        ApiError error = new ApiError(HttpStatus.CONFLICT.value(), "Conflict",
                "La operación viola una regla de integridad de datos (llave foránea inexistente o registro duplicado).");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // 500 - cualquier otro error no controlado (red de seguridad, ya no debería tronar "crudo")
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error",
                "Ocurrió un error inesperado en el servidor.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}