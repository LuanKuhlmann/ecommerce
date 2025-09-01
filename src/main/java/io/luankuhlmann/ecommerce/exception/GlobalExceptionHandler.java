package io.luankuhlmann.ecommerce.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorReponse> handleGenericException(Exception ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        return new ResponseEntity<>(buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Generic Error",
                errors,
                request.getRequestURI()
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorReponse> handleUniqueConstraintViolation(
            DataIntegrityViolationException ex, HttpServletRequest request) {
        String message = "Data integrity violation.";
        String field = "value";

        if (ex.getCause() instanceof ConstraintViolationException hibernateEx) {
            String constraint = hibernateEx.getConstraintName();
            if (constraint != null) {
                if (constraint.contains("users_email_key")) {
                    message = "Email já cadastrado.";
                    field = "email";
                }
            }
        }

        Map<String, String> errors = Map.of(field, message);

        return new ResponseEntity<>(buildErrorResponse(
                HttpStatus.CONFLICT,
                "Integrity error",
                errors,
                request.getRequestURI()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorReponse> handleIllegalArgumentException(
            IllegalArgumentException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("invalidArgument", Optional.ofNullable(ex.getMessage())
                .orElse("Invalid Argument"));

        return new ResponseEntity<>(buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                "Invalid Argument",
                errors,
                request.getRequestURI()), HttpStatus.BAD_REQUEST);
    }

    private ErrorReponse buildErrorResponse(HttpStatus status, String titulo,
                                            Map<String, String> erros, String caminho) {
        return new ErrorReponse(
                Instant.now(),
                status.value(),
                titulo, erros,
                caminho);
    }

}
