package DataStorageLearn;

import java.io.*;

public class JsonDataHandler<T> implements DataHandler {
    final String path = "txts/saves.txt";

    @Override
    public boolean setData(Object data) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(data);

        objectOutputStream.close();

        System.out.println("Data saved");

        return true;
    }

    @Override
    public T getData() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        T data = (T) objectInputStream.readObject();

        System.out.println("Data loaded");

        return data;
    }
}
