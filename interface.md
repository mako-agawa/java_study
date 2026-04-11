# インターフェース まとめ

## 基本ルール

| 項目 | 内容 |
|---|---|
| メソッド | デフォルトで `public abstract` |
| フィールド | デフォルトで `public static final`（定数のみ） |
| インスタンス化 | 直接は不可。実装クラスかラムダ経由のみ |
| 多重実装 | `implements A, B` で複数実装できる |

---

## よくある間違い

### ① インターフェースをインスタンス化しようとする
```java
Greet g = new Greet(); // NG
Greet g = () -> System.out.println("hi"); // OK（関数型の場合）
```

### ② 実装クラスで全メソッドを実装し忘れる
```java
class MyClass implements Greet {
    // hello() を実装しないとコンパイルエラー
}
```

### ③ default メソッドと抽象メソッドを混同する
```java
interface A {
    void must();               // 実装必須
    default void optional() {} // 実装不要（Java 8〜）
}
```

### ④ 関数型インターフェースに抽象メソッドを2つ書く
```java
@FunctionalInterface
interface A {
    void foo();
    void bar(); // コンパイルエラー
}
```

### ⑤ 抽象クラスとの違いを混同する

| | インターフェース | 抽象クラス |
|---|---|---|
| 多重継承 | 複数 `implements` OK | 単一 `extends` のみ |
| コンストラクタ | 持てない | 持てる |
| フィールド | 定数のみ | 通常フィールドOK |
| 用途 | 能力・契約を表す | 共通の基底を表す |
