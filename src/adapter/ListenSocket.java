package adapter;

import java.io.BufferedReader;
import java.util.concurrent.ArrayBlockingQueue;

public class ListenSocket extends Thread {

    private BufferedReader reader;
    private ArrayBlockingQueue<String> buffer;

    public ListenSocket(BufferedReader reader) {
        this.reader = reader;
        buffer = new ArrayBlockingQueue<String>(2048);
    }

    public void run() {
        while(true) {
            try {
                buffer.add(reader.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getLastLine() {
        try {
            return buffer.take();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
