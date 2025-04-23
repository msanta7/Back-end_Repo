package com.example.gestiontp;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    public static Connection connectDB()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect= DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db?useSSL=false","root","45689001h");
            return connect;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}