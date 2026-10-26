package jp.pao2pao2.pure_lisp.read;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jp.pao2pao2.pure_lisp.obj.LispObj;
import jp.pao2pao2.pure_lisp.obj.LispSymbol;

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
    
    @Test
    void readEmptyListReturnsNil() {
        LispObj obj = read("()");
        
        assertSame(LispSymbol.NIL, obj);
    }
    
    @Test
    void readSingleElementList() {
        LispObj obj = read("(a)");
        
        assertEquals("(a)", obj.toString());
    }
    
    @Test
    void readSingleElementListWithDifferentSymbol() {
        LispObj obj = read("(b)");
        
        assertEquals("(b)", obj.toString());
    }
    
    @Test
    void readSingleElementListConsumesClosingParen() {
        SourceCursor cursor = new SourceCursor("(a)");
        SExprReader reader = new SExprReader();
        
        reader.readExpr(cursor);
        
        assertTrue(cursor.eof());
    }
}
