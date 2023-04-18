import java.sql.Timestamp;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class Task {

    public static void main(String[] arg) {
        LinkedList<Map<Timestamp, Integer>> bigs = new LinkedList<>();
        Random rn = new Random();
        Iterator itr = bigs.listIterator(0);
        for (int i = 0; i < 10000; i++) {
            Integer val = rn.nextInt(0, 1000000);

        }
    }


}
