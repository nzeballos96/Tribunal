package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Conect {

    String base = "elecciones_sistema";
    String url = "jdbc:mysql://localhost:3306/";
    String usuario = "root";
    String contraseña = "";
    String driver = "com.mysql.cj.jdbc.Driver";

    public Conect() {
    }

    

    Connection conect = null;

    public Connection conexion() {
        try {
            Class.forName(driver);
            conect = DriverManager.getConnection(url + base, usuario, contraseña);
           // System.out.println("CONECTADO");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e);
            System.out.println("ERROR");
        }

        return conect;
    }

    public void cerrar() {
        try {
            conect.close();
         //   System.out.println("CONEXIÓN CERRADA");
        } catch (Exception e) {
            System.out.println("ERROR AL CERRAR LA CONEXIÓN");
        }
    }
	
}
