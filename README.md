# Java Silver 学習リポジトリ

Java Silver（Oracle Certified Professional Java SE）の試験対策として、問題演習と学習ポイントをまとめたリポジトリです。
Neovim の操作学習メモも合わせて記録しています。

## ファイル構成

| ファイル | 内容 |
|---|---|
| `Main.java` | 問題検証用のコード |
| `java-point.md` | Java Silver 問題の解説・学習ポイント |
| `nvim-point.md` | Neovim 操作の学習ポイント |
| `CLAUDE.md` | Claude へのルール設定 |

## 学習トピック（java-point.md）

- int 同士の演算（除算・剰余）
- String の `==` と `equals()` の違い
- 静的初期化ブロック（`static {}`）
- 配列の範囲外アクセス（`ArrayIndexOutOfBoundsException`）
- switch 文のフォールスルー

## 動作確認

```bash
javac Main.java && java Main
```

## 環境

- Java SE 11 以上
- Neovim
