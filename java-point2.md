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

## Q25. クラスとインスタンス

**問題:** 以下のコードの出力結果は？
```java
public class Dog {
    String name;
    int age;

    void bark() {
        System.out.println(name + " says: Woof!");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog1 = new Dog();
        dog1.name = "Pochi";
        dog1.age = 3;

        Dog dog2 = new Dog();
        dog2.name = "Hana";
        dog2.age = 5;

        dog1.bark();
        dog2.bark();
    }
}
```

**選択肢:**
- A. `Pochi says: Woof!` のみ
- B. `Hana says: Woof!` のみ
- C. `Pochi says: Woof!` → `Hana says: Woof!`
- D. `dog1 says: Woof!` → `dog2 says: Woof!`
- E. コンパイルエラー

**回答:** C

**ワンポイントアドバイス:**
`クラス` は設計図、`インスタンス` はその設計図から作られた実体。`new` するたびに独立したオブジェクトが生まれ、フィールドの値は互いに影響しない。

| インスタンス | name | age | bark()の出力 |
|---|---|---|---|
| `dog1` | `"Pochi"` | `3` | `Pochi says: Woof!` |
| `dog2` | `"Hana"` | `5` | `Hana says: Woof!` |

---

## Q26. コンストラクタ

**問題:** 以下のコードの出力結果は？
```java
public class Car {
    String color;
    int speed;

    Car(String color, int speed) {
        this.color = color;
        this.speed = speed;
    }

    void show() {
        System.out.println(color + " car, speed: " + speed);
    }
}

public class Main {
    public static void main(String[] args) {
        Car car1 = new Car("Red", 100);
        Car car2 = new Car("Blue", 200);

        car1.show();
        car2.show();
    }
}
```

**選択肢:**
- A. `Red car, speed: 100` → `Blue car, speed: 200`
- B. `Blue car, speed: 200` → `Red car, speed: 100`
- C. `color car, speed: speed` → `color car, speed: speed`
- D. コンパイルエラー
- E. `null car, speed: 0` → `null car, speed: 0`

**回答:** A

**ワンポイントアドバイス:**
`this.color = color` の `this.` はフィールドと引数の名前が同じときに区別するためのキーワード。`this` を省略するとコンパイルエラーにはならないが、引数が自分自身に代入されるだけで意味がなくなる。

| 呼び出し | this.color | this.speed | show()の出力 |
|---|---|---|---|
| `new Car("Red", 100)` | `"Red"` | `100` | `Red car, speed: 100` |
| `new Car("Blue", 200)` | `"Blue"` | `200` | `Blue car, speed: 200` |

---

## Q27. カプセル化（private + getter/setter）

**問題:** 以下のコードの出力結果は？
```java
public class Person {
    private String name;
    private int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }
}

public class Main {
    public static void main(String[] args) {
        Person p = new Person("Taro", 25);
        System.out.println(p.getName());
        System.out.println(p.getAge());
    }
}
```

**選択肢:**
- A. `Taro` → `25`
- B. `name` → `age`
- C. `null` → `0`
- D. コンパイルエラー
- E. `p.name` → `p.age`

**回答:** A

**ワンポイントアドバイス:**
カプセル化の目的は「フィールドを外部から直接変更させない」こと。`private` で隠して `getter/setter` で制御することで、不正な値の代入を防げる。命名規則は `getXxx()` / `setXxx()` が慣例。

| アクセス方法 | 結果 |
|---|---|
| `p.name` （直接） | コンパイルエラー（private） |
| `p.getName()` （getter） | `"Taro"` |
| `p.getAge()` （getter） | `25` |

---

## Q28. 継承（extends）

**問題:** 以下のコードの出力結果は？
```java
public class Animal {
    String name;

    Animal(String name) {
        this.name = name;
    }

    void speak() {
        System.out.println(name + " makes a sound.");
    }
}

public class Cat extends Animal {
    Cat(String name) {
        super(name);
    }
}

public class Main {
    public static void main(String[] args) {
        Cat cat = new Cat("Tama");
        cat.speak();
    }
}
```

