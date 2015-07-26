import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private static Socket connection;
    private static ServerSocket server;
    private static ObjectOutputStream output;
    private static ObjectInputStream input;

    public static void main(String[] args) {
        new Thread(new Server()).start();
    }

    public Server() {
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
                //JOptionPane.showMessageDialog(null, (String) input.readObject());
                output.writeObject("ping; " + (String) input.readObject());
            } catch (Exception e) {
                System.out.println("coonection failed");
            }
        }
    }
}
