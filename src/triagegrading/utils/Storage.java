package triagegrading.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {

    private static final String FILE_NAME = "src/TriageData";

    // the file stream has been closed, TryWithResources makes the code a bit unreadable
    @SuppressWarnings({"ConvertToTryWithResources"})
    public static void save(Object data) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        objectOutputStream.writeObject(data);
        objectOutputStream.close();
    }
    
    // the file stream has been closed, TryWithResources makes the code a bit unreadable
    @SuppressWarnings("ConvertToTryWithResources")
    public static Object read() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_NAME));
        Object data = objectInputStream.readObject();
        objectInputStream.close();
        return data;
    }

    public static boolean clearData() {
        File file = new File(FILE_NAME);
        return file.delete();
    }
}