**選択肢:**
- A. `Tama makes a sound.`
- B. `Cat makes a sound.`
- C. `null makes a sound.`
- D. コンパイルエラー
- E. 何も出力されない

**回答:** A（最初の回答はC→解説後に理解）

**ワンポイントアドバイス:**
`super(name)` で親の `Animal(String name)` コンストラクタが呼ばれ `this.name = "Tama"` がセットされる。`this` と `super` の違いは下記の通り。

| キーワード | 指すもの | 主な用途 |
|---|---|---|
| `this` | 自分自身 | フィールドと引数の区別 |
| `super` | 親クラス | 親のコンストラクタ・メソッドを呼ぶ |

| 使い方 | 構文 | タイミング |
|---|---|---|
| 親のコンストラクタ | `super(引数)` | コンストラクタの1行目のみ |
| 親のメソッド | `super.メソッド名()` | どこでも呼べる |

---

## Q29. メソッドオーバーライド

**問題:** 以下のコードの出力結果は？
```java
public class Animal {
    void speak() {
        System.out.println("...");
    }
}

public class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Woof!");
    }
}

public class Cat extends Animal {
    @Override
    void speak() {
        System.out.println("Meow!");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal a = new Animal();
        Animal d = new Dog();
        Animal c = new Cat();

        a.speak();
        d.speak();
        c.speak();
    }
}
```

**選択肢:**
- A. `...` → `...` → `...`
- B. `...` → `Woof!` → `Meow!`
- C. `Woof!` → `Meow!` → `...`
- D. コンパイルエラー
- E. `...` → `...` → `Meow!`

**回答:** B

**ワンポイントアドバイス:**
変数の型が `Animal` でも `new` で作ったインスタンスの型に応じたメソッドが呼ばれる（ポリモーフィズム）。`@Override` は付けなくても動くが、タイポで上書きできていない場合にコンパイルエラーで気づけるので必ず付ける習慣をつけよう。

---

## Q30. 抽象クラスとインターフェース

**問題:** 以下のコードの出力結果は？
```java
interface Flyable {
    void fly();
}

abstract class Bird {
    String name;

    Bird(String name) {
        this.name = name;
    }

    abstract void sing();

    void breathe() {
        System.out.println(name + " breathes.");
    }
}

class Sparrow extends Bird implements Flyable {
    Sparrow(String name) {
        super(name);
    }

    @Override
    public void fly() {
        System.out.println(name + " flies.");
    }

    @Override
    public void sing() {
        System.out.println(name + " sings.");
    }
}

public class Main {
    public static void main(String[] args) {
        Sparrow s = new Sparrow("Jack");
        s.breathe();
        s.fly();
        s.sing();
    }
}
```

**選択肢:**
- A. `Jack breathes.` → `Jack flies.` → `Jack sings.`
- B. `Jack sings.` → `Jack flies.` → `Jack breathes.`
- C. コンパイルエラー（abstractクラスはインスタンス化できない）
- D. コンパイルエラー（interfaceとabstractクラスは同時に使えない）
- E. `Jack breathes.` のみ

**回答:** A（最初の回答はC→解説後に理解）

**ワンポイントアドバイス:**
`abstract クラス` と `interface` の使い分け：

| | abstract クラス | interface |
|---|---|---|
| イメージ | 「鳥」という不完全な親 | 「飛べる」という能力バッジ |
| 目的 | 共通の基盤を提供 | 能力・契約を定義 |
| 継承/実装 | `extends`（1つのみ） | `implements`（複数OK） |
| フィールド | ✅ 持てる | ❌ 基本持てない |

---

## Q31. ポリモーフィズム（応用）

**問題:** 以下のコードの出力結果は？
```java
abstract class Shape {
    abstract double area();

    void printArea() {
        System.out.println("Area: " + area());
    }
}

class Circle extends Shape {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return 3.14 * radius * radius;
    }
}

class Rectangle extends Shape {
    double width, height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return width * height;
    }
}

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = { new Circle(5), new Rectangle(4, 3) };

        for (Shape s : shapes) {
            s.printArea();
        }
    }
}
```

