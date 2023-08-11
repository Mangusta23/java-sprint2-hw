import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileContainerMonths monthContainer = new FileContainerMonths();
        FileContainerYear yearContainer = new FileContainerYear();
        Scanner scanner = new Scanner(System.in);
        Converter converter = new Converter();

        while(true) {
            printMenu();
            int m;
            int command = scanner.nextInt();
            switch (command) {
                case 1:
                    System.out.println("Какие файлы считывать? ");
                    System.out.println("1 - Месячный отчет");
                    System.out.println("2 - Все месячные отчеты");
                    System.out.println("3 - Годовой отчет");
                    int cmnd = scanner.nextInt();
                    String fileName;
                    String monthName;
                    switch (cmnd){
                        case 1:
                            System.out.println("Введите название файла");
                            fileName = scanner.next();
                            monthName = converter.MonthToName(fileName); // По названию файла типа m.YYYYMM.csv возвращает название месяца
                            //fileName – имя файла в папке resources
                            monthContainer.addMonth(monthName, fileName); // Создает/добавляет_в мапу <месяц(алендарное название), список с <категория, трата/прибыль, сумма, цена товара>>
                        case 2:
                            for (int i = 0; i <= 12; i++){
                                if(i < 10){
                                    fileName = "m.2021.0" + i + ".csv";
                                }else{
                                    fileName = "m.2021." + i + ".csv";
                                }
                                monthName = converter.MonthToName(fileName);
                                monthContainer.addMonth(monthName, fileName);
                            }
                        case 3:
                            System.out.println("Введите название файла");
                            fileName = scanner.next();
                            //fileName – имя файла в папке resources
                            yearContainer.addYear(fileName); // Создает список ["месяц", "трата/прибль", "сумма"]
                        default:
                            System.out.println("Извините, такой команды пока нет.");
                    }
                case 2:
                    boolean b = true;
                    for (m = 1; m < 13; m++) {
                        if (!(monthContainer.getSum(m) == yearContainer.getSum(m))) {
                            System.out.println("Месячный отчет за " + converter.numberToMonth(m) + " не сходится с годовым");
                            b = false;
                        }
                    }
                    if (b==true){
                        System.out.println("Все месячный отчеты сходятся с годовым");
                    }
                case 3:
                    System.out.println("Какой месяц вывести?: 1-ЯНВ.  2-ФЕВ. 3-МАРТ. 4-АПР.  5-МАЙ.  6-ИЮНЬ.");
                    System.out.println("                      7-ИЮЛЬ. 8-АВГ. 9-СЕН.  10-ОКТ. 11-НОЯ. 12-ДЕК.");
                    m = scanner.nextInt();
                    System.out.println("Месячныйй отчет:");
                    monthContainer.printReport(m);
                    System.out.println("Годовой отчет:");
                    yearContainer.printReport();
                case 0:
                    System.out.println("Выход");
                    return;
                default:
                    System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Добавить файл");
        System.out.println("2 - Сверить месячный и годовой отчет");
        System.out.println("3 - Вывод месячого и годового отчета");
        System.out.println("0 - Выход");
    }
}

