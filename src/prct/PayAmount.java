package prct;

public class PayAmount {
    private int sumAmount;

    public PayAmount() {
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
