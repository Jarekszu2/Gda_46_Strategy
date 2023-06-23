package strategies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by amen on 8/17/17.
 * Scanner sc = new Scanner(System.in); (std.in)
 */
public class StdInStrategy implements IInputStrategy {
    Scanner scanner = new Scanner(System.in);

    public StdInStrategy() {
    }

    @Override
    public int getInt() {
        int number = 0;
        boolean flag = false;
        do {
            System.out.println("Podaj liczbę całkowitą:");
            String s = scanner.nextLine();
            try {
                number = Integer.valueOf(s);
                flag = true;
            } catch (NumberFormatException e) {
                System.err.println("To nie jest liczba całkowita!");
            }
        } while (!flag);

        return number;
    }

    @Override
    public String getString() {
        System.out.println("Wprowadź tekst:");
        return scanner.nextLine();
    }

    @Override
    public double getDouble() {
        double number = 0;
        boolean flag = false;
        do {
            System.out.println("Podaj liczbę rzeczywistą:");
            String s = scanner.nextLine();
            try {
                number = Double.valueOf(s);
                flag = true;
            } catch (NumberFormatException e) {
                System.err.println("To nie jest liczba!");
            }
        } while (!flag);

        return number;
    }
}
