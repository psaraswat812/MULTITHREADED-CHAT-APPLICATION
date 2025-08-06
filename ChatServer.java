// package JavaTasks.Task3;

import java.io.*;
import java.net.*;
import java.util.*;

// This class handles all the connected clients and manages broadcasting
public class ChatServer {
    private static final int PORT = 1234;  // Port number to listen on
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("✅ Chat server started... Waiting for clients...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // Accept a new client
                Socket clientSocket = serverSocket.accept();
                System.out.println("🔗 New client connected: " + clientSocket);
                // Start a new thread to handle this client
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.out.println("❌ Server error: " + e.getMessage());
        }
    }

    // Inner class to handle each connected client using a thread
    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
                )
            ) {
                out = new PrintWriter(socket.getOutputStream(), true);
                synchronized (clientWriters) {
                    clientWriters.add(out); // Save writer to broadcast later
                }

                String message;
                // Keep reading messages from the client
                while ((message = in.readLine()) != null) {
                    System.out.println("📩 Received: " + message);
                    // Broadcast to all connected clients
                    synchronized (clientWriters) {
                        for (PrintWriter writer : clientWriters) {
                            writer.println(message);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("⚠️ Connection lost: " + e.getMessage());
            } finally {
                if (out != null) {
                    synchronized (clientWriters) {
                        clientWriters.remove(out); // Remove client on disconnect
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    // Do nothing
                }
            }
        }
    }
}
