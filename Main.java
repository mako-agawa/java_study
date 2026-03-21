public class Main {
  static int count = 0;

  public static void main(String[] args) {
    for (int i = 0; i < 3; i++) {
      count++;
    }
    System.out.println(count);

    System.out.println("=====================");
    System.out.println("問題3の回答：B");
    System.out.println("=====================");
  }

  static {
    count = 10;
  }
}
