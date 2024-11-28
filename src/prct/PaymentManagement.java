package prct;

public class PaymentManagement {
    private int sumAmount;

    public PaymentManagement() {
        this.sumAmount = 0;
    }

    public void addAmount(int amount) {
        if (amount < 0) {
            return;
        }
        sumAmount += amount;
    }

    public int getAmount() {
        return sumAmount;
    }

}
