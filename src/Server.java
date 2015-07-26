import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Server side");
        BufferedReader in = null;
        PrintWriter out= null;
        ServerSocket servers = null;
        Socket fromClient = null;

        // create server socket
        try {
            servers = new ServerSocket(4444);
        } catch (IOException e) {
            System.out.println("Couldn't listen to port 4444");
            System.exit(-1);
        }

        boolean serverActive = true;

        //while (serverActive) {
            try {
                System.out.print("Waiting for a client...");
                fromClient = servers.accept();
                System.out.println("Client connected");
            } catch (IOException e) {
                System.out.println("Can't accept");
                System.exit(-1);
            }

            in = new BufferedReader(new
                    InputStreamReader(fromClient.getInputStream()));
            out = new PrintWriter(fromClient.getOutputStream(), true);
            String input, output;
            System.out.println("Wait for messages");

            //server+++
            String clientMessage;
            boolean serverAvailable = true;
//            while (serverAvailable) {
//                clientMessage = in.readLine();
//                if (clientMessage == null) {
//                    continue;
//                }
//                if (clientMessage.equalsIgnoreCase("check")) out.println("catch");
//                if (clientMessage.equalsIgnoreCase("exit")) serverAvailable = false;
//                System.out.println(clientMessage);
//            }
//
//            out.close();
//            in.close();
//            fromClient.close();
//            servers.close();

            try {
                Thread.sleep(2000);
                System.out.println("circle");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        //}
    }

}
