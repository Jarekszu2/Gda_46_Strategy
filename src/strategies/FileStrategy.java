package strategies;

import lombok.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by amen on 8/17/17.
 */
@Data
public class FileStrategy implements IInputStrategy {
    private int counterInt;
    private int counterDouble;
    private int counterString;

    public FileStrategy() {
        this.counterInt = 0;
        this.counterDouble = 0;
        this.counterString = 0;
    }

    public List<String> fromFile() {
        // wczytuję dane z pliku (oddzielam linie znakiem'-X-')
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
//            reader.lines().forEach(stringBuilder::append);
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("-X-");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Błąd pliku.");
        } catch (IOException e) {
            System.err.println("Błąd wejścia-wyjścia.");
        }
        String fromFile = stringBuilder.toString();
//        System.out.println("fromFile" + fromFile);

        // tworzę tablicę stringów ze Stringa fromFile (regex po wprowadzonym dodatku ' X ')
        String[] tabWithSpaces = fromFile.split("-X-");
//        Arrays.toString(tabWithSpaces);

        // przekształcam tablicę String'ów w listę (element listy może zawierać spacje)
        List<String> listWithSpaces = Arrays.asList(tabWithSpaces);
//        System.out.println(listWithSpaces);

        // przekształcam elementy listy ne części bez spacji
        List<String> listWithOutSpaces = new ArrayList<>();

        for (int i = 0; i < listWithSpaces.size(); i++) {
            String[] tabWithOutSpaces = listWithSpaces.get(i).trim().split(" ");
            listWithOutSpaces.addAll(Arrays.asList(tabWithOutSpaces));
        }
//        System.out.println(listWithOutSpaces);
        return listWithOutSpaces;
    }

    // z danych z pliku wybieram int'y
    public List<Integer> getListInt() {
        List<Integer> integerList = new ArrayList<>();

        List<String> daneZPliku = fromFile();
        for (int i = 0; i < daneZPliku.size(); i++) {
            try {
                int liczba = Integer.parseInt(daneZPliku.get(i));
                integerList.add(liczba);
            } catch (NumberFormatException e) {
//                continue;
            }
        }
        return integerList;
    }

    public int getIntegerListSize() {
        List<Integer> integerList = getListInt();
        return integerList.size();
    }

    // z danych z pliku wybieram double
    public List<Double> getListDouble() {
        List<Double> doubleList = new ArrayList<>();
        List<String> listWithOutInt = new ArrayList<>();

        // odsiewam z wszystkich danych int'y
        List<String> daneZPliku = fromFile();
        for (int i = 0; i < daneZPliku.size(); i++) {
            try {
                int liczba = Integer.parseInt(daneZPliku.get(i));
            } catch (NumberFormatException e) {
                listWithOutInt.add(daneZPliku.get(i));
            }
        }

        // wyszukuję w reszcie double
        for (int i = 0; i < listWithOutInt.size(); i++) {
            try {
                double liczba = Double.parseDouble(listWithOutInt.get(i));
                doubleList.add(liczba);
            } catch (NumberFormatException e) {
//                continue;
            }
        }
        return doubleList;
    }

    // z danych z pliku wybieram String'i
    public List<String> getListString() {
        List<String> listStringWithOutNumbers = new ArrayList<>();

        // odsiewam z wszystkich danych liczby
        List<String> daneZPliku = fromFile();
        for (int i = 0; i < daneZPliku.size(); i++) {
            try {
                double liczba = Double.parseDouble(daneZPliku.get(i));
            } catch (NumberFormatException e) {
                listStringWithOutNumbers.add(daneZPliku.get(i));
            }
        }

        return listStringWithOutNumbers;
    }

    public int getListStringSize() {
        List<String> list = getListString();
        return list.size();
    }

    @Override
    public int getInt() {
        List<Integer> integerList = getListInt();
        int counter = getCounterInt();
//        int wyswietlanaLiczba = integerList.get(counter);
//        counter++;
//        if (counter == (integerList.size())) {
//            counter = 0;
//        }
//        return wyswietlanaLiczba;
        return integerList.get(counter);
    }

    @Override
    public String getString() {
        List<String> stringList = getListString();
        int counter = getCounterString();
//        String wyswietlanyString = stringList.get(counter);
//        return wyswietlanyString;
        return stringList.get(counter);
    }

    @Override
    public double getDouble() throws NoDoubleException {
        List<Double> doubleList = getListDouble();
        if (doubleList.size() > 0) {
            return doubleList.get(0);
        } else {
            throw new NoDoubleException("Brak danych double.");
        }
    }
}
