package com.sm.news;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static Connection conn = null;
    
    static {
        String url = "jdbc:mysql://127.0.0.1:3306/capital_area_news";
        String user = "root";
        String passwd = "tiger";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, passwd);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    
    public static Connection getConnection() {
        return conn;
    }
}
