# Java 学習ポイント集 vol.4（Q61〜）

---

## Q61. StringBuilder

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(", ");
        sb.append("World");
        sb.append("!");

        System.out.println(sb.toString());
        System.out.println(sb.length());
    }
}
```

**選択肢:**
1. `Hello, World!` / `13`
2. `Hello` / `5`
3. `Hello, World!` / `5`
4. コンパイルエラー

**回答:** 1（`Hello, World!` / `13`）

**ワンポイントアドバイス:**
`String` はイミュータブルで連結のたびに新オブジェクトを生成する。`StringBuilder` はミュータブルで同じオブジェクトを変更するためループ内の大量連結に適している。

---

## Q62. 配列の基本

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        int[] arr = new int[3];
        arr[0] = 10;
        arr[1] = 20;

        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
        System.out.println(arr.length);
    }
}
```

**選択肢:**
1. `10` / `20` / `0` / `3`
2. `10` / `20` / `null` / `3`
3. `10` / `20` / `0` / `2`
4. 実行時エラー（arr[2] は未初期化）

**回答:** 1（`10` / `20` / `0` / `3`）

**ワンポイントアドバイス:**
配列はデフォルト値で初期化される（int は 0、参照型は null）。`arr.length` はフィールドなので `()` 不要（`String` の `length()` と違う点に注意）。

---

## Q63. 二次元配列

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        System.out.println(matrix[1][2]);
        System.out.println(matrix.length);
        System.out.println(matrix[0].length);
    }
}
```

**選択肢:**
1. `6` / `3` / `3`
2. `5` / `3` / `3`
3. `6` / `9` / `3`
4. コンパイルエラー

**回答:** 1（`6` / `3` / `3`）

**ワンポイントアドバイス:**
`matrix[行][列]` で要素にアクセス。`matrix.length` は行数、`matrix[i].length` は列数。インデックスは0始まり。

---

## Q64. ArrayList と LinkedList の違い

**問題:** 以下の説明のうち正しいものはどれ？

**選択肢:**
1. `ArrayList` は追加・削除が速く、`LinkedList` はランダムアクセスが速い
2. `ArrayList` はランダムアクセスが速く、`LinkedList` は先頭・末尾への追加・削除が速い
3. `ArrayList` と `LinkedList` は内部構造が同じなので性能差はない
4. `LinkedList` は `List` インターフェースを実装していないのでコンパイルエラー

**回答:** 2 ※間違えた問題（1 と回答）

**ワンポイントアドバイス:**
`ArrayList` は配列ベースでランダムアクセスが速い。`LinkedList` は連結リストベースで先頭・末尾の追加削除が速い。迷ったら `ArrayList` を使うのが基本。

| | `ArrayList` | `LinkedList` |
|---|---|---|
| ランダムアクセス | 速い | 遅い |
| 先頭・末尾の追加削除 | 遅い | 速い |
| 主な用途 | 一般的なリスト | キュー・スタック |

---

## Q65. HashMap の基本

**問題:**
```java
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("apple", 100);
        map.put("banana", 200);
        map.put("apple", 300);

        System.out.println(map.get("apple"));
        System.out.println(map.get("cherry"));
        System.out.println(map.size());
    }
}
```

**選択肢:**
1. `100` / `null` / `3`
2. `300` / `null` / `2`
3. `300` / `0` / `2`
4. コンパイルエラー

**回答:** 2（`300` / `null` / `2`）※間違えた問題（3 と回答）

**ワンポイントアドバイス:**
同じキーで `put()` すると値が上書きされる。`get()` でキーが存在しない場合は `null` が返る（`0` ではない）。デフォルト値を返したい場合は `getOrDefault(k, default)` を使う。

---

## Q66. 拡張for文

**問題:**
```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        for (String s : list) {
            System.out.println(s);
        }
    }
}
```

**選択肢:**
1. `A` → `B` → `C`
2. `C` → `B` → `A`
3. コンパイルエラー
4. 実行時エラー

**回答:** 1（`A` → `B` → `C`）

**ワンポイントアドバイス:**
拡張for文はインデックスが不要な場合に使う。配列・List・Mapのキーセットなど `Iterable` を実装したものに使える。ループ中に要素を削除すると `ConcurrentModificationException` が発生するので注意。

---

## Q67. ラッパークラスとオートボクシング

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        int a = 42;
        Integer b = a;
        int c = b;

        System.out.println(b);
        System.out.println(b.getClass().getSimpleName());
        System.out.println(Integer.MAX_VALUE);
    }
}
```

**選択肢:**
1. `42` / `int` / `2147483647`
2. `42` / `Integer` / `2147483647`
3. コンパイルエラー（int を Integer に代入できない）
4. `42` / `Integer` / `2147483648`

**回答:** 2（`42` / `Integer` / `2147483647`）

