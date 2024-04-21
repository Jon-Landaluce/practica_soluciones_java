package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Utils {
    public Connection getConnection (String path) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", path));
            System.out.println("Conexion establecida");
        } catch (SQLException error) {
            System.out.println("Error en la conexion a la base de datos");
            error.printStackTrace();
        }
        return conn;
    }
    
}
