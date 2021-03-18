package com.example.Blog.dao;

import com.example.Blog.entity.User;

import java.sql.*;

public class DbManager {

    private static Connection connection;
    private static final String GET_USER_BY_EMAIL = "select * from users where email = ?";
    private static final String URL = "jdbc:sqlite:C:\\Users\\АбдулК\\Desktop\\Blog\\identifier.sqlite";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User getUserByEmail(String email) throws SQLException {
        User user = null;

        PreparedStatement statement =  connection.prepareStatement(GET_USER_BY_EMAIL);
        statement.setString(1,email);
        ResultSet set = statement.executeQuery();

        if(set.next())
        {
          user = new User (
                    set.getLong(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4));
        }
        set.close();
        statement.close();


        return user;


    }
}
