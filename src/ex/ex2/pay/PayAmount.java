package ex.ex2.pay;

public class PayAmount {
    private int totalAmount = 0;

    public void addAmount(int amount) {
        totalAmount += amount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }


}