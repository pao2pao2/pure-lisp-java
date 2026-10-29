package jp.pao2pao2.pure_lisp.eval;

import jp.pao2pao2.pure_lisp.obj.LispObj;
import jp.pao2pao2.pure_lisp.obj.LispSymbol;

public class Evaluator {
    
    private static final LispSymbol QUOTE =
            new LispSymbol("quote");
    
    public EvalResult eval(LispObj expr, LispObj env) { 
        if (!expr.isAtom() && eq(expr.car(), QUOTE)) {
            return evalQuote(expr, env);
        }
        
        return new EvalResult(expr, env);
    }
    
    private boolean eq(LispObj x, LispObj y) {
        if (!x.isAtom() || !y.isAtom()) {
            return false;
        }
        
        return x.sym().equals(y.sym());
    }
    
    private EvalResult evalQuote(LispObj expr, LispObj env) {
        return new EvalResult(
                expr.cdr().car(),
                env
        ); 
    }
}
