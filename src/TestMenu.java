import business.feature.Impl.CategoryFeatureImpl;

import java.util.Scanner;

import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;

public class TestMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            do {
                System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━━━━━ MENU ORDER ━━━━━━━━━━━━━━━━━━━━┓");
                System.out.println("┃                                                      ┃");
                System.out.println("┃      " + GREEN + "1. SHOW ALL PRODUCTS IN CART   " + BLUE + "                 ┃");
                System.out.println("┃      " + GREEN + "2. ADD CART IN ORDER " + BLUE + "                           ┃");
                System.out.println("┃      " + GREEN + "3. SHOW DETAIL ORDER " + BLUE + "                           ┃");
                System.out.println("┃      " + GREEN + "4. SHOW HISTORY ODER BY ORDER STATUS   " + BLUE + "         ┃");
                System.out.println("┃      " + GREEN + "5. SHOW ALL HISTORY ORDERED    " + BLUE + "                 ┃");
                System.out.println("┃      " + GREEN + "6. BACK               " + BLUE + "                          ┃");
                System.out.println("┃                                                      ┃");
                System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
                System.out.println("Your choice to 1 from 6: ");
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
