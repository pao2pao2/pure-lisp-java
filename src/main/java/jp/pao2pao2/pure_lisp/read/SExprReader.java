package jp.pao2pao2.pure_lisp.read;

import jp.pao2pao2.pure_lisp.obj.LispObj;
import jp.pao2pao2.pure_lisp.obj.LispSymbol;

public class SExprReader {

    public LispObj readExpr(String source) {
        return new LispSymbol(source);
    }
}
