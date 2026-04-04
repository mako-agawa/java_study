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