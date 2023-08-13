import java.util.ArrayList;
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
                    String fileName;
                    String monthName;
                            for (int i = 1; i <= 12; i++) {
                                if (i < 10) {
                                    fileName = "m.20210" + i + ".csv";
                                } else {
                                    fileName = "m.2021" + i + ".csv";
                                }
                                monthName = converter.MonthToName(fileName);
                                monthContainer.addMonth(monthName, fileName);
                            }
                            break;
                case 2:
                    System.out.println("Введите название файла");
                    fileName = scanner.next();
                    //fileName – имя файла в папке resources
                    yearContainer.addYear(fileName); // Создает список ["месяц", "трата/прибль", "сумма"]
                    break;
                case 3:
                    boolean b = true;
                    if (yearContainer.check) {
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
                        } else {
                            System.out.println("Отсутствует хотя бы один месячный отчет");
                        }
                    } else {
                        System.out.println("Отсутстыует годовой отчет");
                    }
                    break;
                case 4:
                    if (yearContainer.check) {
                        if (monthContainer.monthNames.size() != 0) {
                            for (String mnth : monthContainer.monthNames) {
                                m = converter.monthToNumber(mnth);
                                System.out.println("Месячныйй отчет:");
                                monthContainer.printReport(m);
                                System.out.println(" ");
                            }
                        } else {
                            System.out.println("Отсутствует хотя бы один месячный отчет");
                        }
                    } else {
                        System.out.println("Отсутствует годовой отчет");
                    }
                    break;
                case 5:
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
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию обо всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }
}

