package org.example.model;

import org.example.entities.Libro;
import org.example.entities.Socio;
import org.example.utilities.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {
    //SELECT
    private final String SQL_SELECT_LIBROS ="SELECT * FROM LIBROS;";
    private final String SQL_SELECT_SOCIOS = "SELECT * FROM SOCIOS;";
    private final String SQL_SELECT_LIBROS_NO_ALQUILADOS = "SELECT * FROM LIBROS WHERE ALQUILADO= 'false';";
    private final String SQL_SELECT_LIBROS_ALQUILADOS = "SELECT * FROM LIBROS WHERE LIBRO = 'true';";
    private final String SQL_SELECT_HISTORICO = "SELECT DNI_SOCIO,ISBN_LIBRO,FECHA_ALQUILER,FECHA_DEVOLUCION;";

    //INSERTS
    private final String SQL_INSERT_CREAR_LIBRO = "INSERT INTO ALQUILERES (ISBN,DNI,FECHA_ALQUILER,FECHA_DEVOLUCION) VALUES(?,?,?,?);";

    //CHECKS
    private final String SQL_CHECK_IF_SOCIO_EXIST = "SELECT DNI FROM SOCIOS WHERE DNI = ?;";
    private final String SQL_CHECK_IF_LIBRO_EXIST = "SELECT ISBN FROM LIBROS WHERE ISBN = ?;";
    private final String SQL_CHECK_IF_LIBRO_ALQUILADO = "SELECT * FROM LIBROS WHERE ISBN = ? AND ALQUILADO = FALSE;";

    //UPDATES
    private final String SQL_UPDATE_DEVOLUCION = "UPDATE LIBROS SET ALQUILADO = 'false';";
    private final String SQL_UPDATE_ALQUILER = "UPDATE LIBROS SET ALQUILADO='true';";

    //VARIABLES
    Connection conn;

    public Model(){
        conn = Conexion.getInstance();
    }

    public ArrayList<Socio> getListaSocios(){
        ArrayList<Socio> listaSocios = null;
        try {
            PreparedStatement ps = this.conn.prepareStatement(SQL_SELECT_SOCIOS);
            ResultSet rs = ps.executeQuery();
            listaSocios = new ArrayList<>();
            while (rs.next()){
                listaSocios.add(new Socio(rs.getString("DNI"), rs.getString("NOMBRE"),rs.getString("APELLIDOS")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return listaSocios;
    }

    /*
    * public ArrayList<Student> getStudentsList() {
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM student");
            ResultSet rs = ps.executeQuery();

            ArrayList<Student> listaEstudiantes = new ArrayList<Student>();
            while(rs.next()) {
                listaEstudiantes.add(new Student(rs.getString("id"), rs.getString("name"),
                        rs.getString("surname"),rs.getInt("age")));
            }

            return listaEstudiantes;

        } catch (SQLException e) {
            e.printStackTrace();
            closeConnection();
            return new ArrayList<Student>();
        }
    }*/


}
