package jp.pao2pao2.pure_lisp.obj;

public final class LispSymbol implements LispObj {
    
    public static final LispObj NIL = new LispSymbol("nil");
    
    public static final LispObj T = new LispSymbol("t");
    
    private final String name;
    
    public LispSymbol(String name) {
        this.name = name;
    }
    
    @Override
    public String sym() {
        return name;
    }
    
    @Override
    public boolean isAtom() {
        return true;
    }
    
    @Override
    public boolean isNil() {
        return this == NIL;
    }
    
    @Override
    public boolean isT() {
        return this == T;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
