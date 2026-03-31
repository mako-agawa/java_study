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
