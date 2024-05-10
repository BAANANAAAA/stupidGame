package chat;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.112.1", 12345);
            System.out.println("Connected to server.");

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // 发送查询请求
            System.out.println("Enter your request (e.g., add 10 20): ");
            String request = userInput.readLine();
            out.println(request);

            // 接收服务器的响应并打印
            String response = in.readLine();
            System.out.println("Server response: " + response);

            // 关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}