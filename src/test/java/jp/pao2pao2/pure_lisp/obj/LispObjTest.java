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
    void tIsT() {
        assertTrue(LispSymbol.T.isT());
    }
    
    @Test
    void consIsNotAtom() {
        LispObj cons = new LispCons(
                new LispSymbol("a"),
                LispSymbol.NIL
        );
        
        assertFalse(cons.isAtom());
    }
    
    @Test
    void symbolIsAtom() {
        LispObj sym = new LispSymbol("a");
        
        assertTrue(sym.isAtom());
    }
    
    @Test
    void nilIsAtom() {
        assertTrue(LispSymbol.NIL.isAtom());
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
    
    @Test
    void consToStringAsNestedList() {
        LispObj list = new LispCons(
                new LispSymbol("a"),
                new LispCons(
                        new LispCons(
                            new LispSymbol("b"),
                            new LispCons(
                                    new LispSymbol("c"),
                                    LispSymbol.NIL
                            )
                        ),
                        LispSymbol.NIL
                )
        );
        
        assertEquals("(a (b c))", list.toString());
    }
    
    @Test
    void consToStringAsDotPair() {
        LispObj pair = new LispCons(
                new LispSymbol("a"),
                new LispSymbol("b")
        );
        
        assertEquals("(a . b)", pair.toString());
    }
    
    @Test
    void symReturnsName() {
        LispObj obj1 = new LispSymbol("a");
        LispObj obj2 = new LispSymbol("b");
        
        assertEquals("a", obj1.sym());
        assertEquals("b", obj2.sym());
    }
    
    @Test
    void symThrowsOnCons() {
        LispObj obj = new LispCons(
                new LispSymbol("a"),
                LispSymbol.NIL
        );
        
        assertThrows(
                UnsupportedOperationException.class,
                () -> obj.sym()
        );
    }
    
    @Test
    void consRejectNullCar() {
        assertThrows(
                NullPointerException.class,
                () -> new LispCons(null, LispSymbol.NIL)
        );
    }
    
    @Test
    void consRejectNullCdr() {
        assertThrows(
                NullPointerException.class,
                () -> new LispCons(LispSymbol.NIL, null)
        );
    }
    
    @Test
    void symbolRejectNullName() {
        assertThrows(
                NullPointerException.class,
                () -> new LispSymbol(null)
        );
    }
}
