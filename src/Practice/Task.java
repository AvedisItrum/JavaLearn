package Practice;

import java.sql.Time;
import java.time.LocalTime;
import java.util.*;

public class Task {
    public static void main(String[] args) {

       Testing t = new Testing();
       t.GenerateData();
       t.Sort();
    }

    static void Test(){
        //2.5 - 3 сек на генерацию 10млн значений.
        //7 - 7.5 сек на выборку из 10млн 100к в отсортированном виде
    }

    static class Testing{
        Time sortTime = Time.valueOf(LocalTime.now().minusHours(12));

        final Random random = new Random();
        List<Data> datas = new ArrayList<>();

       public void GenerateData(){
            for (int i = 0; i < 10_000_000; i++)
                datas.add(new Data(i%2 == 0?Time.valueOf(LocalTime.now()):Time.valueOf(LocalTime.now().minusHours(24)), random.nextInt()));

        }

        public  void Sort(){
            Data[] d = datas.stream()
                    .filter(x -> x.time.after(sortTime))
                    .sorted((x, y) -> Integer.compare(y.value, x.value))
                    .limit(100_000)
                    .toArray(Data[]::new);
        }
    }

    static class Data {
        Time time;
        int value;

        public Data(Time time, int val) {
            this.time = time;
            value = val;
        }

    }
}
