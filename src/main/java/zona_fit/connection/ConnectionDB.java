/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zona_fit.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author m_fer
 */
public class ConnectionDB {

    private static final String DBNAME = "defaultdb";
    private static final String DBURL = "jdbc:mysql://mysql-1b4b6b11-java-zonafit.c.aivencloud.com:25204/" + DBNAME + "?useSSL=true&requireSSL=true";
    //El proveedor AIVEN requiere conexiones seguras.
    private static final String DBUSER = "avnadmin";
    private static final String DBPASSWORD = "AVNS_Vl2Zqgq3F_ZMWd_jRyz";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);
            //System.out.println("Conexiòn exitosa a la bd");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        
        return conn;

    }
    
    /*public static void main(String[] args) {
        var connection = ConnectionDB.getConnection();
        if(connection != null){
            System.out.println("Conexiòn exitosa a la bbdd: " + connection);
        } else {
            System.out.println("Error al conectarse");
        }
    }*/
}
