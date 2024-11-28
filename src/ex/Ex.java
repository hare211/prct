package ex;

import java.util.Scanner;

public class Ex {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int i = 0;
        int maxLength = 0;
        String maxLengthString = "";
        int sumLength = 0;

        while (i < 3) {
            System.out.print("문자열 입력 : ");
            String str = scanner.nextLine();
            if (str.length() > maxLength) {
                maxLength = str.length();
                maxLengthString = str;
            }
            sumLength += str.length();
            i++;
        }
        System.out.println("가장 긴 문자열 : " + maxLengthString);

        System.out.println("문자열 길이 합 : " + sumLength);

    }
}
