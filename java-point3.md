# Java 学習ポイント集 vol.3（Q41〜）

---

## Q41. ラムダ式の基本

**問題:** 以下のコードの出力結果は？
```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Banana");
        list.add("Apple");
        list.add("Cherry");

        list.forEach(s -> System.out.println(s));
    }
}
```

**選択肢:**
- A. `Apple` → `Banana` → `Cherry`（アルファベット順）
- B. `Banana` → `Apple` → `Cherry`（追加順）
- C. コンパイルエラー
- D. 実行時エラー

**回答:** B（`Banana` → `Apple` → `Cherry`）※間違えた問題（A と回答）

**ワンポイントアドバイス:**
`forEach` は要素を追加した順番に処理する。ソートは行わない。

```
add("Banana") → [Banana]
add("Apple")  → [Banana, Apple]
add("Cherry") → [Banana, Apple, Cherry]

forEach → Banana → Apple → Cherry
```

ソートしたい場合は別途必要。

```java
Collections.sort(list);               // [Apple, Banana, Cherry]
list.sort(Comparator.naturalOrder()); // 同上
```

**補足: ラムダ式とは**

メソッドを短く書く記法。処理のかたまりをその場で定義して渡せる。

```java
// 従来の書き方（匿名クラス）
list.forEach(new Consumer<String>() {
    @Override
    public void accept(String s) {
        System.out.println(s);
    }
});

// ラムダ式で同じことを1行で
list.forEach(s -> System.out.println(s));
```

| 構文 | 例 |
|---|---|
| `(引数) -> { 処理 }` | `(s) -> { System.out.println(s); }` |
| 1行なら `{}` 省略可 | `s -> System.out.println(s)` |
| 1引数なら `()` 省略可 | `s -> System.out.println(s)` |

---

## Q42. メソッド参照

**問題:**
```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Banana");
        list.add("Apple");
        list.add("Cherry");

        list.forEach(System.out::println);
    }
}
```

**選択肢:**
1. コンパイルエラー（`System.out::println` は不正な構文）
2. `Banana` → `Apple` → `Cherry`
3. `Apple` → `Banana` → `Cherry`（アルファベット順）
4. 実行時エラー

**回答:** 2（`Banana` → `Apple` → `Cherry`）

**ワンポイントアドバイス:**
`System.out::println` は `s -> System.out.println(s)` と同じ意味のメソッド参照。`s` には `forEach` が渡すリストの各要素（String）が入る。

| 種類 | 構文 | ラムダ式との対応 |
|---|---|---|
| インスタンスメソッド（特定オブジェクト） | `System.out::println` | `s -> System.out.println(s)` |
| 静的メソッド | `Integer::parseInt` | `s -> Integer.parseInt(s)` |
| インスタンスメソッド（任意オブジェクト） | `String::toUpperCase` | `s -> s.toUpperCase()` |
| コンストラクタ | `ArrayList::new` | `() -> new ArrayList<>()` |

---

## Q43. Stream API の基本

**問題:**
```java
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);

        List<Integer> result = list.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

**選択肢:**
1. `[1, 2, 3, 4, 5]`
2. `[1, 3, 5]`
3. `[2, 4]`
4. コンパイルエラー

**回答:** 3（`[2, 4]`）

**ワンポイントアドバイス:**
`filter(n -> n % 2 == 0)` は偶数のみ通過させる。Stream は `collect()` などの終端操作が呼ばれて初めて処理が実行される（遅延評価）。

| メソッド種別 | 例 | 戻り値 |
|---|---|---|
| 中間操作 | `filter()` `map()` `sorted()` | Stream（繋げられる） |
| 終端操作 | `collect()` `forEach()` `count()` | Stream以外 |

---

## Q44. Stream の map

**問題:**
```java
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> list = List.of("apple", "banana", "cherry");

        List<Integer> result = list.stream()
                .map(s -> s.length())
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

**選択肢:**
1. `[apple, banana, cherry]`
2. `[5, 6, 6]`
3. `[5, 5, 5]`
4. コンパイルエラー

**回答:** 2（`[5, 6, 6]`）※間違えた問題（1 と回答）

**ワンポイントアドバイス:**
`map` は元の値ではなく変換後の値を流す。`s.length()` で文字数に変換されるため型も `List<String>` → `List<Integer>` に変わる。

---

## Q45. Stream の sorted / distinct

**問題:**
```java
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = List.of(3, 1, 4, 1, 5, 9, 2, 6);

        List<Integer> result = list.stream()
                .sorted()
                .distinct()
                .collect(Collectors.toList());

        System.out.println(result);
    }
}
```

