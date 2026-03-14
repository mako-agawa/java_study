// 1. インターフェース（ルール・免許証）
interface Playable {
  // インターフェースのメソッドは自動的に public abstract になる
  void play();
}

// 2. 実装クラス（実体・ドライバー）
class Cat implements Playable {
  // インターフェースの約束を守る（必ず public をつける！）
  @Override
  public void play() {
    System.out.println("猫じゃらしで遊ぶ");
  }

  // Cat クラス独自のメソッド（Playable 免許には載っていない）
  public void hello() {
    System.out.println("ニャー");
  }
}

public class Main {
  public static void main(String[] args) {
    // 【重要】型(Playable) と 実体(Cat) が違う状態
    Playable p = new Cat();

    // ① 型（免許証）に載っているメソッドはそのまま呼べる
    p.play();

    // ② 型（免許証）に載っていないメソッドは、そのままではエラーになる
    // p.hello(); // ← コメントアウトを外すとコンパイルエラー！

    // ③ 「キャスト」を使って、正体（Cat）の能力を無理やり引き出す
    System.out.println("--- キャスト後 ---");
    ((Cat) p).hello();
  }
}
