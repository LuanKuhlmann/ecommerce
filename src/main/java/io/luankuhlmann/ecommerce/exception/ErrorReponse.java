package io.luankuhlmann.ecommerce.exception;

import java.time.Instant;
import java.util.Map;

public record ErrorReponse(
       Instant timeStamp,
       int status,
       String error,
       Map<String, String> errors,
       String path
) {
}
