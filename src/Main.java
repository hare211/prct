import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;

        while (true) {
            System.out.print("1에서 100 사이의 정수를 입력하세요: ");


            if (scanner.hasNextInt()) {
                number = scanner.nextInt();


                if (number >= 1 && number <= 100) {
                    System.out.println("입력된 값: " + number);
                    break;
                } else {
                    System.out.println("오류: 1에서 100 사이의 정수를 입력하세요.");
                }
            } else {
                System.out.println("오류: 유효한 정수를 입력하세요.");
                scanner.next();
            }
        }

        scanner.close();
    }
    }