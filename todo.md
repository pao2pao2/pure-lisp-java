# TODO

## 未決事項

- [ ] READで`nil`を読み込んだとき、`LispSymbol.NIL`を返すか、通常のシンボルを生成するか
- [ ] READで`t`を読み込んだときの扱い
- [ ] EOF時の`peek()`と`next()`の振る舞い
- [ ] 空文字列をシンボル名として許可するか

## 実装予定

- [x] SExprReaderにSourceCursorを導入する
- [ ] リストの読み取りを実装する
- [ ] ドット対の読み取りを実装する

## 後で検討

- [ ] Javadocの整備
