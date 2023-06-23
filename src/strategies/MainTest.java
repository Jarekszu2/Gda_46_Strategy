package strategies;

import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        System.out.println();

        System.out.println("stringList");
        FileStrategy fileStrategy = new FileStrategy();
        List<String> stringList = fileStrategy.fromFile();
        stringList
                .forEach(System.out::println);

        System.out.println();
        System.out.println("integerList");
        List<Integer> integerList = fileStrategy.getListInt();
        integerList
                .forEach(System.out::println);

        System.out.println();
        System.out.println("doubleList");
        List<Double> doubleList = fileStrategy.getListDouble();
        doubleList
                .forEach(System.out::println);

        System.out.println();
        System.out.println("stringList");
        List<String> stringListWithOutNumbers = fileStrategy.getListString();
        stringListWithOutNumbers
                .forEach(System.out::println);
    }
}
