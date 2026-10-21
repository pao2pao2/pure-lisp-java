package jp.pao2pao2.pure_lisp.obj;

interface LispObj {

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
}
