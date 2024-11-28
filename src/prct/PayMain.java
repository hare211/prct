package prct;

import java.util.Scanner;

public class PayMain {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PayService payService = new PayService();
        PayAmount payAmount = new PayAmount();

        while (true) {
            System.out.println("메뉴를 선택해주세요");
            System.out.print("1. 결제 | 2. 총 결제금액 | 3. 종료 : ");
            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:
                    System.out.print("결제 시스템 입력 : ");
                    String option = scanner.nextLine();

                    System.out.print("결제 금액 입력 : ");
                    int amount = scanner.nextInt();

                    payService.processPay(option, amount);
                    payAmount.addAmount(amount);
                    break;
                case 2:
                    System.out.println("총 결제금액 : " + payAmount.getAmount());
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 메뉴입니다.");
                    break;
            }
        }





        /*
        PayService payService = new PayService();
        //kakao 결제
        String payOption1 = "kakao";
        int amount1 = 5000;
        payService.processPay(payOption1, amount1);
        //naver 결제
        String payOption2 = "naver";
        int amount2 = 10000;
        payService.processPay(payOption2, amount2);
        //잘못된 결제 수단 선택
        String payOption3 = "bad";
        int amount3 = 15000;
        payService.processPay(payOption3, amount3)
        */
    }
}
