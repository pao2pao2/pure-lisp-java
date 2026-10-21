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

}
