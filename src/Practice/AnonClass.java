package Practice;

import java.util.ArrayList;
import java.util.List;

public class AnonClass {
    public static int exampleStaticInt = 10;

    public static void Main(String[] args) {
        Logger LogInt = new Logger() {
            @Override
            public void DoSomeLog(Object obj) {
                System.out.println(obj.toString());
            }

            @Override
            public void DontDoLog(Object obj) {

            }
        };
        Logger CheckInt = new Logger() {
            @Override
            public void DoSomeLog(Object obj) {
                System.out.println(obj.toString());
            }

            @Override
            public void DontDoLog(Object obj) {

            }
            public void SomeMethod(){
                exampleStaticInt++;
            }
        };

        int exampleInt = 10;
        List<Integer> listInts = new ArrayList<>();

        listInts.forEach(LogInt::DoSomeLog);
        listInts.forEach(CheckInt::DontDoLog);

        FuncLog myFuncLog = x->System.out.println("Выведено с помощью функционального интерфейса: "+x.toString());

        listInts.forEach(myFuncLog::Log);
    }
}
interface Logger {
    void DoSomeLog(Object obj);
    void DontDoLog(Object obj);
}
@FunctionalInterface
interface FuncLog{
    void Log(Object obg);

    static Boolean inNotNull(Object obj){
        return obj!=null;
    }
}
