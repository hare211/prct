import java.lang.reflect.Array;
import java.util.*;

public class Center {
    public static void main(String[] args) {

//      1.5명의 학생의 국어점수를 받아서 등수를 출력하는 프로그램 작성

        /*
        int[] kor = new int[5];

        for (int i = 0; i < kor.length; i++) {
            kor[i] = (int)(Math.random() * 100) + 1;
        }

        Arrays.sort(kor);

        for (int i = 0; i < kor.length / 2; i++) {
            int temp = kor[i];
            kor[i] = kor[kor.length - i - 1];
            kor[kor.length - i - 1] = temp;

        }

        System.out.println(Arrays.toString(kor));

         */

//		2.10개의 임의의 정수를 받아서 최대값,최소값(난수)  구하는 프로그램 작성
        /*
        int[] arr = new int[10];
        int max = 0;
        int min = 100;

        for (int i = 0; i < 10; i++) {
            arr[i] = (int)(Math.random() * 101);
            if (min > arr[i]) {
                min = arr[i];
            } else if (max < arr[i]) {
                max = arr[i];
            }
        }
        System.out.print(max + " " + min);

         */

//		3.100 개의 난수(0~9)를 발생하여 빈도수를 구하는 프로그램을 작성하시오
//		  int[] arr=new int[10]
        /*
        int[] arr = new int[10];
        for (int i = 0; i < 100; i++) {
            int num = (int)(Math.random() * 10);
            arr[num]++;
        }
        System.out.println(Arrays.toString(arr));

         */

//		4.10개의 난수를 발생하여 배열에 저장한 후에
//		   인덱스가 1인 곳의 값을 출력하시오.
        /*
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = (int)(Math.random() * 101);
        }
        System.out.println(arr[1]);

         */

//		5.위 문제 배열 중에서 최대값의 인덱스를 출력하시오. 
        /*
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = (int)(Math.random() * 101);
        }

        System.out.println(Arrays.toString(arr));

        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        System.out.println("max = " + max);

         */

//		6.10개의 난수로 이루어진 배열 안의 모든 값을 더하고, 총합과 평균을 출력하시오.

        /*
        int sum = 0;
        double avg = 0.0;

        int[] arr = new int[10];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
            sum += arr[i];
        }
        avg = sum / (double) arr.length;

        System.out.println("sum = " + sum + " avg = " + avg);

         */

//		7.10개의 난수를 배열에 저장 후에 배열의 인덱스가 3인 곳은 출력하지 말고, 나머지만 출력하시오
        /*
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = (int) (Math.random() * 101);
            if (i != 3) {
                System.out.print(arr[i] + " ");
            }
        }

         */
//		8.5개의 난수를 배열에 저장한 후에 인덱스번호 두개를 받아서 값을 교환하는 프로그램을 작성하시오

        /*
        int[] arr = new int[5];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10) + 1;
        }

        int num = (int) (Math.random() * 5);
        int num1 = (int) (Math.random() * 5);

        System.out.println(arr[num] + " " + arr[num1]);

        int temp = arr[num];
        arr[num] = arr[num1];
        arr[num1] = temp;
        System.out.println(arr[num] + " " + arr[num1]);

         */

//		9.char 배열을 생성하여, 알파벳 A~Z까지 대입 후, 출력하시오
        /*
        char[] chars = new char[26];

        for (int i = 0; i < 26; i++) {
            chars[i] = (char) ('A' + i);
            System.out.print(chars[i] + " ");
        }

         */

//		10.위문제를 역순으로 출력하시오
        /*
        char[] chars = new char[26];

        for (int i = 0; i < 26; i++) {
            chars[i] = (char) ('Z' - i);
            System.out.print(chars[i] + " ");
        }

        */
        /* 10. 다른 풀이
        char[] chars = new char[26];

        // A부터 Z까지 대입
        for (int i = 0; i < 26; i++) {
            chars[i] = (char) ('A' + i);
        }

        // 배열을 역순으로 출력
        for (int i = chars.length - 1; i >= 0; i--) {
            System.out.print(chars[i] + " ");
        }
         */

//		11.정수 10개를 입력받아 배열에 저장하고, 배열에 있는 정수 중에서 3의 배수만 출력하시오
        /*
        int[] arr = new int[10];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10) + 1;
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 3 == 0) {
                System.out.print(arr[i] + " ");
            }
        }

         */
//		12.10개의 정수를 입력받아서 짝수의 최대값,홀수의 최대값을 출력하는 프로그램을 작성하시오
        /*
        int[] arr = new int[10];
        int evenMax = 0;
        int oddMax = 0;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100) + 1;
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        for (int j : arr) {
            if (j % 2 == 0 && evenMax < j) {
                evenMax = j;
            } else if (j % 2 != 0 && oddMax < j) {
                oddMax = j;
            }
        }
        System.out.print("짝수 최대값 : " + evenMax + ", 홀수 최대값 : " + oddMax);

         */


//		13.3명의 학생에 대한 국어,영어,수학 점수를 입력받아서 저장후에 총점,평균,등수를 구하고
//		국어점수 총점,영어점수 총점,수학점수의 총점을 구해서 출력하는 프로그램을 작성하시오




    }
}
