package org.example.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Connection connection;
    private final String usuario = "admin";
    private final String clave = "admin";
    private final String url ="jdbc:mysql://localhost:3306/biblioteca";

    // El constructor del singleton siempre debe ser privado para evitar llamadas de construcción directas con el operador `new`.
    private Conexion(){
        try {
            connection = DriverManager.getConnection(url, usuario, clave);
        }catch(SQLException sqle) {
            System.out.println("Error al abrir la conexión");
        }
    }

    // El método estático que controla el acceso a la instancia
    // singleton.
    public static Connection getInstance(){
        if (Conexion.connection == null)
            new Conexion();
        return Conexion.connection;
    }


}
