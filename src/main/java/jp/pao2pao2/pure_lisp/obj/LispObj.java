package jp.pao2pao2.pure_lisp.obj;

public sealed interface LispObj 
        permits LispSymbol, LispCons {

    default LispObj car() {
        throw new UnsupportedOperationException("car");
    }
    
    default LispObj cdr() {
        throw new UnsupportedOperationException("cdr");
    }
    
    default boolean isAtom() {
        return false;
    }
    
    default boolean isNil() {
        return false;
    }
    
    default boolean isT() {
        return false;
    }
    
    default String sym() {
        throw new UnsupportedOperationException(
                "not a symbol"
        );
    }
}
