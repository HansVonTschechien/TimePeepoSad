import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import static java.lang.System.currentTimeMillis;

public class Main {
    public static void main(String[] args) {

                Scanner scanner = new Scanner(System.in);
                boolean running = true;

                while (running) {
                    System.out.println("Vyberte aplikaci:");
                    System.out.println("1. Zjištění přestupného roku podle zadaného roku");
                    System.out.println("2. Zjištění přestupného roku podle aktuálního data");
                    System.out.println("3. Vlastní formát data a času podle systémového času");
                    System.out.println("4. Zbývající čas do určitého data");
                    System.out.println("5. Konec programu");

                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.println("Zadejte rok pro zjištění, zda je přestupný:");
                            int year = scanner.nextInt();
                            boolean isLeap = isLeapYear(year);
                            System.out.println("Rok " + year + " je " + (isLeap ? "přestupný." : "ne přestupný."));
                            break;
                        case 2:
                            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                            boolean isCurrentYearLeap = isLeapYear(currentYear);
                            System.out.println("Aktuální rok " + currentYear + " je " + (isCurrentYearLeap ? "přestupný." : "ne přestupný."));
                            if (!isCurrentYearLeap) {
                                int nextLeapYear = findNextLeapYear(currentYear);
                                System.out.println("Následující přestupný rok je " + nextLeapYear + ".");
                            }
                            break;
                        case 3:
                            long currentTimeMillis = currentTimeMillis();
                            Date currentDate = new Date(currentTimeMillis);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                            System.out.println("Aktuální systémový čas: " + dateFormat.format(currentDate));
                            break;
                        case 4:
                            System.out.println("Zadejte den:");
                            int day = scanner.nextInt();
                            System.out.println("Zadejte měsíc (1-12):");
                            int month = scanner.nextInt();
                            System.out.println("Zadejte rok:");
                            int targetYear = scanner.nextInt();

                            Calendar calendar = Calendar.getInstance();
                            calendar.set(targetYear, month - 1, day, 0, 0, 0);
                            Date targetDate = calendar.getTime();

                            long timeDifferenceMillis = targetDate.getTime() - currentTimeMillis();
                            long seconds = timeDifferenceMillis / 1000;
                            long minutes = seconds / 60;
                            long hours = minutes / 60;
                            long days = hours / 24;
                            long months = days / 30;
                            long years = months / 12;

                            System.out.println("Do zadaného data zbývá:");
                            System.out.println(years + " let");
                            System.out.println(months % 12 + " měsíců");
                            System.out.println(days % 30 + " dní");
                            System.out.println(hours % 24 + " hodin");
                            System.out.println(minutes % 60 + " minut");
                            System.out.println(seconds % 60 + " sekund");
                            break;
                        case 5:
                            running = false;
                            break;
                        default:
                            System.out.println("Neplatná volba. Zvolte znovu.");
                            break;
                    }
                }
                scanner.close();
            }

            public static boolean isLeapYear(int year) {
                return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
            }

            public static int findNextLeapYear(int currentYear) {
                int nextYear = currentYear + 1;
                while (!isLeapYear(nextYear)) {
                    nextYear++;
                }
                return nextYear;
            }
        }


