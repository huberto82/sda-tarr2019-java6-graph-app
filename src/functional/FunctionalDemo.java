package functional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

@FunctionalInterface
interface ArithmeticOperation {
    double  calc(double a, double b);

    default double sumWith(double a, double b, double fact){
        return fact + calc(a, b);
    }
}

class Addition implements ArithmeticOperation{
    @Override
    public double calc(double a, double b) {
        return a + b;
    }
}

class Box {
    private String content;

    public Box(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

class IntegerBox {
    private Integer content;

    public IntegerBox(Integer content) {
        this.content = content;
    }

    public Integer getContent() {
        return content;
    }
}
class UniversalBox<T> {
    private T content;

    public UniversalBox(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}

public class FunctionalDemo {
    public static void main(String[] args) {
        UniversalBox<String> stringUniversalBox = new UniversalBox<>("Ha");
        UniversalBox<Integer> integerUniversalBox = new UniversalBox<>(10);

        Addition addition = new Addition();
        System.out.println(addition.calc(12, 14));
        ArithmeticOperation operation = new ArithmeticOperation() {
            @Override
            public double calc(double a, double b) {
                return (1 + b) * a;
            }
        };
        System.out.println(operation.calc(54, 0.23));
        ArithmeticOperation lambda = (a, b) -> (1 + b) * a;
        System.out.println(lambda.calc(54, 0.23));
        List<ArithmeticOperation> operations = new ArrayList<>();
        operations.add(addition);
        operations.add(operation);
        operations.add(lambda);

        for(ArithmeticOperation o: operations){
            System.out.println(o.calc(4, 6));
        }

        //Gotowe  interfejsy funkcyjne
        LongFunction<Long> multiplyByTen = a -> a * 10;
        LongBinaryOperator multiply = (a, b) -> a * b;
        //Dwie poniższe lambdy wykonują to samo, ale są zapisane na dwa różne sposoby
        //Lambda wykorzystuje tylko wywołanie jednej metody, której przekazujemy wszsytkie argumenty lambdy
        Function<String, LocalDate> strToDate = str -> LocalDate.parse(str);
        //Lambda, w której wykorzystujemy referencję do metody, która jest całym ciałem lambdy i argumenty metody są jednocześnie
        //argumetanmi lambdy
        // :: - to symbol referencji metody
        Function<String, LocalDate> referenceMethod = LocalDate::parse;
        //Predykaty to funkcje jednoargumentowe zwracające tylko typ logiczny: true or false
        //Predykat będzie robił konwersje z int do Integer
        Predicate<Integer> isMature = age -> age >= 18;
        //Predykat działa tylko na typie int
        IntPredicate intIsMature = age -> age >= 18;
        IntPredicate isRetired = age -> age >= 65;
        int userAge = 56;
        //dwa różne sposoby testowania wieku producyjnego
        if (intIsMature.and(isRetired.negate()).test(userAge)){
            System.out.println("Wiek produkcyjny");
        }
        if (userAge >= 18 && userAge <= 65){
            System.out.println("Wiek produkcyjny");
        }
        //Dostawcy
        Supplier<String> name = () -> "Mark";
        //Konsument
        Consumer<String> print = (str) -> System.out.println(str);
    }
}
