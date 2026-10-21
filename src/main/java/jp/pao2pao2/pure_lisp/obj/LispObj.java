package jp.pao2pao2.pure_lisp.obj;

public class LispObj {

    public LispObj car() {
        throw new UnsupportedOperationException("car");
    }
    
    public LispObj cdr() {
        throw new UnsupportedOperationException("cdr");
    }
    
    public boolean isNil() {
        return false;
    }
}
