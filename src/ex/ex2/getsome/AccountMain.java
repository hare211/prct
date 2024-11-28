package ex.ex2.getsome;

import java.util.Scanner;

public class AccountMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account account = new Account();

        while (true) {
            System.out.println("1. 입금 | 2. 출금 | 3. 잔액 확인 | 4. 종료");
            System.out.print("메뉴를 선택하세요 : ");
            int menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    System.out.print("입금하려는 금액을 입력해주세요 : ");
                    int depositAmount = scanner.nextInt();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("출금하려는 금액을 입력해주세요 : ");
                    int withdrawAmount = scanner.nextInt();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.println("현재 잔액 : " + account.balance + "원");
                    break;
                case 4:
                    System.out.println("시스템을 종료합니다.");
                    return;
            }
        }
    }
}
