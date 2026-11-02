package jp.pao2pao2.pure_lisp.eval;

import jp.pao2pao2.pure_lisp.error.LispEvalException;
import jp.pao2pao2.pure_lisp.obj.LispObj;
import jp.pao2pao2.pure_lisp.obj.LispSymbol;

public class Evaluator {
    
    private static final LispSymbol QUOTE =
            new LispSymbol("quote");
    private static final LispSymbol CAR =
            new LispSymbol("car");
    private static final LispSymbol CDR =
            new LispSymbol("cdr");
    private static final LispSymbol ATOM =
            new LispSymbol("atom");
    
    public EvalResult eval(LispObj expr, LispObj env) { 
        if (!expr.isAtom() && eq(expr.car(), QUOTE)) {
            return evalQuote(expr, env);
        }
        
        if (!expr.isAtom() && eq(expr.car(), ATOM)) {
            return evalAtom(expr, env);
        }
        
        if (!expr.isAtom() && eq(expr.car(), CAR)) {
            return evalCar(expr, env);
        }
        
        if (!expr.isAtom() && eq(expr.car(), CDR)) {
            return evalCdr(expr, env);
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
    
    private EvalResult evalAtom(LispObj expr, LispObj env) {
        LispObj arg = requireSingleArgument(expr, "atom");
        EvalResult result = eval(arg, env);
        
        return new EvalResult(
                result.value().isAtom()
                    ? LispSymbol.T
                    : LispSymbol.NIL,
                env
        );
    }
    
    private EvalResult evalCar(LispObj expr, LispObj env) {
        LispObj arg = requireSingleArgument(expr, "car");
        
        EvalResult result = eval(arg, env);
        
        return new EvalResult(
                result.value().car(),
                env
        );
    }
    
    private EvalResult evalCdr(LispObj expr, LispObj env) {
        LispObj arg = requireSingleArgument(expr, "cdr");
        
        EvalResult result = eval(arg, env);
        
        return new EvalResult(
                result.value().cdr(),
                env
        );
    }
    
    private LispObj requireSingleArgument(
            LispObj expr,
            String functionName) {
        
        LispObj args = expr.cdr();
        
        if (args.isNil() || !args.cdr().isNil()) {
            throw new LispEvalException(
                    functionName + " expects exactly one argument"
            );
        }
        
        return args.car();
    }
}
