package prct;

public class DefaultPay implements Pay{
    @Override
    public boolean pay(int amount) {
        System.out.println("존재하지 않는 시스템입니다.");
        return false;
        //   ddd
    }
}
