package jp.pao2pao2.pure_lisp.read;

public class SourceCursor {
    
    private final String source;
    private int pos;
    
    public SourceCursor(String source) {
        this.source = source;
    }
    
    public char peek() {
        return source.charAt(pos);
    }
    
    public char next() {
        return source.charAt(pos++);
    }
    
    public boolean eof() {
        return pos >= source.length();
    }
}
