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

---

## Q95. ポリモーフィズム

**問題:**
```java
public class Animal {
    public String sound() {
        return "...";
    }
}

public class Dog extends Animal {
    @Override
    public String sound() {
        return "ワン";
    }
}

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog();
        System.out.println(a.sound());
    }
}
```

**選択肢:**
1. `...`
2. `ワン`
3. コンパイルエラー
4. 実行時エラー

**回答:** 2（`ワン`）

**ワンポイントアドバイス:**
Javaのメソッド呼び出しは実行時の実際の型で決まる（動的ディスパッチ）。変数の型が `Animal` でも実際のオブジェクトが `Dog` なら `Dog#sound()` が呼ばれる。これがポリモーフィズムの基本。

---

## Q96. 整数除算と double

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        int x = 5;
        int y = 2;
        double result = x / y;
        System.out.println(result);
    }
}
```

**選択肢:**
1. `2.5`
2. `2.0`
3. `3.0`
4. コンパイルエラー

**回答:** 2（`2.0`）

**ワンポイントアドバイス:**
除算の結果の型はオペランドの型で決まる。`int / int` は整数除算になり小数が切り捨てられる。`2.5` を得たいなら `(double) x / y` または `5.0 / 2` のようにどちらかを `double` にする。

---

## Q97. StringBuilder

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World");
        sb.reverse();
        System.out.println(sb);
    }
}
```

**選択肢:**
1. `Hello World`
2. `dlroW olleH`
3. `World Hello`
4. コンパイルエラー

**回答:** 2（`dlroW olleH`）

**ワンポイントアドバイス:**
`StringBuilder` の主なメソッド: `append(s)`（末尾追加）、`insert(i, s)`（位置 i に挿入）、`delete(s, e)`（s〜e 削除）、`reverse()`（逆順）、`toString()`（String 変換）。

---

## Q98. 三項演算子で最大値

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        int a = 3;
        int b = 4;
        System.out.println(a > b ? a : b);
    }
}
```

**選択肢:**
1. `3`
2. `4`
3. `true`
4. コンパイルエラー

**回答:** 2（`4`）

**ワンポイントアドバイス:**
`a > b ? a : b` は2つの値の大きい方を返すパターン。`Math.max(a, b)` でも同じ結果が得られる。

---

## Q99. Arrays.sort / Arrays.toString

**問題:**
```java
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 4, 2};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
```

**選択肢:**
1. `[5, 3, 1, 4, 2]`
2. `[1, 2, 3, 4, 5]`
3. `[5, 4, 3, 2, 1]`
4. コンパイルエラー

**回答:** 2（`[1, 2, 3, 4, 5]`）

**ワンポイントアドバイス:**
`Arrays.sort()` は昇順ソート。降順にするには `int[]` をそのまま使えないため、`Integer[]` に変換して `Arrays.sort(arr, Comparator.reverseOrder())` を使う。`int[]` はプリミティブ型なので `Comparator` が直接使えない点に注意。

---

## Q101. 関数型インターフェースとラムダ式

**問題:**
```java
public interface Greet {
    void hello();
}

public class Main {
    public static void main(String[] args) {
        Greet g = () -> System.out.println("こんにちは");
        g.hello();
    }
}
```

**選択肢:**
1. コンパイルエラー（インターフェースはインスタンス化できない）
2. `こんにちは`
3. 実行時エラー
4. 何も出力されない

**回答:** 2（`こんにちは`）

**ワンポイントアドバイス:**
抽象メソッドが1つだけのインターフェースを関数型インターフェースと呼ぶ。ラムダ式で実装できる。`@FunctionalInterface` アノテーションを付けると明示できる。`Runnable`, `Comparator`, `Predicate` 等も関数型インターフェース。→ 詳細は `interface.md` 参照。

---

## Q100. ArrayIndexOutOfBoundsException

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        try {
            int[] arr = new int[3];
            arr[5] = 10;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("範囲外: " + e.getMessage());
        }
    }
}
```

**選択肢:**
1. `範囲外: null`
2. `範囲外: 3`
3. `範囲外: 5`
4. 実行時エラー（catchされない）

**回答:** 3（`範囲外: Index 5 out of bounds for length 3`）

**ワンポイントアドバイス:**
`ArrayIndexOutOfBoundsException` は配列の範囲外アクセス時に発生。Java 11 以降は `e.getMessage()` で `"Index 5 out of bounds for length 3"` のように詳細なメッセージが返る。配列の有効インデックスは `0` 〜 `length - 1`。
