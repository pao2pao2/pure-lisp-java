package jp.pao2pao2.pure_lisp.obj;

public class LispSymbol extends LispObj {
    
    private String name;
    
    public LispSymbol(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
