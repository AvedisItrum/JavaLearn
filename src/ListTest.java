import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTest {
    public static void main(String[] a) {
        List<Integer> ArrayTimes = new ArrayList<>();
        List<Integer> LinkedTimes = new ArrayList<>();

        long startTime;
        int i1 = 0, i2 = 0;
        for (int i = 0; i < 5; i++) {
          /*  ArrayList<Integer> AL = new ArrayList<>();
            startTime = System.currentTimeMillis();
            for (int A = 0; A < 1_000_000; A++) {
                AL.add((int) System.currentTimeMillis());
            }
            ArrayTimes.add((int) (System.currentTimeMillis() - startTime));
            i1 = AL.get(AL.size() / 2);*/
            LinkedList<Integer> LL = new LinkedList<>();
            startTime = System.currentTimeMillis();
            for (int L = 0; L < 1_000_000; L++) {
                LL.add(0,(int) System.currentTimeMillis());
            }

            LinkedTimes.add((int) (System.currentTimeMillis() - startTime));
            i2 = LL.get(LL.size() / 2);

        }
       // int Array_AVG = ArrayTimes.stream().reduce(Integer::sum).get() / ArrayTimes.size();
        int Linked_AVG = LinkedTimes.stream().reduce(Integer::sum).get() / LinkedTimes.size();

    //   System.out.println("ArrayList AVG = " + Array_AVG + "   " + i1);
        System.out.println("LinkedList AVG = " + Linked_AVG + "   " + i2);

     //   System.out.println("Linked>Array = " + (float) Linked_AVG / (float) Array_AVG * 100 + "%");

        /*
        Для 10 млн добавлений
        ArrayList AVG = 494 млс в среднем
        LinkedList AVG = 1100 млс в среднем
        Linked>Array = 222.67206%

        Для 50 млн добавлений
        ArrayList AVG = 3347
        LinkedList AVG = 8287
        Linked>Array = 247.59486%

*/
    }
}
