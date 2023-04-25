package MultyThreading;

public class MyThread extends Thread {
    //Interruption example
    @Override
    public void run() {
        //DoSomeWork
        System.out.println("Doing some work");

        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
            return;
        }

        System.out.println("Done some work");
    }
}
