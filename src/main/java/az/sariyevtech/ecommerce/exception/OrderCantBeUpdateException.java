package az.sariyevtech.ecommerce.exception;

public class OrderCantBeUpdateException extends RuntimeException{
    public OrderCantBeUpdateException(String message) {
        super(message);
    }
}
