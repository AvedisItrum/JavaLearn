package Practice;

import java.util.*;


public class StandAloneMethods {
    static Collection Distinct(Collection collection) {
        return collection.stream().distinct().toList();
    }

    static HashMap<Object, Integer> mapper(Object[] objs) {
        HashMap<Object, Integer> maps = new HashMap<>();
        Arrays.stream(objs).forEach(x -> {
            if (maps.containsKey(x))
                maps.put(x, maps.get(x) + 1);
            else
                maps.put(x, 1);
        });
        return maps;
    }
}
