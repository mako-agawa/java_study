# Java 学習ポイント集 vol.5（Q91〜）

---

## Q91. HashMap のキー上書き

**問題:**
```java
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("a", 3);
        System.out.println(map.get("a"));
    }
}
```

**選択肢:**
1. `1`
2. `3`
3. `[1, 3]`
4. コンパイルエラー

**回答:** 2（`3`）

**ワンポイントアドバイス:**
`Map` は同一キーの重複を許さない。既存キーに `put` すると値が上書きされる。上書きせずに既存値を保持したい場合は `putIfAbsent()` を使う。

---

## Q92. String の不変性

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        String s = "Java";
        s.concat(" Silver");
        System.out.println(s);
    }
}
```

**選択肢:**
1. `Java Silver`
2. `Java`
3. `Silver`
4. コンパイルエラー

**回答:** 2（`Java`）

**ワンポイントアドバイス:**
`String` は不変（immutable）なので `concat()` 等のメソッドは新しい文字列を返すだけで元のオブジェクトは変わらない。戻り値を `s = s.concat(" Silver")` のように受け取る必要がある。頻繁な結合には `StringBuilder` を使う。

---

## Q93. 三項演算子

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        int a = 7;
        String result = (a % 2 == 0) ? "偶数" : "奇数";
        System.out.println(result);
    }
}
```

**選択肢:**
1. `偶数`
2. `奇数`
3. `true`
4. コンパイルエラー

**回答:** 2（`奇数`）

**ワンポイントアドバイス:**
三項演算子の構文: `条件 ? trueの値 : falseの値`。`if-else` の簡潔な代替として使える。ネストは可読性が下がるので避けるのが無難。

---

## Q94. 配列の length

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(arr.length);
    }
}
```

**選択肢:**
1. `4`
2. `5`
3. `6`
4. コンパイルエラー（`length()` と書くべき）

**回答:** 2（`5`）

**ワンポイントアドバイス:**
配列は `.length`（フィールド）、`String` は `.length()`（メソッド）、`List` 等コレクションは `.size()`。この3つの使い分けに注意。
