package jp.pao2pao2.pure_lisp.read;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jp.pao2pao2.pure_lisp.obj.LispObj;

class SExprReaderTest {

    private LispObj read(String source) {
        SExprReader reader = new SExprReader();
        return reader.readExpr(source);
    }
    
    @Test
    void readSymbol() {
        LispObj obj = read("a");
        
        assertEquals(
                "a",
                obj.toString()
        );
    }
    
    @Test
    void readSymbolAfterLeadingWhitespace() {
        LispObj obj = read("     a");
        
        assertEquals("a", obj.toString());
    }
    
    @Test
    void readSymbolBeforeTrailingWhitespace() {
        LispObj obj = read("a   ");
        
        assertEquals("a", obj.toString());
    }
    
    @Test
    void readRejectsEmptySource() {
        assertThrows(
                IllegalArgumentException.class,
                () -> read("")
        );
    }
    
    @Test
    void readRejectsWhitespaceOnlySource() {
        assertThrows(
                IllegalArgumentException.class,
                () -> read("   ")
        );
    }
}
