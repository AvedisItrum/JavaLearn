import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTest {
    public static void main(String[] a) {
        List<Integer> ALTimes = new ArrayList<>();
        List<Integer> LLTimes = new ArrayList<>();

        long startTime;

        for (int i = 0; i < 5; i++) {
            ArrayList<Integer> AL = new ArrayList<>();
            startTime = System.currentTimeMillis();
            for (Integer A = 0; A < 50_000_000; A++) {
                AL.add(i * A);
            }
            ALTimes.add((int) (System.currentTimeMillis() - startTime));

            LinkedList<Integer> LL = new LinkedList<>();
            startTime = System.currentTimeMillis();
            for (Integer L = 0; L < 50_000_000; L++) {
                LL.add(i * L);
            }
            LLTimes.add((int) (System.currentTimeMillis() - startTime));
        }
        int AL_AVG = ALTimes.stream().reduce(Integer::sum).get() / ALTimes.size();
        int LL_AVG = LLTimes.stream().reduce(Integer::sum).get() / LLTimes.size();

        System.out.println("ArrayList AVG = " + AL_AVG);
        System.out.println("LinkedList AVG = " + LL_AVG);

        System.out.println((float) AL_AVG / (float) LL_AVG * 100 + "%");

        /*
        Для 10 млн добавлений
        ArrayList AVG = 551 млс в среднем
        LinkedList AVG = 1188 млс в среднем
        Разница 46.38047%

        Для 50 млн добавлений
        ArrayList AVG = 2342
        LinkedList AVG = 6917
        33.85861%

*/
    }
}
