import java.util.HashMap;
import java.util.ArrayList;
public class FileContainerMonths {
    public HashMap<String, ArrayList<CashOperation>> monthToSales = new HashMap<>();
    public ArrayList<String> monthNames = new ArrayList<>();

    public void addMonth(String monthName, String fileName) {

        ArrayList<CashOperation> sales = new ArrayList<>();
        System.out.print(monthName + ": ");

        FileReader reader = new FileReader();
        ArrayList<String> lines = reader.readFileContents(fileName);
        if (!lines.isEmpty()) {
            lines.remove(0);
            for (String line : lines) {
                String[] parts = line.split(","); // ["категория", "трата/прибль", "кол-во", "цена товара за штуку"]
                String category = parts[0];
                boolean isExpense = Boolean.parseBoolean(parts[1]);
                double sumMoney = Double.parseDouble(parts[3]);
                double quantity = Double.parseDouble(parts[2]);
                CashOperation sale = new CashOperation(category, isExpense, quantity, sumMoney);
                sales.add(sale);
            }
            monthToSales.put(monthName, sales);
            monthNames.add(monthName);
            System.out.println("добавлен");
        }
    }

    double getSum(String m){
        double sum = 0;
        ArrayList<CashOperation> x = monthToSales.get(m);
        for (CashOperation cash : x){
            if (cash.isExpense){
                sum -= (cash.sumMoney * cash.quantity);
            }else{
                sum += (cash.sumMoney * cash.quantity);
            }
        }
        return sum;
    }

    void printReport(int m){
        Converter conv = new Converter();
        String monthName = conv.numberToMonth(m);
        ArrayList<CashOperation> x = monthToSales.get(monthName);
        double profitSum = 0;
        double expSum = 0;
        String profitCtgr = "";
        String expCtgr = "";
        for (CashOperation s : x){
            double localSum = 0;
            double localExp = 0;
            if (!s.isExpense) {
                localSum += (s.sumMoney * s.quantity);
                if (localSum > profitSum){
                    profitSum = localSum;
                    profitCtgr = s.category;
                }
            }else{
                localExp += (s.sumMoney * s.quantity);
                if (localExp > expSum){
                    expSum = localExp;
                    expCtgr = s.category;
                }
            }
        }
        System.out.println("Отчет за:" + monthName);
        System.out.println("Самый прибыльный товар: " + profitCtgr);
        System.out.println("Прибыль: " + profitSum);
        System.out.println("Самый большая трата в категории: " + expCtgr);
        System.out.println("Потрачено: " + expSum);
    }
}
