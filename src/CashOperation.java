public class CashOperation {
    public String category;
    public boolean isExpense;
    public double sumMoney;
    public double moneyForOneItem;

    public CashOperation(String category, boolean isExpense, double sumMoney, double moneyForOneItem) {
        this.category = category;
        this.isExpense = isExpense;
        this.sumMoney = sumMoney;
        this.moneyForOneItem = moneyForOneItem;
    }
}
