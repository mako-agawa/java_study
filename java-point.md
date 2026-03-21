# Java 学習ポイント集

## Q1. int同士の演算（除算・剰余）

**問題:** `int x = 5; int y = 2;` のとき `x/y` と `x%y` の出力は？

**選択肢:**
1. `2.5` / `1`
2. `2` / `1`
3. `2` / `0`
4. コンパイルエラー

**回答:** B（`2` / `1`）

**ワンポイントアドバイス:**
int同士の割り算は小数点以下を切り捨てる整数除算になる。`5/2=2`、`5%2=1`（余り）。

---

## Q2. String の == と equals()

**問題:** 文字列リテラルと `new String()` を `==` と `equals()` で比較した結果は？

**選択肢:**
1. `true` / `true` / `true`
2. `true` / `false` / `true`
3. `false` / `false` / `true`
4. `false` / `false` / `false`

**回答:** B（`true` / `false` / `true`）

**ワンポイントアドバイス:**
文字列リテラルは文字列プール（String Pool）で同一参照を共有するため `==` が `true`。`new String()` は新しいオブジェクトを生成するので `==` は `false`。値の比較には `equals()` を使う。

---

## Q3. 静的初期化ブロック（static initializer）

**問題:** `static {}` ブロックと `main()` メソッドの実行順序は？

**選択肢:**
1. `3`
2. `13`
3. `10`
4. `0`

**回答:** B（`13`）

**ワンポイントアドバイス:**
`static {}` ブロックはクラスロード時に `main()` より先に実行される。`count=10` の後、ループで3回加算されて `13` になる。

---

## Q4. 配列の範囲外アクセス（ArrayIndexOutOfBoundsException）

**問題:** 以下のコードを実行した結果は？
```java
int[] arr = {1, 2, 3, 4, 5};
System.out.println(arr[arr.length]);
```

**選択肢:**
- A. `5`
- B. `0`
- C. `null`
- D. コンパイルエラー
- E. 実行時エラー（ArrayIndexOutOfBoundsException）

**回答:** E（実行時エラー）※間違えた問題

**ワンポイントアドバイス:**
配列のインデックスは `0` から始まるため、長さ5の配列の有効インデックスは `0〜4`。`arr.length` は `5` なので `arr[5]` は範囲外。コンパイルは通るが実行時に `ArrayIndexOutOfBoundsException` が発生する。最後の要素は `arr[arr.length - 1]` で取得する。

---

## Q5. switch文のフォールスルー（fall-through）

**問題:** 以下のコードを実行した結果は？
```java
int i = 10;
switch (i) {
  case 10:
    System.out.println("ten");
  case 20:
    System.out.println("twenty");
    break;
  case 30:
    System.out.println("thirty");
  default:
    System.out.println("other");
}
```

**選択肢:**
- A. `ten`
- B. `ten` / `twenty`
- C. `ten` / `twenty` / `thirty` / `other`
- D. コンパイルエラー

**回答:** B（`ten` / `twenty`）※間違えた問題

**ワンポイントアドバイス:**
`case 10` に `break` がないため、フォールスルーが発生し次の `case 20` も実行される。`case 20` に `break` があるのでそこで停止。意図しないフォールスルーはバグの原因になるため、各 `case` には原則 `break` を忘れずに書く。

---

## Q9. instanceof と null

**問題:** 以下のコードの出力結果は？
```java
String s = null;
System.out.println(s instanceof String);
```

**選択肢:**
- A. `true`
- B. `false`
- C. `null`
- D. コンパイルエラー
- E. `NullPointerException`
- F. `ClassCastException`

**回答:** B（`false`）※間違えた問題（F と回答）

**ワンポイントアドバイス:**
`instanceof` は `null` に対して常に `false` を返す。例外は発生しない。`null` はどの型のインスタンスでもない。

| 値 | `instanceof` の結果 |
|---|---|
| `null` | 常に `false` |
| `""` （空文字） | `true`（Stringオブジェクトのため） |
| `0` （int） | コンパイルエラー（プリミティブには使えない） |

`ClassCastException` は型キャストに失敗したときに発生する：
```java
Object obj = "hello";
Integer i = (Integer) obj;  // ClassCastException
```

---

## Q8. try-catch-finally の実行順序

**問題:** 以下のコードの出力結果は？
```java
try {
    int[] arr = new int[3];
    arr[5] = 10;
    System.out.println("A");
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("B");
} finally {
    System.out.println("C");
}
```

**選択肢:**
- A. `A`
- B. `B`
- C. `A` / `C`
- D. `B` / `C`
- E. `A` / `B` / `C`

**回答:** D（`B` / `C`）※間違えた問題（C と回答）

**ワンポイントアドバイス:**
`arr[5]` で例外が発生した時点で `"A"` はスキップされ `catch` ブロックへジャンプする。`finally` は例外の有無に関わらず必ず実行される。

`try` / `catch` / `finally` の組み合わせルール：

| 組み合わせ | 可否 |
|---|---|
| `try` + `catch` | ✅ |
| `try` + `finally` | ✅ |
| `try` + `catch` + `finally` | ✅ |
| `try` だけ | ❌ コンパイルエラー |

---

## Q7. 変数のスコープとコンパイルエラー

**問題:** 以下のコードの出力結果は？
```java
int x = 10;
if (x > 5) {
    int y = 20;
}
System.out.println(y);
```

**選択肢:**
- A. `20`
- B. `0`
- C. `null`
- D. コンパイルエラー
- E. `NullPointerException`

**回答:** D（コンパイルエラー）※間違えた問題（E と回答）

**ワンポイントアドバイス:**
`y` は `if` ブロック内で宣言されているためスコープはブロック内のみ。ブロック外から参照するとコンパイルエラーになる。`NullPointerException` はオブジェクトが `null` のときにメソッドやフィールドにアクセスした場合に発生する実行時例外。スコープ違反はコンパイル時に検出される。

**補足：エラーハンドリング（例外処理）**

```java
try {
    // 例外が発生するかもしれない処理
} catch (ArithmeticException e) {
    // 例外を捕まえて対処
} finally {
    // 例外の有無に関わらず必ず実行
}
```

| 種類 | 説明 | 例 |
|---|---|---|
| 検査例外（checked） | catchを強制 | `IOException` |
| 非検査例外（unchecked） | catchは任意 | `NullPointerException`, `ArrayIndexOutOfBoundsException` |

複数 `catch` を書く場合は**具体的な例外を先に**書く。

---

## Q6. StringBuilder の append と insert

**問題:** 以下のコードの出力結果は？
```java
StringBuilder sb = new StringBuilder("Java");
sb.append(" Silver");
sb.insert(4, "!");
System.out.println(sb);
```

**選択肢:**
- A. `Java! Silver`
- B. `Java Silver!`
- C. `!Java Silver`
- D. `Java Silver`

**回答:** A（`Java! Silver`）

**ワンポイントアドバイス:**
`append(str)` は末尾に追加、`insert(index, str)` は指定インデックスの位置に挿入する。
`append` 後は `"Java Silver"`（11文字）、`insert(4, "!")` でインデックス4に `"!"` を挿入して `"Java! Silver"` になる。
`String` と異なり `StringBuilder` はイミュータブルではないため、元のオブジェクトを直接変更する。

| メソッド | 動作 |
|---|---|
| `append(str)` | 末尾に追加 |
| `insert(index, str)` | 指定位置に挿入 |

---
