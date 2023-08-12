public class CashOperation {
    public String category;
    public boolean isExpense;
    public double sumMoney;
    public double quantity;

    public CashOperation(String category, boolean isExpense, double quantity, double sumMoney) {
        this.category = category;
        this.isExpense = isExpense;
        this.sumMoney = sumMoney;
        this.quantity = quantity;
    }
}
