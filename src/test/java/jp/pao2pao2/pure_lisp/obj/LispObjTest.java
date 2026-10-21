package jp.pao2pao2.pure_lisp.obj;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LispObjTest {

    @Test
    void symbolToString() {
        LispObj obj1 = new LispSymbol("a");
        LispObj obj2 = new LispSymbol("b");
        
        assertEquals("a", obj1.toString());
        assertEquals("b", obj2.toString());
    }
    
    @Test
    void nilToString() {
        assertEquals("nil", LispSymbol.NIL.toString());
    }
    
    @Test
    void nilIsNil() {
        assertTrue(LispSymbol.NIL.isNil());
    }
    
    @Test
    void consCarCdr() {
        LispObj a = new LispSymbol("a");
        LispObj b = new LispSymbol("b");
        LispObj tail = new LispCons(b, LispSymbol.NIL);
        LispObj list = new LispCons(a, tail);
        
        assertSame(a, list.car());
        assertSame(tail, list.cdr());
        assertSame(b, list.cdr().car());
        assertSame(LispSymbol.NIL, list.cdr().cdr());
    }
    
    @Test
    void consToStringAsList() {
        LispObj list1 = new LispCons(
                new LispSymbol("a"),
                new LispCons(
                    new LispSymbol("b"),
                    LispSymbol.NIL
                )
        );
        LispObj list2 = new LispCons(
                new LispSymbol("c"),
                new LispCons(
                        new LispSymbol("d"),
                        LispSymbol.NIL
                        )
                );
        
        assertEquals("(a b)", list1.toString());
        assertEquals("(c d)", list2.toString());
    }

}
