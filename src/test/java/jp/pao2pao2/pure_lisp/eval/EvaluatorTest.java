package jp.pao2pao2.pure_lisp.eval;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jp.pao2pao2.pure_lisp.obj.LispObj;
import jp.pao2pao2.pure_lisp.obj.LispSymbol;
import jp.pao2pao2.pure_lisp.read.SExprReader;
import jp.pao2pao2.pure_lisp.read.SourceCursor;

class EvaluatorTest {
    
    private LispObj read(String source) {
        return new SExprReader()
                .readExpr(new SourceCursor(source));
    }

    @Test
    void evalQuotedSymbol() {
        LispObj expr = read("(quote a)");
        LispObj env = LispSymbol.NIL;
        
        EvalResult result = new Evaluator().eval(expr, env);
        
        assertEquals("a", result.value().toString());
        assertSame(env, result.env());
    }
    
    @Test
    void evalQuotedDifferentSymbol() {
        LispObj expr = read("(quote b)");
        LispObj env = LispSymbol.NIL;
        
        EvalResult result = new Evaluator().eval(expr, env);
        
        assertEquals("b", result.value().toString());
        assertSame(env, result.env());
    }
    
    @Test
    void evalAtomicSymbol() {
        LispObj expr = read("a");
        LispObj env = LispSymbol.NIL;
        
        EvalResult result = new Evaluator().eval(expr, env);
        
        assertSame(expr, result.value());
        assertSame(env, result.env());
    }
    
    @Test
    void evalQuotedList() {
        LispObj expr = read("(quote (a b))");
        LispObj env = LispSymbol.NIL;
        
        EvalResult result = new Evaluator().eval(expr, env);
        
        assertSame(expr.cdr().car(), result.value());
        assertSame(env, result.env());
    }
    
    @Test
    void evalCar() {
        LispObj expr = read("(car (quote (a b)))");
        LispObj env = LispSymbol.NIL;
        
        EvalResult result = new Evaluator().eval(expr, env);
        
        assertEquals("a", result.value().sym());
        assertSame(env, result.env());
    }

    @Test
    void evalCarWithDifferentSymbol() {
        LispObj expr = read("(car (quote (b c)))");
        LispObj env = LispSymbol.NIL;
        
        EvalResult result = new Evaluator().eval(expr, env);
        
        assertEquals("b", result.value().sym());
        assertSame(env, result.env());
    }
    
    @Test
    void evalCarRejectsTooManyArguments() {
        LispObj expr = read("(car (quote (a b)) (quote (c d)))");
        LispObj env = LispSymbol.NIL;
        
        assertThrows(
                IllegalArgumentException.class,
                () -> new Evaluator().eval(expr, env)
        );
    }
    
    @Test
    void evalCarRejectsNoArguments() {
        LispObj expr = read("(car)");
        LispObj env = LispSymbol.NIL;
        
        assertThrows(
                IllegalArgumentException.class,
                () -> new Evaluator().eval(expr, env)
        );
    }
}
