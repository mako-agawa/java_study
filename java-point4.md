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