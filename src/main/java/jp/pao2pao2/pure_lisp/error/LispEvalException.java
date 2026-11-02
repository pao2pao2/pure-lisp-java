package jp.pao2pao2.pure_lisp.error;

public class LispEvalException extends RuntimeException {
    
    public LispEvalException(String message) {
        super(message);
    }
}
