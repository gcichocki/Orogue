package adapter;

import java.io.BufferedReader;
import java.util.concurrent.ArrayBlockingQueue;

public class ListenSocket extends Thread {

    private BufferedReader reader;
    private ArrayBlockingQueue<String> buffer;

    public ListenSocket(BufferedReader reader) {
        this.reader = reader;
        buffer = new ArrayBlockingQueue<String>(20048);
        this.start();
    }

    public void run() {
        int cpt = 0;
        while(true) {
            try {
                String tmp = reader.readLine();
                cpt++;
                buffer.add(tmp);
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
