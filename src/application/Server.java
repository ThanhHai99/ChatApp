package application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server { 

    public static int port = 1234;
    public static int numThread = 3;
    private static ServerSocket server = null;
    public static int sum = 0;
    public static Vector<Worker> workers = new Vector<>();

    public static void main(String[] args) throws IOException {
        ExecutorService executor = Executors.newFixedThreadPool(numThread);
        try {
            server = new ServerSocket(port);
            System.out.println("Server binding at port " + port);
            System.out.println("Waiting for client...");   
            while (true) {
                Socket socket = server.accept();
                Worker client = new Worker(socket);
                workers.add(client);
                executor.execute(client);
                sum++;
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (server != null) {
                server.close();
            }
        }
    }
}
