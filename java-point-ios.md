# Java 学習ポイント（Claude app）

> Claude app（iOS / Web）でのやり取りをまとめたファイルです。

## Q1. static とは何か

**問題:** static キーワードとは何か？

**回答:**

`static` は「インスタンスを作らなくても使える」という意味のキーワード。

```java
// static なし → new が必要
class Dog {
    String name;
    void bark() {
        System.out.println(name + "が吠えた");
    }
}
Dog d = new Dog();
d.bark();

// static あり → new 不要、クラス名で直接呼べる
class MathUtil {
    static int add(int a, int b) {
        return a + b;
    }
}
int result = MathUtil.add(3, 5); // → 8
```

| | static あり | static なし |
|---|---|---|
| 所属 | クラスそのもの | インスタンス（オブジェクト） |
| 呼び方 | `クラス名.メソッド()` | `インスタンス.メソッド()` |
| new が必要 | 不要 | 必要 |
| 用途 | ユーティリティ、定数、main メソッド | オブジェクトごとに状態が違うもの |

`main` メソッドが static なのは、インスタンスを作る前に Java から呼ばれるため。

```java
public static void main(String[] args) {
    // Java はここから起動する
}
```

**ワンポイントアドバイス:**
static はクラスに1つだけ存在する「共有の棚」。インスタンスは「個人の棚」。用途に応じて使い分けよう。
