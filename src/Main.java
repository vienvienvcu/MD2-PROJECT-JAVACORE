import static business.utils.Colors.BLUE;
import static business.utils.Colors.GREEN;

public class Main {
    public static void main(String[] args) {

        do {
            System.out.println(BLUE + "┏━━━━━━━━━━━━━━━━━━━━━━━━━ MENU PRODUCT ━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃                                                                 ┃");
            System.out.println("┃      " + GREEN + "1. SHOW PRODUCT   " + BLUE + "                                         ┃");
            System.out.println("┃      " + GREEN + "2. ADD PRODUCT          " + BLUE + "                                   ┃");
            System.out.println("┃      " + GREEN + "3. UPDATE PRODUCT      " + BLUE + "                                    ┃");
            System.out.println("┃      " + GREEN + "4. DELETE PRODUCT     " + BLUE + "                                     ┃");
            System.out.println("┃      " + GREEN + "5. SEARCH PRODUCT BY ID   " + BLUE + "                                 ┃");
            System.out.println("┃      " + GREEN + "6. SORT BY PRICE OR NAME IN DESCENDING OR ASCENDING    " + BLUE + "    ┃");
            System.out.println("┃      " + GREEN + "7. BACK               " + BLUE + "                                     ┃");
            System.out.println("┃                                                                 ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.println("Your choice to 1 from 4: ");
            break;

        } while (true);
    }
}