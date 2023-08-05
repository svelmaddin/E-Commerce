package az.sariyevtech.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> authorNotFound(ProductNotFoundException productNotFoundException) {
        List<String> detail = new ArrayList<>();
        detail.add(productNotFoundException.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("Product not Found !", detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity<?> storeNotFound(StoreNotFoundException storeNotFoundException) {
        List<String> detail = new ArrayList<>();
        detail.add(storeNotFoundException.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("Store not Found !", detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
