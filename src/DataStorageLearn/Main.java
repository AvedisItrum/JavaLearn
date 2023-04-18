package DataStorageLearn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person P1 = new Person("DGuy1", 19);
        Person P2 = new Person("DGuy11", 19);
        Person P3 = new Person("AGuy2", 18);
        Person P4 = new Person("AGuy3", 21);
        Person P5 = new Person("AGuy3", 20);

        List<Person> people =Arrays.stream(new Person[]{P1, P2, P3, P4, P5}).sorted().toList();

        DataHandler<Person[]> Saver = new JsonDataHandler<>();

        try {
            Saver.setData(people.toArray(Person[]::new));
        } catch (Exception e) {
            System.out.println("ERROR:" + e.getMessage());
        }

        try {
            Arrays.stream(Saver.getData()).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("ERROR:" + e.getMessage());
        }

    }


}
