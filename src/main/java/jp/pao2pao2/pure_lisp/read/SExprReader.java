package jp.pao2pao2.pure_lisp.read;

import jp.pao2pao2.pure_lisp.obj.LispObj;
import jp.pao2pao2.pure_lisp.obj.LispSymbol;

public class SExprReader {

    public LispObj readExpr(String source) {
        return readExpr(new SourceCursor(source));
    }
    
    public LispObj readExpr(SourceCursor cursor) {
        StringBuilder sb = new StringBuilder();
        
        cursor.skipWhitespace();
        
        while (!cursor.eof()
                && !Character.isWhitespace(cursor.peek())) {
            sb.append(cursor.next());
        }
        
        return new LispSymbol(sb.toString());
    }
}
