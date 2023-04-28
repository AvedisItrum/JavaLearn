package MultyThreading.Practice;

import org.junit.platform.engine.support.hierarchical.ThrowableCollector;

import java.io.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.stream.Collectors;

public class MyReadWriteLock /*implements ReadWriteLock*/ {
    private File file = new File("MyReadWriteLock.txt");

    private AtomicBoolean isReading = new AtomicBoolean(false);
    private AtomicBoolean isWriting = new AtomicBoolean(false);
    private AtomicBoolean isWaitingForReading = new AtomicBoolean(false);
    private AtomicBoolean isWaitingForWriting = new AtomicBoolean(false);

    String read() {
        while (isWaitingForWriting.get())
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                return "";
            }

        while (isWriting.get()) {
            isWaitingForReading.set(true);
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                isWaitingForReading.set(false);
                return "";

            }
        }
        isWaitingForReading.set(false);
        isReading.set(true);

        String str;

        try {
            FileReader fR = new FileReader(file);
            BufferedReader reader = new BufferedReader(fR);

            str = reader.lines().collect(Collectors.joining("\n"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        isReading.set(false);
        return str;
    }

    public void write(String str) {
        while (isWaitingForReading.get())
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                return;
            }

        while (isReading.get()) {
            isWaitingForWriting.set(true);
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                isWaitingForWriting.set(false);
                return;
            }
        }

        isWaitingForWriting.set(false);
        isWriting.set(true);

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(str); // Записываем строку в файл, добавляя пробелы
            fileWriter.flush(); // Очищаем буфер потока
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        isWriting.set(false);
    }
}
