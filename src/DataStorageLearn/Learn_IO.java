package DataStorageLearn;

import java.io.*;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Learn_IO {
    public static void main(String[] args) throws IOException {
        try {
            Practice();
        } catch (IOException e) {
            throw e;
        }
    }

    static void Practice() throws IOException {
        File f = new File("txts/Practice.txt");
        if (!f.exists())
            f.createNewFile();

        BufferedReader bR = new BufferedReader(new FileReader(f));
        String str = bR.lines().collect(Collectors.joining("\n"));
        bR.close();

        System.out.println(str);
        Scanner in = new Scanner(System.in);
        str += "\n" + in.nextLine().replace(' ', '_');

        BufferedWriter bW = new BufferedWriter(new FileWriter(f));
        bW.write(str);
        bW.close();
    }

    static void FileStreamExample() throws IOException {
        FileInputStream input1 = new FileInputStream("txts/saves.txt");
        input1.close();

        File saves = new File("txts/saves.txt");
        FileInputStream input2 = new FileInputStream(saves);
        input2.close();

        File mainDir = new File("src");
        System.out.println(mainDir.getAbsolutePath());
        for (var file : mainDir.listFiles())
            System.out.println(file.getName());

        FileOutputStream output = new FileOutputStream(saves);
        output.close();
    }

    static void ObjectStreamExample() throws IOException {
        String str = "";

        File file = new File("txts/TestIO.txt");
        if (file.exists()) {
            FileInputStream fIS = new FileInputStream("txts/TestIO.txt");
            ObjectInputStream oIS = new ObjectInputStream(fIS);

            str = oIS.readUTF();
            oIS.close();
        }
        str += " str";

        FileOutputStream fOS = new FileOutputStream("txts/TestIO.txt");
        ObjectOutputStream oOS = new ObjectOutputStream(fOS);
        oOS.writeUTF(str);
        oOS.close();
    }

    static void ReaderWriter() throws IOException {
        File f = new File("txts/TestReaderWriter.txt");

        if (!f.exists())
            f.createNewFile();

        FileReader fR = new FileReader(f);
        BufferedReader bFR = new BufferedReader(fR);

        String str = bFR.lines().collect(Collectors.joining("\n"));
        bFR.close();

        str += "\n" + LocalTime.now();

        FileWriter fW = new FileWriter(f);
        BufferedWriter bFW = new BufferedWriter(fW);

        bFW.write(str);
        bFW.close();
    }
}
