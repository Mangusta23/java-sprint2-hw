import java.util.HashMap;
public class Converter {
    String[] monthsNames = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    String MonthToName(String fileName){
        HashMap<String, String> months = new HashMap<>();
        String monthKey;
        for(int i = 1; i < 10; i++){
            monthKey = "m.20210" + i + ".csv";
            months.put(monthKey, monthsNames[i-1]);
        }
        for(int i = 10; i <=12; i++){
            monthKey = "m.2021" + i + ".csv";
            months.put(monthKey, monthsNames[i-1]);
        }
        String x = months.get(fileName);
        return x;
    }

    String numberToName(int i){
        String x;
        if (i > 0 && i < 10){
            x = "0" + i;
        }else{
            x = Integer.toString(i);
        }
        return x;
    }

    String numberToMonth(int m){
        String x = monthsNames[m - 1];
        return x;
    }
}
