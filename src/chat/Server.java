package chat;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Listening on port 12345");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                out.println(processRequest(in.readLine()));

                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String processRequest(String request) {
            // Input: String, "method arg1 arg2"
            String[] parts = request.split(" ");
            String method = parts[0];

            switch (method) {
                case "login" -> {
                    return Database.login(parts[1], parts[2]);
                }
                case "createRoom" -> {
                    return Database.createRoom(Integer.parseInt(parts[1]));
                }
                case "createUse" -> {
                    return Database.joinRoom(Integer.parseInt(parts[1]), parts[2]);
                }
            }
            return "No match method.";
        }
    }
}