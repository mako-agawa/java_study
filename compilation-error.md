# Java コンパイルエラーあるある 20

## 型・変数系

```java
// 1. 型が合わない
int n = "hello";           // String を int に代入

// 2. 変数の未初期化
int x;
System.out.println(x);    // 初期化前に使用

// 3. 存在しない変数
System.out.println(val);  // val が宣言されていない

// 4. スコープ外の参照
if (true) { int x = 1; }
System.out.println(x);    // x は if の外からは見えない

// 5. キャストなしの縮小変換
double d = 3.14;
int n = d;                 // double → int は明示的キャスト必要
int n = (int) d;           // OK
```

---

## メソッド系

```java
// 6. 戻り値の型違い
public int getValue() {
    return "hello";        // int なのに String を返す
}

// 7. return がない
public int getValue() {
    int x = 1;             // return がない
}

// 8. void の戻り値を代入
public void doSomething() { }
int x = doSomething();    // void は代入できない

// 9. 引数の数・型が合わない
public void print(String s) { }
print(123);               // int を渡している

// 10. 到達不可なコード
return x;
x++;                      // return の後
```

---

## クラス・継承系

```java
// 11. 抽象クラスのインスタンス化
abstract class Shape { }
new Shape();              // 直接生成不可

// 12. 抽象メソッドの未実装
abstract class Shape { abstract void draw(); }
class Circle extends Shape { }  // draw() を実装していない

// 13. private メンバへのアクセス
class Sample { private int x; }
Sample s = new Sample();
s.x = 1;                 // private はクラス外からアクセス不可

// 14. インターフェースのメソッド未実装
interface Flyable { void fly(); }
class Bird implements Flyable { }  // fly() を実装していない

// 15. 多重継承
class A extends B, C { }  // クラスの多重継承は不可
```

---

## その他

```java
// 16. タイポ（クラス名・メソッド名）
public static void main(Stiring[] args)  // String のタイポ

// 17. セミコロン忘れ
int x = 1              // ; がない
System.out.println(x)

// 18. 括弧の不一致
if (x > 0 {            // ) が足りない
    System.out.println(x);
}

// 19. static でないメソッドを static コンテキストから呼ぶ
public class Main {
    int x = 1;
    public static void main(String[] args) {
        System.out.println(x);  // static から非static フィールドへのアクセス
    }
}

// 20. final 変数への再代入
final int MAX = 10;
MAX = 20;              // final は再代入不可
```

---

## よく出るエラーメッセージ

| メッセージ | 原因 |
|---|---|
| `cannot find symbol` | 変数・メソッドが見つからない（タイポ・スコープ外） |
| `incompatible types` | 型が合わない |
| `missing return statement` | return がない |
| `unreachable statement` | 到達不可なコード |
| `void type not allowed here` | void の戻り値を代入しようとしている |
| `non-static variable cannot be referenced from a static context` | static から非static へのアクセス |
| `class is abstract; cannot be instantiated` | 抽象クラスを直接生成しようとしている |
