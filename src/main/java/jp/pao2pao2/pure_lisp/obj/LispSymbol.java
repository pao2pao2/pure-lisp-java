package jp.pao2pao2.pure_lisp.obj;

public class LispSymbol implements LispObj {
    
    public static final LispObj NIL = new LispSymbol("nil");
    
    private final String name;
    
    public LispSymbol(String name) {
        this.name = name;
    }
    
    @Override
    public boolean isNil() {
        return this == NIL;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
