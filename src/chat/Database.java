package chat;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;

public class Database {

    private static final String url = "jdbc:postgresql://localhost/JavaGame";
    private static final String user = "postgres";
    private static final String password = "0311";
    public static final String PORT = "12345";

    // 单一连接，执行过程中仅使用该连接
    private static Connection connection = null;

    private static void connect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                System.err.println("Connect database failed" + e.getMessage());
            }
        }
    }

    private static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Disconnect database failed" + e.getMessage());
            } finally {
                connection = null;
            }
        }
    }

    public static String login(String username, String password) {
        connect(); // 获取连接或者新建连接
        String user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int uid = resultSet.getInt("uid");
                user = uid + " " + username + " " + password;
            }
        } catch (SQLException e) {
            System.err.println("User login failed" + e.getMessage());
        }
        return user;
    }

    public static String createRoom(int player1Uid) {
        // 创建房间，返回一个房间编号
        connect();
        String identification = null;
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            String ipAddress = localHost.getHostAddress();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO rooms (player1, status, ip_address) " +
                            "VALUES (?, 'open', ?) RETURNING identification");
            statement.setInt(1, player1Uid);
            statement.setString(2, ipAddress);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                identification = resultSet.getString("identification");
                System.out.println("try to get id " + identification);
            }
        } catch (SQLException | UnknownHostException e) {
            System.err.println("Room creation failed." + e.getMessage());
        }
        return identification;
    }

    public static String joinRoom(int player2Uid, String identification) {
        // 加入房间
        connect();
        String ipAddress = null;
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE rooms SET player2 = ? WHERE identification = ? " +
                            "RETURNING ip_address");
            statement.setInt(1, player2Uid);
            statement.setString(2, identification);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ipAddress = resultSet.getString("ip_address");
                System.out.println("Joined room successfully with IP address: " + ipAddress);
            } else {
                System.err.println("Room join failed: Room not found or identification incorrect");
            }
        } catch (SQLException e) {
            System.err.println("Room join failed" + e.getMessage());
        }
        return ipAddress;
    }

    public static void closeConnection() {
        disconnect();
    }
}