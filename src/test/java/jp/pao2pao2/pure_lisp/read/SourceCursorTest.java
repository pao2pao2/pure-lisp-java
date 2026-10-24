package jp.pao2pao2.pure_lisp.read;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SourceCursorTest {

    @Test
    void peekDoesNotAdvance() {
        SourceCursor cursor = new SourceCursor("abc");
        
        assertEquals('a', cursor.peek());
        assertEquals('a', cursor.peek());
    }
    
    @Test
    void nextReturnsCurrentCharAndAdvances() {
        SourceCursor cursor = new SourceCursor("abc");
        
        assertEquals('a', cursor.next());
        assertEquals('b', cursor.peek());
        assertEquals('b', cursor.next());
        assertEquals('c', cursor.next());
    }
    
    @Test
    void eofDetectsEndOfSource() {
        SourceCursor cursor = new SourceCursor("abc");
        
        assertFalse(cursor.eof());
        assertEquals('a', cursor.next());
        assertFalse(cursor.eof());
        assertEquals('b', cursor.next());
        assertFalse(cursor.eof());
        assertEquals('c', cursor.next());
        assertTrue(cursor.eof());
    }
    
    @Test
    void eofIsTrueForEmptySource() {
        SourceCursor cursor = new SourceCursor("");
        
        assertTrue(cursor.eof());
    }

}
