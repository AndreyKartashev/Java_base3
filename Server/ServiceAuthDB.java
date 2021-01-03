package server;

import java.sql.*;
import java.util.ArrayList;

public class ServiceAuthDB {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement psInsert;
    private static PreparedStatement psUpdate;
    private static PreparedStatement psSelect;
    private static PreparedStatement psDelete;

    public void ServiceAuthDB() throws Exception {
        connect("Main.db");
    }

    public void connect(String base) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + base);
        stmt = connection.createStatement();
        selectAll(SimpleAuthService.userlist);
    }

    public void disconnect() {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Распечатать всех пользователей
    public void selectAll(ArrayList<UserData> users) throws SQLException {
        psSelect.executeQuery("SELECT * FROM users;");
        ResultSet rs = psSelect.getResultSet();
       // ResultSet rs = stmt.executeQuery("SELECT * FROM users");
        while (rs.next()) {
            users.add(new UserData(rs.getString("login"), rs.getString("password"), rs.getString("nickname")));
            System.out.println("пользователь:");
            System.out.println(rs.getString("login") + " " + rs.getString("password") + " " + rs.getString("nickname"));
        }
        rs.close();
    }

    public void select(String loginName) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT login, password, nickname FROM users WHERE login = '" + loginName +"';");
        while (rs.next()) {
            System.out.println(rs.getString("login") + " " + rs.getString("password") + " " + rs.getString("nickname"));
        }
        rs.close();
    }

    public void update(String newNick, String loginName) throws SQLException {
        stmt.executeUpdate("UPDATE users (login, password, nickname) SET (nickname = '" + newNick + "') WHERE login = '" + loginName + "';");
    }

    public void insert(String loginName, String password, String nickName) throws SQLException {
        psInsert.executeQuery("INSERT INTO users (login, password, nickname) VALUES ('"+ loginName + "','" + password + "','" + nickName + "');");
    }

    public void clearTable() throws SQLException {
        stmt.executeUpdate("DELETE FROM students;");
    }

    public void delete(String loginName) throws SQLException {
        psDelete.executeQuery("DELETE FROM users WHERE login = '" + loginName + "';");
    }
}