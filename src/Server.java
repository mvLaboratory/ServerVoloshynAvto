import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class Server implements Runnable {
    private static Socket connection;
    private static ServerSocket server;
    private static ObjectOutputStream output;
    private static ObjectInputStream input;
    public static String outFileAdress;

    public static void main(String[] args) {
        if (args.length > 0) {
            outFileAdress = args[0];
        }
        new Thread(new Server()).start();
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(5678, 100);
            System.out.println("Server are running");
        } catch (Exception e) {
            System.out.println("coonection failed");
        }
        while (true) {
            try {
                connection = server.accept();
                output = new ObjectOutputStream(connection.getOutputStream());
                input = new ObjectInputStream(connection.getInputStream());
                String massage;
                if ((massage = (String) input.readObject()) != null) {
                    try {
                        FileConstructor fConstractor = new FileConstructor(1, 2, new UUID(222,2));
                        fConstractor.createDocument();
                    } catch (Exception xmlEx) {massage = "can't write xml to " + massage;}
                    output.writeObject("ping; " + massage);
                    System.out.println("send" + massage);
                }
            } catch (Exception e) {
                System.out.println("coonection failed");
            }
        }
    }
}
