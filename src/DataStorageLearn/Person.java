package DataStorageLearn;

import java.io.Serializable;
import java.util.Optional;

public class Person implements Comparable<Person>, Serializable {
    String name;
    int age;

    public Optional<String> getName(){
     return Optional.ofNullable(name);
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person person) || obj == null || this == null) return false;

        return name.equals(person.name) && age == person.age;
    }

    @Override
    public int hashCode() {
        int nameResult = name != null ? name.hashCode() : 0;
        return 31 * nameResult + age;
    }

    //Сортировка по имени и возрасту
    @Override
    public int compareTo(Person other) {
        int result = 0;

        char[] A = name.toCharArray();
        char[] B = other.name.toCharArray();

        int l = Math.min(A.length, B.length);
        for (int i = 0; i < l; i++) {
            result = A[i] - B[i];
            if (result != 0) break;
        }
        if (result == 0) result = A.length - B.length;
        if (result == 0) result = age - other.age;
        return result < 0 ? -1 : result == 0 ? 0 : 1;
    }

    /*
    Сортировка по возрасту
    @Override
    public int compareTo(Person other){
        return Integer.compare(age,other.age);
    }*/
    @Override
    public String toString() {
        return name + " " + age;
    }
}

