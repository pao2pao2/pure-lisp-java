package jp.pao2pao2.pure_lisp.read;

import jp.pao2pao2.pure_lisp.obj.LispCons;
import jp.pao2pao2.pure_lisp.obj.LispObj;
import jp.pao2pao2.pure_lisp.obj.LispSymbol;

public class SExprReader {

    public LispObj readExpr(String source) {
        return readExpr(new SourceCursor(source));
    }
    
    public LispObj readExpr(SourceCursor cursor) {
        
        cursor.skipWhitespace();
        
        if (cursor.eof()) {
            throw new IllegalArgumentException(
                    "no expression to read"
            );
        }
        
        if (cursor.peek() == ')') {
            throw new IllegalArgumentException(
                    "unexpected ')'"
            );
        }
        
        if (cursor.peek() != '(') {
            return readSymbol(cursor);
        }
        
        return readList(cursor);
    }
    
    private LispObj readSymbol(SourceCursor cursor) {
        StringBuilder sb = new StringBuilder();
        
        if (cursor.eof()) {
            throw new IllegalArgumentException(
                    "no expression to read"
            );
        }
        
        while (!cursor.eof()
                && !Character.isWhitespace(cursor.peek())
                && cursor.peek() != '('
                && cursor.peek() != ')') {
            sb.append(cursor.next());
        }
        
        return new LispSymbol(sb.toString());
    }
    
    private LispObj readList(SourceCursor cursor) {
        cursor.next();
        
        return readListBody(cursor);
    }
    
    private LispObj readListBody(SourceCursor cursor) {
        cursor.skipWhitespace();
        
        if (cursor.eof()) {
            throw new IllegalArgumentException(
                    "unexpected EOF"
            );
        }
        
        if (cursor.peek() == ')') {
            cursor.next();
            return LispSymbol.NIL;
        }
        
        LispObj obj = readExpr(cursor);
        
        return new LispCons(obj, readListBody(cursor));
    }
}
