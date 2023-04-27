package Practice;

import java.util.*;


public class StandAloneMethods {
    static Collection distinct(Collection collection) {
        return collection.stream().distinct().toList();
    }

    static HashMap<Object, Integer> mapper(Object[] objs) {
        HashMap<Object, Integer> map = new HashMap<>();
        Arrays.stream(objs).forEach(x -> map.put(x, map.getOrDefault(x, 0) + 1));
        return map;
    }
}
