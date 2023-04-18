package DataStorageLearn;

import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public interface DataHandler<T> {

    boolean setData(T data) throws Exception;

    T getData() throws Exception;

    default String dataToString(T data) {
        return "Some string";
    }

    static int SomeRandomValue() {
        return new Random().nextInt();
    }

}
