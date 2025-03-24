/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTBASE.Database;

import java.sql.*;

/**
 *
 * @author Tam
 */
public class Database {
     public static Connection openConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://192.168.178.10:1433;databaseName=AIRLINE;encrypt=true;trustServerCertificate=true";
        String user = "sa";
        String password = "123456";
        Connection con = DriverManager.getConnection(url,user, password);
        return con;
    }
}