**選択肢:**
- A. `Area: 78.5` → `Area: 12.0`
- B. `Area: 15.7` → `Area: 12.0`
- C. `Area: 78.5` → `Area: 7.0`
- D. コンパイルエラー
- E. `Area: 0.0` → `Area: 0.0`

**回答:** A

**ワンポイントアドバイス:**
`printArea()` は `Shape` に1つだけ定義されているが、内部で呼ぶ `area()` は各サブクラスの実装が使われる。これがポリモーフィズムの核心で「呼び出し方は共通、中身は各自」というパターン。

---

## Q32. キャスト（型変換）

**問題:** 以下のコードの出力結果は？
```java
abstract class Animal {
    abstract void speak();
}

class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("Woof!");
    }

    void fetch() {
        System.out.println("Fetching!");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal a = new Dog();
        a.speak();

        Dog d = (Dog) a;
        d.fetch();
    }
}
```

**選択肢:**
- A. `Woof!` → `Fetching!`
- B. `Woof!` のみ
- C. コンパイルエラー（`Animal` に `fetch()` がない）
- D. 実行時エラー（ClassCastException）
- E. `Fetching!` のみ

**回答:** A

**ワンポイントアドバイス:**
キャストの方向に注意。

| 種類 | 方向 | 記述 |
|---|---|---|
| アップキャスト | 子 → 親 | 自動（キャスト不要） |
| ダウンキャスト | 親 → 子 | `(型名)` が必要 |

- **コンパイル時** → 変数の型で判断（`Animal` か `Dog` か）
- **実行時** → 実体の型で判断（`new Dog()` か）

ダウンキャストは実体と型が一致しないと `ClassCastException` が発生するので `instanceof` で確認してから使うのが安全。
```java
if (a instanceof Dog) {
    Dog d = (Dog) a;
    d.fetch();
}
```

---

## Q33. instanceof と ClassCastException

**問題:** 以下のコードの出力結果は？
```java
abstract class Animal {
    abstract void speak();
}

class Dog extends Animal {
    @Override
    void speak() { System.out.println("Woof!"); }
    void fetch() { System.out.println("Fetching!"); }
}

class Cat extends Animal {
    @Override
    void speak() { System.out.println("Meow!"); }
}

public class Main {
    public static void main(String[] args) {
        Animal[] animals = { new Dog(), new Cat(), new Dog() };

        for (Animal a : animals) {
            if (a instanceof Dog) {
                Dog d = (Dog) a;
                d.fetch();
            } else {
                a.speak();
            }
        }
    }
}
```

**選択肢:**
- A. `Fetching!` → `Meow!` → `Fetching!`
- B. `Woof!` → `Meow!` → `Woof!`
- C. `Fetching!` → `Fetching!` → `Meow!`
- D. 実行時エラー（ClassCastException）
- E. `Woof!` → `Meow!` → `Fetching!`

**回答:** A

**ワンポイントアドバイス:**
`instanceof` → ダウンキャスト → メソッド呼び出しの3ステップが定石。

```java
// ① instanceof で確認
if (a instanceof Dog) {
    // ② ダウンキャスト
    Dog d = (Dog) a;
    // ③ メソッド呼び出し
    d.fetch();
}
```

`instanceof` なしでキャストすると実体が違う場合に実行時 `ClassCastException` が発生する。Java 16以降は `if (a instanceof Dog d)` で確認とキャストを1行で書ける。

---

## Q34. staticメソッドとstaticフィールド

**問題:** 以下のコードの出力結果は？
```java
public class Counter {
    static int count = 0;

    Counter() {
        count++;
    }

    static int getCount() {
        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();

        System.out.println(Counter.getCount());
    }
}
```

**選択肢:**
- A. `0`
- B. `1`
- C. `3`
- D. コンパイルエラー
- E. `null`

**回答:** C

**ワンポイントアドバイス:**
`static` フィールドはクラス全体で1つ共有される。インスタンスフィールドとの違いは下記の通り。

| | インスタンスフィールド | staticフィールド |
|---|---|---|
| 保存場所 | インスタンスごと | クラスで1つ |
| アクセス方法 | `インスタンス名.フィールド` | `クラス名.フィールド` |
| 用途 | 各オブジェクトの状態 | 共有カウンターや定数 |

