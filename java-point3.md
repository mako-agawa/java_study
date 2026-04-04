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

---

## Q51. 継承とオーバーライド（ポリモーフィズム）

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
        return "Woof";
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
2. `Woof`
3. コンパイルエラー
4. 実行時エラー

**回答:** 2（`Woof`）

**ワンポイントアドバイス:**
変数の型（`Animal`）ではなく `new` で生成した実際のオブジェクトの型（`Dog`）のメソッドが呼ばれる（動的ディスパッチ）。これがポリモーフィズムの核心。

---

## Q52. 抽象クラス

**問題:**
```java
abstract class Shape {
    abstract double area();

    public void printArea() {
        System.out.println("面積: " + area());
    }
}

class Circle extends Shape {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return radius * radius * 3.14;
    }
}

public class Main {
    public static void main(String[] args) {
        Shape s = new Circle(5);
        s.printArea();
    }
}
```

**選択肢:**
1. `面積: 0.0`
2. `面積: 78.5`
3. コンパイルエラー
4. `Shape` は抽象クラスなので実行時エラー

**回答:** 2（`面積: 78.5`）※間違えた問題（4 と回答）

**ワンポイントアドバイス:**
抽象クラスは `new Shape()` で直接生成不可だが、`Shape s = new Circle(5)` のようにサブクラスのインスタンスを親クラス型で参照するのは問題ない。「直接インスタンス化」と「親クラス型での参照」は別物。

---

## Q53. インターフェースの複数実装

**問題:**
```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

class Duck implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("飛ぶ");
    }

    @Override
    public void swim() {
        System.out.println("泳ぐ");
    }
}

public class Main {
    public static void main(String[] args) {
        Duck d = new Duck();
        d.fly();
        d.swim();
    }
}
```

**選択肢:**
1. コンパイルエラー（複数のインターフェースは実装できない）
2. `飛ぶ` → `泳ぐ`
3. `泳ぐ` → `飛ぶ`
4. 実行時エラー

**回答:** 2（`飛ぶ` → `泳ぐ`）

**ワンポイントアドバイス:**
クラスの継承（`extends`）は1つだけだが、インターフェースの実装（`implements`）は複数可。`implements B, C, D` のようにカンマ区切りで列挙する。

---

## Q54. instanceof 演算子

**問題:**
```java
class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog();

        System.out.println(a instanceof Animal);
        System.out.println(a instanceof Dog);
        System.out.println(a instanceof Cat);
    }
}
```

**選択肢:**
1. `true` / `true` / `true`
2. `true` / `true` / `false`
3. `false` / `true` / `false`
4. コンパイルエラー

**回答:** 2（`true` / `true` / `false`）

**ワンポイントアドバイス:**
`instanceof` は変数の型ではなく `new` で生成した実際のオブジェクトの型を見る。ダウンキャスト前の安全確認に使う。

```java
if (a instanceof Dog) {
    Dog d = (Dog) a;  // 安全にキャスト
}
```

---

## Q55. super キーワード

**問題:**
```java
class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }

    String greet() {
        return "私は " + name + " です";
    }
}

class Dog extends Animal {
    String breed;

    Dog(String name, String breed) {
        super(name);
        this.breed = breed;
    }

    @Override
    String greet() {
        return super.greet() + "（" + breed + "）";
    }
}

public class Main {
    public static void main(String[] args) {
        Dog d = new Dog("ポチ", "柴犬");
        System.out.println(d.greet());
    }
}
```

**選択肢:**
1. `私は ポチ です`
2. `私は ポチ です（柴犬）`
3. `（柴犬）`
4. コンパイルエラー

**回答:** 2（`私は ポチ です（柴犬）`）

**ワンポイントアドバイス:**
`super(引数)` で親のコンストラクタ、`super.メソッド()` で親のメソッドを呼べる。`super()` はコンストラクタの1行目に書く必要がある。

---

## Q56. アクセス修飾子

**問題:**
```java
package com.example;

class Animal {
    private String name = "動物";
    String type = "哺乳類";
    protected int age = 3;
    public String color = "茶色";
}

class Main {
    public static void main(String[] args) {
        Animal a = new Animal();
        System.out.println(a.name);   // (1)
        System.out.println(a.type);   // (2)
        System.out.println(a.age);    // (3)
        System.out.println(a.color);  // (4)
    }
}
```

