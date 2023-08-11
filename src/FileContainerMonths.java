import java.util.HashMap;
import java.util.ArrayList;
public class FileContainerMonths {
    public HashMap<String, ArrayList<CashOperation>> monthToSales = new HashMap<>();
    public ArrayList<String> monthNames = new ArrayList<>();

    public void addMonth(String monthName, String fileName) {

        ArrayList<CashOperation> sales = new ArrayList<>();

        FileReader reader = new FileReader();
        ArrayList<String> lines = reader.readFileContents(fileName);
        if (!lines.isEmpty()) {
            for (String line : lines) {
                String[] parts = line.split(","); // ["категория", "трата/прибль", "сумма", "цена товара за штуку"]
                String category = parts[0];
                boolean isExpense = Boolean.parseBoolean(parts[1]);
                double sumMoney = Double.parseDouble(parts[2]);
                double moneyForOneItem = Double.parseDouble(parts[3]);
                CashOperation sale = new CashOperation(category, isExpense, sumMoney, moneyForOneItem);
                sales.add(sale);
            }

            monthToSales.put(monthName, sales);
            monthNames.add(monthName);
        }
    }

    double getSum(int m){
        Converter conv = new Converter();
        String monthName = conv.numberToMonth(m);
        double sum = 0;
        if (monthNames.contains(monthName)){
            ArrayList<CashOperation> x = monthToSales.get(monthName);
            for (CashOperation cash : x){
                if (cash.isExpense){
                    sum -= cash.sumMoney;
                }else{
                    sum += cash.sumMoney;
                }
            }
        }
        return sum;
    }

    void printReport(int m){
        Converter conv = new Converter();
        String monthName = conv.numberToMonth(m);
        ArrayList<CashOperation> x = monthToSales.get(monthName);
        System.out.println(x);
    }
}
