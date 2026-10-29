package jp.pao2pao2.pure_lisp.eval;

import jp.pao2pao2.pure_lisp.obj.LispObj;

public record EvalResult(
        LispObj value,
        LispObj env
) {
}
