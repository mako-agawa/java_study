# Java 学習ポイント集 vol.2（Q21〜）

---

## Q21. 三項演算子

**問題:** 以下のコードの出力結果は？
```java
public class Main {
    public static void main(String[] args) {
        int x = 10;
        int y = 20;
        int z = x > y ? x : y;
        System.out.println(z);
    }
}
```

**選択肢:**
- A. `10`
- B. `20`
- C. `true`
- D. `false`
- E. コンパイルエラー
- F. `0`

**回答:** B（`20`）※間違えた問題（D と回答）

**ワンポイントアドバイス:**
三項演算子は条件の真偽ではなく**選ばれた値**を返す。`x > y` が `false` なので `:` の後の `y(20)` が選ばれる。

```java
条件 ? 真の場合の値 : 偽の場合の値
```

| 条件 | 結果 |
|---|---|
| `x > y ? x : y` | `20`（大きい方） |
| `x < y ? x : y` | `10`（小さい方） |

**補足: よく使う Math メソッド**

| メソッド | 動作 |
|---|---|
| `Math.max(a, b)` | 大きい方を返す |
| `Math.min(a, b)` | 小さい方を返す |
| `Math.abs(a)` | 絶対値を返す |
| `Math.pow(a, b)` | a の b乗 |
| `Math.sqrt(a)` | 平方根 |

---

## Q22. String メソッド（length / substring / toUpperCase）

**問題:** 以下のコードの出力結果は？
```java
public class Main {
    public static void main(String[] args) {
        String s = "Hello, World!";
        System.out.println(s.length());
        System.out.println(s.substring(7));
        System.out.println(s.toUpperCase());
    }
}
```

**選択肢:**
- A. `13` / `World!` / `HELLO, WORLD!`
- B. `12` / `World!` / `HELLO, WORLD!`
- C. `13` / `world!` / `HELLO, WORLD!`
- D. `13` / `World!` / `hello, world!`

**回答:** A（`13` / `World!` / `HELLO, WORLD!`）

**ワンポイントアドバイス:**
`substring(n)` はインデックスn番目から末尾まで。`substring(n, m)` はn番目からm番目の**手前まで**（m番目は含まない）。

```java
String s = "Hello, World!";
s.length();                  // 13
s.substring(7);              // "World!"
s.substring(0, 5);           // "Hello"（4番目まで）
s.toUpperCase();             // "HELLO, WORLD!"
s.toLowerCase();             // "hello, world!"
s.indexOf("o");              // 4（最初のoの位置）
s.indexOf("z");              // -1（存在しない場合）
s.contains("World");         // true
s.replace("World", "Java");  // "Hello, Java!"
```

---

## Q23. 配列のデフォルト値

**問題:** 以下のコードの出力結果は？
```java
public class Main {
    public static void main(String[] args) {
        int[] arr = new int[5];
        System.out.println(arr[0]);
        System.out.println(arr[4]);
    }
}
```

**選択肢:**
- A. `null` / `null`
- B. `0` / `0`
- C. `0` / `null`
- D. コンパイルエラー
- E. `ArrayIndexOutOfBoundsException`
- F. `1` / `5`

**回答:** B（`0` / `0`）※間違えた問題（A と回答）

**ワンポイントアドバイス:**
`new int[5]` で生成した配列はデフォルト値 `0` で初期化される。`null` になるのは参照型の配列。

| 型 | デフォルト値 |
|---|---|
| `int` / `long` / `short` / `byte` | `0` |
| `double` / `float` | `0.0` |
| `boolean` | `false` |
| `char` | `'\u0000'`（空文字） |
| 参照型（`String`など） | `null` |

---

## Q24. 論理演算子（&&, ||, !）

**問題:** 以下のコードの出力結果は？
```java
public class Main {
    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;
        System.out.println(a && b);
        System.out.println(a || b);
        System.out.println(!a);
    }
}
```

**選択肢:**
- A. `true` / `true` / `false`
- B. `false` / `true` / `false`
- C. `true` / `false` / `false`
- D. `false` / `false` / `true`

**回答:** B（`false` / `true` / `false`）

**ワンポイントアドバイス:**

| 演算子 | 意味 | 結果がtrueになる条件 |
|---|---|---|
| `&&` | AND | 両方 `true` |
| `\|\|` | OR | どちらか `true` |
| `!` | NOT | 元が `false` |

---