**選択肢:**
1. `[3, 1, 4, 1, 5, 9, 2, 6]`
2. `[1, 2, 3, 4, 5, 6, 9]`
3. `[1, 1, 2, 3, 4, 5, 6, 9]`
4. `[9, 6, 5, 4, 3, 2, 1]`

**回答:** 2（`[1, 2, 3, 4, 5, 6, 9]`）

**ワンポイントアドバイス:**
中間操作は記述順に実行される。`sorted()` で昇順ソート後、`distinct()` で重複除去。順序によって結果が変わるケースもあるため注意。

---

## Q46. Optional の基本

**問題:**
```java
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = List.of(3, 1, 4, 1, 5, 9, 2, 6);

        Optional<Integer> result = list.stream()
                .filter(n -> n > 7)
                .findFirst();

        System.out.println(result.get());
    }
}
```

**選択肢:**
1. `3`
2. `9`
3. `6`
4. コンパイルエラー

**回答:** 2（`9`）

**ワンポイントアドバイス:**
`findFirst()` は `Optional` を返す。`get()` で値を取り出せるが、値がない場合は `NoSuchElementException` が発生する。安全に扱うには `orElse()` や `isPresent()` を使う。

---

## Q47. 関数型インターフェース

**問題:**
```java
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Predicate<String> isLong = s -> s.length() > 5;

        System.out.println(isLong.test("Hello"));
        System.out.println(isLong.test("Hello, World"));
    }
}
```

**選択肢:**
1. `true` / `true`
2. `false` / `false`
3. `false` / `true`
4. コンパイルエラー

**回答:** 3（`false` / `true`）

**ワンポイントアドバイス:**
Stream API の各メソッドはこれらの関数型インターフェースを引数に取っている。4つの型と抽象メソッドを押さえておく。

| | 引数 | 戻り値 | 抽象メソッド |
|---|---|---|---|
| `Predicate<T>` | あり | boolean | `test()` |
| `Function<T,R>` | あり | あり | `apply()` |
| `Consumer<T>` | あり | なし | `accept()` |
| `Supplier<T>` | なし | あり | `get()` |

---

## Q48. ジェネリクスの基本

**問題:**
```java
public class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}

public class Main {
    public static void main(String[] args) {
        Box<String> strBox = new Box<>("Hello");
        Box<Integer> intBox = new Box<>(123);

        System.out.println(strBox.getValue());
        System.out.println(intBox.getValue());
    }
}
```

**選択肢:**
1. `Hello` / `123`
2. `T` / `T`
3. コンパイルエラー
4. 実行時エラー

**回答:** 1（`Hello` / `123`）

**ワンポイントアドバイス:**
`<T>` は型パラメータ。インスタンス生成時に具体的な型が決まる。`List<String>` や `Optional<Integer>` もジェネリクスの仕組みで動いている。

---

## Q49. 例外処理の finally

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("try");
            int result = 10 / 0;
            System.out.println("after division");
        } catch (ArithmeticException e) {
            System.out.println("catch");
        } finally {
            System.out.println("finally");
        }
    }
}
```

**選択肢:**
1. `try` → `after division` → `finally`
2. `try` → `catch` → `finally`
3. `try` → `catch`
4. `try` → `finally`

**回答:** 2（`try` → `catch` → `finally`）

**ワンポイントアドバイス:**
`finally` は例外が発生してもしなくても必ず実行される。リソース解放に使われるが、Java 7 以降は `try-with-resources` が推奨。

| ケース | 実行順 |
|---|---|
| 例外なし | try → finally |
| 例外あり・catchあり | try → catch → finally |
| 例外あり・catchなし | try → finally → 例外が上に伝播 |

---

## Q50. try-with-resources

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        try (var br = new java.io.BufferedReader(
                new java.io.FileReader("test.txt"))) {
            System.out.println(br.readLine());
        } catch (java.io.IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
```

**選択肢:**
1. `finally` ブロックがないためリソースがクローズされない
2. `try` ブロックを抜けると自動的に `br.close()` が呼ばれる
3. `var` は使えないのでコンパイルエラー
4. `BufferedReader` は `AutoCloseable` を実装していないためコンパイルエラー

**回答:** 2

**ワンポイントアドバイス:**
`try-with-resources` は `AutoCloseable` を実装したリソースを自動クローズする。Java 7 以降の推奨スタイル。`finally` での手動クローズが不要になる。
