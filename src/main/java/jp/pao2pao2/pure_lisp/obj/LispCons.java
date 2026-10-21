package jp.pao2pao2.pure_lisp.obj;

import java.util.Objects;

public final class LispCons implements LispObj {
    
    private final LispObj car;
    private final LispObj cdr;
    
    public LispCons(LispObj car, LispObj cdr) {
        this.car = Objects.requireNonNull(
                car,
                "car must not be null"
        );
        this.cdr = Objects.requireNonNull(
                cdr,
                "cdr must not be null"
        );
    }
    
    @Override
    public LispObj car() {
        return car;
    }
    
    @Override
    public LispObj cdr() {
        return cdr;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildListString(sb, this);
        return sb.toString();
    }
    
    private void buildListString(StringBuilder sb, LispObj obj) {
        sb.append('(');
        buildListBodyString(sb, obj);
        sb.append(')');
    }
    
    private void buildListBodyString(
            StringBuilder sb,
            LispObj obj) {
        
        // car
        sb.append(obj.car().toString());
        
        if (obj.cdr() == LispSymbol.NIL) {
            return;
        }
        
        // cdr
        if (obj.cdr().isAtom()) {
            // dot pair
            sb.append(" . ");
            sb.append(obj.cdr().toString());
        } else {
            sb.append(' ');
            buildListBodyString(sb, obj.cdr());
        }
    }

}