**選択肢:**
1. すべてコンパイルエラーなし
2. (1) のみコンパイルエラー
3. (1)(2) がコンパイルエラー
4. (1)(2)(3) がコンパイルエラー

**回答:** 2（(1) のみコンパイルエラー）

**ワンポイントアドバイス:**
`protected` はサブクラスと同パッケージからアクセス可。別パッケージの無関係クラスからはアクセス不可。

| 修飾子 | 同クラス | 同パッケージ | サブクラス | 別パッケージ |
|---|---|---|---|---|
| `private` | ○ | × | × | × |
| なし | ○ | ○ | × | × |
| `protected` | ○ | ○ | ○ | × |
| `public` | ○ | ○ | ○ | ○ |

---

## Q57. final キーワード

**問題:** 以下のうちコンパイルエラーになるものはどれ？

```java
// (1)
final int x = 10;
x = 20;

// (2)
final class Animal {}
class Dog extends Animal {}

// (3)
class Animal {
    final String sound() { return "..."; }
}
class Dog extends Animal {
    @Override
    String sound() { return "Woof"; }
}

// (4)
final List<String> list = new ArrayList<>();
list.add("hello");
```

**選択肢:**
1. (1) のみ
2. (1)(2) のみ
3. (1)(2)(3) のみ
4. (1)(2)(3)(4) すべて

**回答:** 3（(1)(2)(3) のみ）※間違えた問題（2 と回答）

**ワンポイントアドバイス:**
`final` 変数は参照先を固定するだけで、オブジェクトの中身は変更可能。`list.add()` は参照先を変えるのではなく中身を変えるだけなのでエラーにならない。

| 対象 | 効果 |
|---|---|
| 変数 | 再代入不可 |
| クラス | 継承不可 |
| メソッド | オーバーライド不可 |

---

## Q58. 列挙型（enum）

**問題:**
```java
enum Day {
    MON, TUE, WED, THU, FRI, SAT, SUN
}

public class Main {
    public static void main(String[] args) {
        Day d = Day.WED;

        System.out.println(d);
        System.out.println(d.ordinal());
        System.out.println(d.name());
    }
}
```

**選択肢:**
1. `WED` / `2` / `WED`
2. `WED` / `3` / `WED`
3. `2` / `2` / `WED`
4. コンパイルエラー

**回答:** 1（`WED` / `2` / `WED`）※間違えた問題（3 と回答）

**ワンポイントアドバイス:**
`ordinal()` は0始まりのインデックス。`println(d)` は内部で `d.toString()` を呼ぶため定数名が出力される。`name()` と `toString()` は基本同じ値を返す。

---

## Q59. 文字列の比較

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        String a = "hello";
        String b = "hello";
        String c = new String("hello");

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a.equals(c));
    }
}
```

**選択肢:**
1. `true` / `true` / `true`
2. `true` / `false` / `true`
3. `false` / `false` / `true`
4. `true` / `false` / `false`

**回答:** 2（`true` / `false` / `true`）

**ワンポイントアドバイス:**
`==` は参照（アドレス）を比較、`equals()` は中身を比較。文字列リテラルは文字列プールで共有されるが、`new String()` は別オブジェクトを生成する。文字列比較は必ず `equals()` を使う。

---

## Q60. String のメソッド

**問題:**
```java
public class Main {
    public static void main(String[] args) {
        String s = "Hello, World!";

        System.out.println(s.length());
        System.out.println(s.substring(7));
        System.out.println(s.toUpperCase());
        System.out.println(s.contains("World"));
    }
}
```

**選択肢:**
1. `13` / `World!` / `HELLO, WORLD!` / `true`
2. `14` / `World!` / `HELLO, WORLD!` / `true`
3. `13` / `World` / `HELLO, WORLD!` / `true`
4. `13` / `World!` / `Hello, World!` / `true`

**回答:** 1（`13` / `World!` / `HELLO, WORLD!` / `true`）

**ワンポイントアドバイス:**
`String` はイミュータブル。`toUpperCase()` などは元の文字列を変えず新しい文字列を返す。メソッドチェーンで繋げて書ける。

```java
"  090-1234-5678  ".trim().replace("-", ""); // "09012345678"
```
