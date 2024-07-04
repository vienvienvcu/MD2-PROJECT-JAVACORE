import business.feature.Impl.CategoryFeatureImpl;

import java.util.Scanner;

import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;

public class TestMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            do {
                System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━ MENU ADMIN ━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("┃                                                 ┃");
                System.out.println("┃      " + GREEN + "1. MANAGEMENT CATALOG    " + BLUE + "                  ┃");
                System.out.println("┃      " + GREEN + "2. MANAGEMENT PRODUCT          " + BLUE + "            ┃");
                System.out.println("┃      " + GREEN + "3. MANAGEMENT ORDER      " + BLUE + "                  ┃");
                System.out.println("┃      " + GREEN + "4. MANAGEMENT USERS     " + BLUE + "                   ┃");
                System.out.println("┃      " + GREEN + "5. MANAGEMENT STATISTICAL   " + BLUE + "               ┃");
                System.out.println("┃      " + GREEN + "6. SHOW LIST ROLE   " + BLUE + "                       ┃");
                System.out.println("┃      " + GREEN + "7. LOG OUT               " + BLUE + "                  ┃");
                System.out.println("┃                                                 ┃");
                System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                System.out.println("Your choice to 1 from 4: ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:

                        break;
                    case 7:
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Enter a valid choice 1-6");
                }
            } while (true);
        }
}