**ワンポイントアドバイス:**
`int` → `Integer` の自動変換をオートボクシング、`Integer` → `int` をアンボクシングと言う。`null` の `Integer` をアンボクシングすると `NullPointerException` が発生する。`long` リテラルは `L`、`float` リテラルは `f` をつける。

---

## Q68. 型変換とキャスト

**問題:** 以下のうちコンパイルエラーになるものはどれ？

```java
// (1)
int a = 100;
long b = a;

// (2)
long c = 100L;
int d = c;

// (3)
double e = 3.14;
int f = (int) e;

// (4)
int g = 3.14;
```

**選択肢:**
1. (2) のみ
2. (2)(4) のみ
3. (4) のみ
4. (1)(2)(3)(4) すべて

**回答:** 2（(2)(4) のみ）

**ワンポイントアドバイス:**
`byte → short → int → long → float → double` の順で拡大変換は自動、縮小変換はキャスト必要。キャストで小数点以下は切り捨て（四捨五入ではない）。`double` リテラル（3.14）を `int` に直接代入はエラー。

---

## Q69. switch式

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        int day = 3;

        String result = switch (day) {
            case 1, 7 -> "休日";
            case 2, 3, 4, 5, 6 -> "平日";
            default -> "不明";
        };

        System.out.println(result);
    }
}
```

**選択肢:**
1. `休日`
2. `平日`
3. `不明`
4. コンパイルエラー

**回答:** 2（`平日`）

**ワンポイントアドバイス:**
switch 式（Java 14以降）は `->` を使い `break` 不要。カンマで複数ケースをまとめられる。値を変数に直接代入できる。フォールスルーが起きない点が従来の switch 文と大きく異なる。

---

## Q70. 日付・時刻API

**問題:**
```java
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2024, 3, 15);

        System.out.println(date);
        System.out.println(date.getYear());
        System.out.println(date.getMonthValue());
        System.out.println(date.plusDays(10));
    }
}
```

**選択肢:**
1. `2024-03-15` / `2024` / `3` / `2024-03-25`
2. `2024/03/15` / `2024` / `3` / `2024/03/25`
3. `2024-03-15` / `2024` / `3` / `2024-03-15`
4. コンパイルエラー

**回答:** 1（`2024-03-15` / `2024` / `3` / `2024-03-25`）

**ワンポイントアドバイス:**
`LocalDate` はイミュータブル。`plusDays()` などは新しいオブジェクトを返す。`getMonthValue()` は1〜12を返す。`LocalDateTime.now()` で一度取得して `toLocalDate()` / `toLocalTime()` で分解できる。

---

## Q71. var キーワード

**問題:** 以下のうちコンパイルエラーになるものはどれ？

```java
// (1)
var x = 10;

// (2)
var list = new ArrayList<String>();
list.add("hello");

// (3)
var n = null;

// (4)
var message = "Hello";
message = "World";
```

**選択肢:**
1. (3) のみ
2. (3)(4) のみ
3. (1)(3) のみ
4. エラーなし

**回答:** 1（(3) のみ）※間違えた問題（4 と回答）

**ワンポイントアドバイス:**
`var` は右辺からコンパイラが型を推論する。`null` は型推論できないのでエラー。初期化なし・メソッド引数・フィールドには使えない。一度推論された型は変わらない（動的型付けではない）。

---

## Q72. インターフェースの default メソッド

**問題:**
```java
interface Greeter {
    void greet(String name);

    default void greetAll(String[] names) {
        for (String name : names) {
            greet(name);
        }
    }
}

class HelloGreeter implements Greeter {
    @Override
    public void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }
}

public class Main {
    public static void main(String[] args) {
        Greeter g = new HelloGreeter();
        g.greetAll(new String[]{"Alice", "Bob"});
    }
}
```

**選択肢:**
1. コンパイルエラー（インターフェースにメソッド実装は書けない）
2. `Hello, Alice!` → `Hello, Bob!`
3. `Hello, Alice!` のみ
4. 実行時エラー

**回答:** 2（`Hello, Alice!` → `Hello, Bob!`）

**ワンポイントアドバイス:**
`default` メソッドはJava 8以降でインターフェースにデフォルト実装を持たせる機能。実装クラスでオーバーライド不要だが、必要なら上書きできる。既存の実装クラスを変更せずにインターフェースへメソッド追加が可能。

---

## Q73. 匿名クラス

**問題:**
```java
interface Animal {
    String sound();
}

public class Main {
    public static void main(String[] args) {
        Animal cat = new Animal() {
            @Override
            public String sound() {
                return "Meow";
            }
        };

        System.out.println(cat.sound());
    }
}
```

**選択肢:**
1. コンパイルエラー（インターフェースは `new` できない）
2. `Meow`
3. `Animal`
4. 実行時エラー

**回答:** 2（`Meow`）

**ワンポイントアドバイス:**
匿名クラスはインターフェースや抽象クラスをその場で実装してインスタンス化する。抽象メソッドが1つの場合はラムダ式の方が簡潔。複数メソッドを実装したい場合に匿名クラスを使う。