import strategies.FileStrategy;
import strategies.NoDoubleException;
import strategies.RandomStrategy;
import strategies.StdInStrategy;

import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println();

        FileStrategy fileStrategy = new FileStrategy();

        System.out.println("Witaj!");
        boolean flag = false;
        do {
            System.out.println("Podaj typ wejścia: (1-random, 2-stdin, 3-plik, k-koniec)");
            InputReader reader = new InputReader();

            Scanner sc = new Scanner(System.in);

            String type = sc.next();
            if (type.equals("1")) {
                reader.setStrategy(new RandomStrategy());
            } else if (type.equals("2")) {
                reader.setStrategy(new StdInStrategy());
            } else if (type.equals("3")) {
                reader.setStrategy(fileStrategy);
            } else if (type.equals("k")) {
                flag = true;
                System.exit(0);
            }

            int counterInt = fileStrategy.getCounterInt();
            int counterString = fileStrategy.getCounterString();
            char znak;
            boolean flag2 = false;
            while (!flag2) {
                System.out.println("Wybierz: i(getInt), s(getString), d(getDouble), k(Koniec):");
                znak = sc.next().charAt(0);
                if (znak == 'i') {
                    fileStrategy.setCounterInt(counterInt);
                    System.out.println(reader.requestInt());
                    counterInt++;
                    int rozmiarListyInt = fileStrategy.getIntegerListSize();
                    if (counterInt == rozmiarListyInt) {
                        counterInt = 0;
                    }
                } else if (znak == 's') {
                    fileStrategy.setCounterString(counterString);
                    System.out.println(reader.requestString());
                    counterString++;
                    int rozmiarListyString = fileStrategy.getListStringSize();
                    if (counterString == rozmiarListyString) {
                        counterString = 0;
                    }
                } else if (znak == 'd') {
                    try {
                        System.out.println(reader.requestDouble());
                    } catch (NoDoubleException e) {
                        System.err.println("Brak danych double.");
                    }
                } else if (znak == 'k') {
                    flag2 = true;
                }
//                else if (command.equals("setstrategy")) {
//                    type = sc.next();
//                    if (type.equals("random")) {
//                        reader.setStrategy(new RandomStrategy());
//                    } else if (type.equals("stdin")) {
//                        reader.setStrategy(new StdInStrategy());
//                    } else if (type.equals("file")) {
//                        reader.setStrategy(new FileStrategy());
//                    }
//                }
            }
        } while (!flag);
        System.out.println("Koniec pracy programu.");
    }
}
