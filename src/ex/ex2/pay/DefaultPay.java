package ex.ex2.pay;

public class DefaultPay implements Pay{
    @Override
    public boolean pay(int amount) {
        System.out.println("결제 수단이 존재하지 않습니다.");
        System.out.println("올바른 결제 수단을 선택해주세요.");
        return false;
    }
}
