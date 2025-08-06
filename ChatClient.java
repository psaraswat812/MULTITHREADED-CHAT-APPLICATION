// package JavaTasks.Task3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

// Simple chat client that sends/receives messages from the server
public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
        ) {
            System.out.println("✅ Connected to chat server.");
            System.out.print("🧑 Enter your name: ");
            String name = scanner.nextLine();

            // Thread to listen for messages from server
            Thread readerThread = new Thread(() -> {
                try {
                    String incoming;
                    while ((incoming = in.readLine()) != null) {
                        System.out.println(incoming);
                    }
                } catch (IOException e) {
                    System.out.println("❌ Connection closed.");
                }
            });
            readerThread.start();

            // Send messages to server
            while (true) {
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                out.println("[" + name + "]: " + message);
            }

        } catch (IOException e) {
            System.out.println("❌ Client error: " + e.getMessage());
        }
    }
}
