package ex.objectOriented.pay;

public class PayService {

    public void processPay(String option, int amount) {
        System.out.println("결제를 시작합니다 : PaySystem : " + option + ", 결제 금액 : " + amount);
        Pay pay = Payment.findPay(option);

        boolean result = pay.pay(amount);

        if (result) {
            System.out.println("결제가 완료되었습니다.");
        } else {
            System.out.println("결제가 실패했습니다.");
        }

    }
}
