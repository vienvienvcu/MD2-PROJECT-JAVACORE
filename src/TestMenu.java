import business.feature.Impl.CategoryFeatureImpl;

import java.util.Scanner;

import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;

public class TestMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            do {
                System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━━ MENU WISH LIST━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("┃                                                      ┃");
                System.out.println("┃      " + GREEN + "1. SHOW ALL PRODUCTS IN WISH LIST  " + BLUE + "             ┃");
                System.out.println("┃      " + GREEN + "2. DELETE ONE PRODUCT IN WISH LIST " + BLUE + "             ┃");
                System.out.println("┃      " + GREEN + "3. DELETE ALL PRODUCTS IN WISH LIST   " + BLUE + "          ┃");
                System.out.println("┃      " + GREEN + "4. ADD PRODUCT IN WISH LIST " + BLUE + "                    ┃");
                System.out.println("┃      " + GREEN + "5. BACK               " + BLUE + "                          ┃");
                System.out.println("┃                                                      ┃");
                System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                System.out.println("Your choice to 1 from 5: ");
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