`static` メソッドはインスタンスを作らずに呼べる。`Math.max()` や `Integer.parseInt()` も static メソッド。

---

## Q35. オーバーロードとオーバーライドの区別

**問題:** 以下のコードの出力結果は？
```java
public class Printer {
    void print(String s) {
        System.out.println("String: " + s);
    }

    void print(int n) {
        System.out.println("int: " + n);
    }

    void print(String s, int n) {
        System.out.println("String+int: " + s + ", " + n);
    }
}

public class Main {
    public static void main(String[] args) {
        Printer p = new Printer();
        p.print("Hello");
        p.print(42);
        p.print("Score", 100);
    }
}
```

**選択肢:**
- A. `String: Hello` → `int: 42` → `String+int: Score, 100`
- B. `Hello` → `42` → `Score, 100`
- C. コンパイルエラー（同じメソッド名を複数定義できない）
- D. `String: Hello` → `String: 42` → `String+int: Score, 100`
- E. 実行時エラー

**回答:** A

**ワンポイントアドバイス:**
オーバーロードとオーバーライドの違いは下記の通り。

| | オーバーロード | オーバーライド |
|---|---|---|
| 定義場所 | 同じクラス内 | 子クラス |
| メソッド名 | 同じ | 同じ |
| 引数 | 違う | 同じ |
| 目的 | 同じ操作を異なる型に対応 | 親の処理を上書き |

`System.out.println()` 自体もオーバーロードの代表例。`String`・`int`・`double` など何を渡しても動くのは引数違いのメソッドが複数定義されているから。

---

## Q36. アクセス修飾子

**問題:** 以下のコードをコンパイルするとどうなる？
```java
package animal;

public class Animal {
    public String name = "Animal";
    protected String type = "Mammal";
    String sound = "...";
    private int age = 5;
}
```
```java
package main;

import animal.Animal;

public class Main {
    public static void main(String[] args) {
        Animal a = new Animal();
        System.out.println(a.name);   // ①
        System.out.println(a.type);   // ②
        System.out.println(a.sound);  // ③
        System.out.println(a.age);    // ④
    }
}
```

**選択肢:**
- A. すべて正常にコンパイル・実行される
- B. ①のみコンパイルエラー
- C. ①は通るが、②③④でコンパイルエラー
- D. ①②は通るが、③④でコンパイルエラー
- E. ①②③は通るが、④でコンパイルエラー

**回答:** C（最初の回答はD→解説後に理解）

**ワンポイントアドバイス:**
`protected` は同パッケージまたはサブクラスからアクセス可能。別パッケージの非サブクラスからはアクセス不可。

| 修飾子 | 同クラス | 同パッケージ | サブクラス | 別パッケージ |
|---|---|---|---|---|
| `public` | ✅ | ✅ | ✅ | ✅ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| なし | ✅ | ✅ | ❌ | ❌ |
| `private` | ✅ | ❌ | ❌ | ❌ |

---

## Q37. フィールドの初期値

**問題:** 以下のコードの出力結果は？
```java
public class Data {
    int number;
    double decimal;
    boolean flag;
    String text;
}

public class Main {
    public static void main(String[] args) {
        Data d = new Data();
        System.out.println(d.number);
        System.out.println(d.decimal);
        System.out.println(d.flag);
        System.out.println(d.text);
    }
}
```

**選択肢:**
- A. `0` → `0.0` → `false` → `null`
- B. `0` → `0.0` → `false` → `""`
- C. コンパイルエラー（初期化されていない）
- D. `null` → `null` → `null` → `null`
- E. `0` → `0` → `0` → `0`

**回答:** A（最初の回答はB→解説後に理解）

**ワンポイントアドバイス:**
デフォルト値が適用されるのはフィールドのみ。ローカル変数は初期化しないとコンパイルエラー。`null` と `""` は別物（`null` はオブジェクト自体が存在しない、`""` は空の文字列オブジェクト）。

