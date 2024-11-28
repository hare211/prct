package prct;

public class NaverPay implements Pay{
    @Override
    public boolean pay(int amount) {
        System.out.println("네이버 페이 시스템과 연결합니다.");
        if (amount < 0) {
            System.out.println("결제 금액은 음수가 될 수 없습니다.");
            return false;
        } else {
            System.out.println(amount + "원 결제를 시도합니다.");
        }
        return true;
    }
}
