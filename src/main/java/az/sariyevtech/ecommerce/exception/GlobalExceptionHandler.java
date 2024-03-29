package az.sariyevtech.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

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

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<?> orderNotFound(OrderNotFoundException orderNotFoundException) {
        List<String> detail = new ArrayList<>();
        detail.add(orderNotFoundException.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("Order not Found !", detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<?> outOfStockException(OutOfStockException outOfStockException) {
        List<String> detail = new ArrayList<>();
        detail.add(outOfStockException.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("Out Of Stock!", detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderCantBeUpdateException.class)
    public ResponseEntity<?> orderUpdate(OrderCantBeUpdateException orderCantBeUpdateException) {
        List<String> detail = new ArrayList<>();
        detail.add(orderCantBeUpdateException.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("Order update errors", detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
