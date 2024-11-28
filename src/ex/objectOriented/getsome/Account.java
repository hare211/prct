package ex.objectOriented.getsome;

public class Account {
    int balance;

    int deposit(int depositAmount) {
        balance += depositAmount;
        System.out.println(depositAmount + "원 입금 되었습니다. 현재 잔액 : " + balance + "원");
        return balance;
    }

    int withdraw(int withdrawAmount) {
        if (balance >= withdrawAmount) {
            balance -= withdrawAmount;
            System.out.println(withdrawAmount + "원 출금 되었습니다. 현재 잔액 : " + balance + "원");
        } else {
            System.out.println("잔액이 부족합니다. 잔액 : " + balance + "원");
        }
        return balance;
    }
}
