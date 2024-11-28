package objectOriented.pay;

import java.util.Scanner;

public class PayMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PayAmount payAmount = new PayAmount();
        PayService payService = new PayService();



        while (true) {
            System.out.println("메뉴를 선택해주세요.");
            System.out.print("1. 결제 | 2. 총 결제금액 | 3. 종료 : ");

            int menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    System.out.print("결제 시스템을 선택해주세요 : ");
                    String option = scanner.next();

                    System.out.print("결제 금액을 입력해주세요 : ");
                    int amount = scanner.nextInt();

                    payService.processPay(option, amount);
                    payAmount.addAmount(amount);

                    continue;
                case 2:
                    System.out.println("총 결제 금액 : " + payAmount.getTotalAmount() + "원");

                    continue;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
        }

    }
}
