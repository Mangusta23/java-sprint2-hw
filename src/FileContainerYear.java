import java.util.ArrayList;
public class FileContainerYear {
    public ArrayList<CashOperationYear> salesYear = new ArrayList<>();

    public void addYear(String fileName) {

        FileReader reader = new FileReader();
        ArrayList<String> linesYear = reader.readFileContents(fileName);
        if (!linesYear.isEmpty()) {
            for (String line : linesYear) {
                String[] parts = line.split(","); // ["месяц(номер)", "трата/прибль", "сумма"]
                String month = parts[0];
                boolean isExpense = Boolean.parseBoolean(parts[1]);
                double money = Double.parseDouble(parts[2]);
                CashOperationYear sale = new CashOperationYear(month, isExpense, money);
                salesYear.add(sale);
            }
        }
    }

    double getSum(int m){
        Converter conv = new Converter();
        String monthNumber = conv.numberToName(m);
        double sum = 0;
        for (CashOperationYear x : salesYear){
            if (x.month.equals(monthNumber)){
                if(x.isExpense){
                    sum -= x.money;
                }else{
                    sum += x.money;
                }
            }
        }
        return sum;
    }

    void printReport(){
        System.out.println(salesYear);
    }
}