package it.interno.payment.exception;

public class PaymentFallbackException extends RuntimeException {

    public PaymentFallbackException(String message) {
        super(message);
    }

}
