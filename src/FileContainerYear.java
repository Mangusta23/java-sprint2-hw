import java.util.ArrayList;
public class FileContainerYear {
    public ArrayList<CashOperationYear> salesYear = new ArrayList<>();
    boolean check = false;
    String yearHolder;

    public void addYear(String fileName) {

        FileReader reader = new FileReader();
        ArrayList<String> linesYear = reader.readFileContents(fileName);
        if (!linesYear.isEmpty()) {
            linesYear.remove(0);
            for (String line : linesYear) {
                String[] parts = line.split(","); // ["месяц(номер)", "трата/прибль", "сумма"]
                String month = parts[0];
                boolean isExpense = Boolean.parseBoolean(parts[2]);
                double money = Double.parseDouble(parts[1]);
                CashOperationYear sale = new CashOperationYear(month, isExpense, money);
                salesYear.add(sale);
            }
            check = true;
            String[] yearParts = fileName.split("\\.");
            yearHolder = yearParts[1];
            System.out.println("Годовой отчет добавлен");
        }
    }

    double getSum(String m){
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
        Converter conv = new Converter();
        FileContainerMonths t = new FileContainerMonths();

        System.out.println("Отчет за " + yearHolder + " год.");
        System.out.println("Прибыль:");
        for (String n : t.monthNames){
            String nx = conv.numberToName(n);
            double sum = 0;
            for (CashOperationYear s : salesYear){
                if (s.month.equals(nx)){
                    if(s.isExpense){
                        sum -= s.money;
                    }else{
                        sum += s.money;
                    }
                }
            }
            System.out.println(n + ": " + sum);
        }
        double sumProfit = 0;
        double sumExpense = 0;
        int iProf = 0;
        int iExp = 0;
        for (CashOperationYear s : salesYear){
            if (s.isExpense){
                sumExpense += s.money;
                iExp++;
            }else{
                sumProfit += s.money;
                iProf++;
            }
        }
        System.out.println("Средний расход за все операции за год - " + (sumExpense / iExp));
        System.out.println("Средняя прибыль за все операции за год - " + (sumProfit / iProf));
    }
}