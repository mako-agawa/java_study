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

---

## Q74. Comparator によるソート

**問題:**
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("banana");
        list.add("apple");
        list.add("cherry");

        Collections.sort(list, (a, b) -> a.compareTo(b));

        System.out.println(list);
    }
}
```

**選択肢:**
1. `[banana, apple, cherry]`
2. `[apple, banana, cherry]`
3. `[cherry, banana, apple]`
4. コンパイルエラー

**回答:** 2（`[apple, banana, cherry]`）

**ワンポイントアドバイス:**
`compareTo` の戻り値が負なら a が先、正なら b が先。`Comparator.naturalOrder()` で昇順、`Comparator.reverseOrder()` で降順。`comparingInt` で数値基準のソートも可能。

---

## Q75. マルチキャッチ

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        try {
            String s = null;
            System.out.println(s.length());
        } catch (NullPointerException | IllegalArgumentException e) {
            System.out.println("例外発生: " + e.getClass().getSimpleName());
        }
    }
}
```

**選択肢:**
1. `例外発生: NullPointerException`
2. `例外発生: IllegalArgumentException`
3. コンパイルエラー（`|` で複数の例外は書けない）
4. 実行時エラー（catchされない）

**回答:** 1（`例外発生: NullPointerException`）

**ワンポイントアドバイス:**
Java 7以降、`|` で複数の例外をまとめて捕捉できる。親子関係にある例外はマルチキャッチに書けない。`NullPointerException` は null のオブジェクトに操作を行うと発生する。`Optional.ofNullable()` で null を安全に扱える。

---

## Q76. モジュールシステム

**問題:** 以下の説明のうち正しいものはどれ？

```java
// module-info.java
module com.example.app {
    requires java.sql;
    exports com.example.app.api;
}
```

**選択肢:**
1. `requires` は使用するモジュールを宣言し、`exports` は公開するパッケージを宣言する
2. `requires` は公開するパッケージを宣言し、`exports` は使用するモジュールを宣言する
3. `module-info.java` はクラスの中に書く
4. モジュールシステムはJava 8で導入された

**回答:** 1

**ワンポイントアドバイス:**
モジュールシステムは Java 9 で導入。`module-info.java` はパッケージのルートに置く。`requires` で依存モジュールを宣言、`exports` で公開パッケージを宣言。`exports` していないパッケージは外部からアクセス不可。

---

## Q77. String の switch 文

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        String s = "Hello";
        switch (s) {
            case "Hello":
                System.out.println("Hi!");
                break;
            case "World":
                System.out.println("World!");
                break;
            default:
                System.out.println("Unknown");
        }
    }
}
```

**選択肢:**
1. `Hi!`
2. `World!`
3. `Unknown`
4. コンパイルエラー（String は switch に使えない）

**回答:** 1（`Hi!`）

**ワンポイントアドバイス:**
`String` の `switch` 文は Java 7 以降で使用可能。`switch` で使える型: `byte`, `short`, `int`, `char`, それらのラッパー型、`String`, `enum`。`long`, `float`, `double` は使えない。

---

## Q78. 拡張 for 文と配列の合計

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 1, 5};
        int sum = 0;
        for (int x : arr) {
            sum += x;
        }
        System.out.println(sum);
    }
}
```

**選択肢:**
1. `14`
2. `15`
3. `13`
4. コンパイルエラー

**回答:** 1（`14`）

**ワンポイントアドバイス:**
拡張 for 文はインデックスが不要なときに使う。配列・`Iterable` 実装クラス（`List` 等）に使用可能。プリミティブ型の要素を変更しても元の配列には反映されない。

---

## Q79. static フィールド

**問題:**
```java
public class Main {
    static int count = 0;

    public static void main(String[] args) {
        Main obj1 = new Main();
        Main obj2 = new Main();
        obj1.count++;
        System.out.println(obj2.count);
    }
}
```

**選択肢:**
1. `0`
2. `1`
3. コンパイルエラー
4. 実行時エラー

**回答:** 2（`1`）

**ワンポイントアドバイス:**
`static` フィールドはインスタンスではなくクラスに属し、全インスタンスで共有される。インスタンス経由でのアクセスは可能だが、`Main.count` と書くのが推奨。

---

## Q80. 文字列の == 比較

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        String a = "Java";
        String b = "Java";
        String c = new String("Java");

        System.out.println(a == b);
        System.out.println(a == c);
    }
}
```

**選択肢:**
1. `true` / `true`
2. `true` / `false`
3. `false` / `true`
4. `false` / `false`

**回答:** 2（`true` / `false`）

**ワンポイントアドバイス:**
文字列リテラルは文字列プールで共有されるため `==` が `true` になる。`new String()` は新たなオブジェクトを生成するため別参照。文字列の内容比較には `equals()` を使う。

---

## Q81. 剰余演算子

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        int x = 10;
        int y = 3;
        System.out.println(x % y);
    }
}
```

**選択肢:**
1. `3`
2. `1`
3. `0`
4. コンパイルエラー

**回答:** 2（`1`）

**ワンポイントアドバイス:**
`%` は割り算の余りを返す剰余演算子。`10 ÷ 3 = 3 余り 1`。偶数・奇数判定（`n % 2 == 0`）や配列の循環インデックス（`i % arr.length`）でよく使われる。

---

## Q82. List の remove

**問題:**
```java
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(1);
        System.out.println(list);
    }
}
```

**選択肢:**
1. `[2, 3]`
2. `[1, 3]`
3. `[1, 2]`
4. `[1, 2, 3]`

**回答:** 2（`[1, 3]`）

**ワンポイントアドバイス:**
`List` の `remove` には `remove(int index)`（インデックス削除）と `remove(Object o)`（値削除）の2種類がある。値で削除したい場合は `list.remove(Integer.valueOf(2))` のようにラッパー型を使う。

---

## Q83. while ループ

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        int i = 0;
        while (i < 3) {
            System.out.print(i + " ");
            i++;
        }
    }
}
```

**選択肢:**
1. `0 1 2 3 `
2. `1 2 3 `
3. `0 1 2 `
4. 無限ループ

**回答:** 3（`0 1 2 `）

**ワンポイントアドバイス:**
`print` は改行なし、`println` は改行あり。`i < 3` は `0, 1, 2` の3回ループ。`i <= 3` なら `0 1 2 3` になる。

---

## Q84. 前置・後置インクリメント

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        int a = 5;
        int b = ++a * 2;
        System.out.println(a + " " + b);
    }
}
```

**選択肢:**
1. `5 10`
2. `6 10`
3. `6 12`
4. `5 12`

**回答:** 3（`6 12`）

**ワンポイントアドバイス:**
`++a`（前置）はインクリメント後の値を使う。`a++`（後置）はインクリメント前の値を使う。`a++` なら `b = 5 * 2 = 10` になり、`a` は後から `6` になる。