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
                    System.out.println("2 - Все месячные отчет");
                    System.out.println("3 - Годовой отчет");
                    int cmnd = scanner.nextInt();
                    String fileName;
                    String monthName;
                    switch (cmnd) {
                        case 1:
                            System.out.println("Введите название файла");
                            fileName = scanner.next();
                            monthName = converter.MonthToName(fileName); // По названию файла типа m.YYYYMM.csv возвращает название месяца
                            //fileName – имя файла в папке resources
                            monthContainer.addMonth(monthName, fileName); // Создает/добавляет_в мапу <месяц(алендарное название), список с <категория, трата/прибыль, сумма, цена товара>>
                            break;
                        case 2:
                            for (int i = 0; i <= 12; i++){
                                if(i < 10){
                                    fileName = "m.20210" + i + ".csv";
                                }else{
                                    fileName = "m.2021" + i + ".csv";
                                }
                                monthName = converter.MonthToName(fileName);
                                monthContainer.addMonth(monthName, fileName);
                            }
                            break;
                        case 3:
                            System.out.println("Введите название файла");
                            fileName = scanner.next();
                            //fileName – имя файла в папке resources
                            yearContainer.addYear(fileName); // Создает список ["месяц", "трата/прибль", "сумма"]
                            break;
                        default:
                            System.out.println("Извините, такой команды пока нет.");
                            break;
                    }
                    break;
                case 2:
                    boolean b = true;
                    if(yearContainer.check) {
                        if (monthContainer.monthNames.size() != 0) {
                            for (String mnth : monthContainer.monthNames) {
                                if (!(monthContainer.getSum(mnth) == yearContainer.getSum(mnth))) {
                                    System.out.println("Месячный отчет за " + mnth + " не сходится с годовым");
                                    b = false;
                                }
                            }
                            if (b) {
                                System.out.println("Все месячные отчеты сходятся с годовым");
                            }
                        }else{
                            System.out.println("Отсутствует хотя бы один месячный отчет");
                            }
                    } else {
                        System.out.println("Отсутстыует годовой отчет");
                    }
                    break;
                case 3:
                    System.out.println("Какой месяц вывести?: 0-Все отчеты");
                    System.out.println("                      1-ЯНВ.  2-ФЕВ. 3-МАРТ. 4-АПР.  5-МАЙ.  6-ИЮНЬ.");
                    System.out.println("                      7-ИЮЛЬ. 8-АВГ. 9-СЕН.  10-ОКТ. 11-НОЯ. 12-ДЕК.");
                    m = scanner.nextInt();
                    if (m == 0){
                        if(yearContainer.check) {
                            if (monthContainer.monthNames.size() != 0) {
                                for (String mnth : monthContainer.monthNames) {
                                    m = converter.monthToNumber(mnth);
                                    System.out.println("Месячныйй отчет:");
                                    monthContainer.printReport(m);
                                    System.out.println(" ");
                                }
                            }else{
                                System.out.println("Отсутствует хотя бы один месячный отчет");
                            }
                        }else{
                            System.out.println("Отсутствует годовой отчет");
                        }
                    }else{
                        if(yearContainer.check) {
                            if (monthContainer.monthNames.size() != 0) {
                                System.out.println("Месячныйй отчет:");
                                monthContainer.printReport(m);
                                System.out.println(" ");
                            }
                        }
                    }
                    System.out.println("Годовой отчет:");
                    yearContainer.printReport();
                    break;
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