| 型 | デフォルト値 |
|---|---|
| `int` / `long` / `short` / `byte` | `0` |
| `double` / `float` | `0.0` |
| `boolean` | `false` |
| 参照型（`String` など） | `null` |

---

## Q38. 例外処理（try-catch）

**問題:** 以下のコードの出力結果は？
```java
public class Main {
    public static void main(String[] args) {
        try {
            int[] arr = new int[3];
            arr[5] = 10;
            System.out.println("A");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("B");
        } finally {
            System.out.println("C");
        }
    }
}
```

**選択肢:**
- A. `A` → `C`
- B. `B` → `C`
- C. `A` → `B` → `C`
- D. `B` のみ
- E. 実行時エラーで終了

**回答:** B

**ワンポイントアドバイス:**
`finally` は例外の有無にかかわらず必ず実行される。

| ケース | 実行される |
|---|---|
| 例外なし | `try` → `finally` |
| 例外あり・catchで補足 | `try`（途中まで）→ `catch` → `finally` |
| 例外あり・catchなし | `try`（途中まで）→ `finally` → プログラム終了 |

---

## Q39. チェック例外と非チェック例外

**問題:** 以下のコードのうち、コンパイルエラーになるのはどれか？
```java
// ①
public class Main {
    public static void main(String[] args) {
        int[] arr = new int[3];
        arr[5] = 10;
    }
}

// ②
import java.io.*;
public class Main {
    public static void main(String[] args) {
        FileReader fr = new FileReader("test.txt");
    }
}

// ③
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("test.txt");
    }
}
```

**選択肢:**
- A. ①のみ
- B. ②のみ
- C. ③のみ
- D. ①と②
- E. ①②③すべて

**回答:** B（最初の回答はD→解説後に理解）

**ワンポイントアドバイス:**

| 種類 | いつエラー | 対処 |
|---|---|---|
| チェック例外 | コンパイル時 | `try-catch` か `throws` が必須 |
| 非チェック例外 | 実行時 | 任意（書かなくてもコンパイル通る） |

| | チェック例外 | 非チェック例外 |
|---|---|---|
| 親クラス | `Exception` | `RuntimeException` |
| 例 | `IOException`、`SQLException` | `NullPointerException`、`ArrayIndexOutOfBoundsException` |

---

## Q40. ArrayList の add と get

**問題:** 以下のコードの出力結果は？
```java
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");
        list.add(1, "Blueberry");

        System.out.println(list.size());
        System.out.println(list.get(1));
    }
}
```

**選択肢:**
- A. `3` / `Banana`
- B. `4` / `Banana`
- C. `4` / `Blueberry`
- D. `3` / `Blueberry`
- E. コンパイルエラー

**回答:** C（`4` / `Blueberry`）

**ワンポイントアドバイス:**
`add(index, value)` は指定位置に挿入し、以降の要素をずらす。

```
add("Apple")        → ["Apple"]
add("Banana")       → ["Apple", "Banana"]
add("Cherry")       → ["Apple", "Banana", "Cherry"]
add(1, "Blueberry") → ["Apple", "Blueberry", "Banana", "Cherry"]
```

| メソッド | 動作 |
|---|---|
| `add(value)` | 末尾に追加 |
| `add(index, value)` | 指定位置に挿入（以降をずらす） |
| `get(index)` | 指定位置の要素を取得 |
| `remove(index)` | 指定位置の要素を削除 |
| `size()` | 要素数を返す |

**補足: List インタフェースと ArrayList の使い分け**

`new List<>()` はコンパイルエラー（インタフェースはインスタンス化不可）。変数の型を `List` にするだけなら問題なし。

```java
List<String> list = new ArrayList<>();  // ✅ 推奨（後で実装クラスを差し替えやすい）
```

`add(index, value)` / `get()` / `size()` はすべて `List` インタフェースに定義されているため、`ArrayList` 固有のメソッドは不要。

| 宣言方法 | メリット |
|---|---|
| `List<String> list = new ArrayList<>()` | 実装クラスを差し替えやすい（推奨） |
| `ArrayList<String> list = new ArrayList<>()` | `ArrayList` 固有メソッドが使える場合のみ |
