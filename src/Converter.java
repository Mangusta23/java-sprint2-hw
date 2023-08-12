import java.util.HashMap;
public class Converter {
    String[] monthsNames = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    String[] monthsNumbers = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

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

    String numberToName(String m){
        String x = "";
        for(int i = 0; i < 12; i++){
            if(monthsNames[i] == m){
                x = monthsNumbers[i];
            }
        }
        return x;
    }

    String numberToMonth(int m){
        String x = monthsNames[m--];
        return x;
    }

    int monthToNumber(String mnth){
        int x = 0;
        for (int i=0; i<12; i++){
            if(mnth == monthsNames[i]){
                x = i;
            }
        }
        return x;
    }
}
