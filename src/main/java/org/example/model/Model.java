package org.example.model;

import org.example.entities.Alquiler;
import org.example.entities.Libro;
import org.example.entities.Socio;
import org.example.utilities.Conexion;

import javax.swing.*;
import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    //SELECT
    private final String SQL_SELECT_SOCIOS = "SELECT * FROM SOCIOS;";
    private final String SQL_SELECT_LIBROS_DISPONIBLES = "SELECT * FROM LIBROS WHERE ALQUILADO= 0;";
    private final String SQL_SELECT_LIBROS_ALQUILADOS = "SELECT * FROM LIBROS WHERE ALQUILADO = 1;";
    private final String SQL_SELECT_HISTORICO = "SELECT DNI_SOCIO,ISBN_LIBRO,FECHA_ALQUILER,FECHA_DEVOLUCION FROM alquileres;";

    //INSERTS
    private final String SQL_INSERT_CREAR_ALQUILER = "INSERT INTO ALQUILERES (ISBN_LIBRO,DNI_SOCIO,FECHA_ALQUILER,FECHA_DEVOLUCION) VALUES(?,?,CURDATE(),date_add(now(), interval 15 day));";

    //CHECKS
    private final String SQL_CHECK_IF_LIBRO_EXIST = "SELECT ISBN FROM LIBROS WHERE ISBN = ? AND ALQUILADO = 1;";

    //UPDATES
    private final String SQL_UPDATE_ALQUILER = "UPDATE LIBROS SET ALQUILADO=? WHERE ISBN = ? ;";


    private final String SQL_SELECT_ID_ALQUILER = "SELECT MAX(ID_ALQUILER) ID FROM ALQUILERES AL INNER JOIN LIBROS LI ON AL.ISBN_LIBRO = LI.ISBN WHERE LI.ALQUILADO = 1 AND ISBN = ?;";
    private final String SQL_UPDATE_DEVOLUCION = "UPDATE ALQUILERES SET FECHA_DEVOLUCION = curdate() WHERE ID_ALQUILER = ?;";

    //VARIABLES
    Connection conn;

    public Model(){
        conn = Conexion.getInstance();
    }

    /**
     * //Devuelve la lista de socios registrados
     * @return ArrayList<Socio>
     */
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

    /**
     * Devuelve la lista de libros que NO ESTAN ALQUILADOS
     * @return ArrayList<Libro>
     */
   public ArrayList<Libro> getLibrosDisponibles(){
       ArrayList<Libro> listaLibros = null;
       try {
           PreparedStatement ps = this.conn.prepareStatement(SQL_SELECT_LIBROS_DISPONIBLES);
           ResultSet rs = ps.executeQuery();
           listaLibros = new ArrayList<>();
           while (rs.next()){
               listaLibros.add(new Libro(rs.getString("ISBN"), rs.getString("TITULO"),rs.getString("AUTOR")));
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return listaLibros;
   }

    /**
     * Devuelve la lista de libros que  ESTAN ALQUILADOS
     * @return ArrayList<Libro>
     */
    public ArrayList<Libro> getLibrosAlquilados(){
        ArrayList<Libro> listaLibros = null;
        try {
            PreparedStatement ps = this.conn.prepareStatement(SQL_SELECT_LIBROS_ALQUILADOS);
            ResultSet rs = ps.executeQuery();
            listaLibros = new ArrayList<>();
            while (rs.next()){
                listaLibros.add(new Libro(rs.getString("ISBN"), rs.getString("TITULO"),rs.getString("AUTOR")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaLibros;
    }

    /**
     * Método para alquilar un libro
     * @param isbn código del libro
     * @param dni dni del socio
     */
    public void alquilarLibro(String isbn, String dni){
        PreparedStatement ps;
        try {
            if (!socioExists(dni)){
                JOptionPane.showMessageDialog(null, "El DNI del socio no es correcto", "Erro al introducir el DNI", JOptionPane.WARNING_MESSAGE);
            }if (!libroExists(isbn)){
                JOptionPane.showMessageDialog(null, "El ISBN del libro no existe o ya esta alquilado", "Error al introducir el ISBN", JOptionPane.WARNING_MESSAGE);
            }if (libroExists(isbn) && socioExists(dni)){
                ps = this.conn.prepareStatement(SQL_INSERT_CREAR_ALQUILER);
                ps.setString(1,isbn);
                ps.setString(2,dni);
                ps.setString(1,isbn);
                ps.setString(1,isbn);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Enorabuena, has alquilado el libro, tienes 15 días para devolverlo", "Libro alquilado", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Comprueba si el socio existe
     * @param dni dni del socio
     * @return true | false dependiendo el dni del socio existe
     */
    public boolean socioExists(String dni){
    ArrayList<Socio> socios = getListaSocios();
        for (Socio s : socios) {
            if (s.getDni().equals(dni)){
                return true;
            }
        }
        return false;
    }

    /**
     * Comprueba si el libro existe y no está alquilado
     * @param isbn código del libro
     * @return true | false dependiendo el código del libro existe
     */
    public boolean libroExists(String isbn){
        ArrayList<Libro> libros = getLibrosDisponibles();
        for (Libro l : libros) {
            if (l.getIsbn().equals(isbn)){
                return true;
            }
        }
        return false;
    }

    /**
     * Método para devolver un libro
     * @param isbn código del libro
     */
    public void devolverLibro(String isbn){
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (checkIfLibroAlquiladoExists(isbn)){
            try {
                ps = conn.prepareStatement(SQL_SELECT_ID_ALQUILER);
                ps.setString(1,isbn);
                rs = ps.executeQuery();
                int idAlquiler = 0;
                while (rs.next()){
                    idAlquiler = rs.getInt("ID");
                }
                ps = conn.prepareStatement(SQL_UPDATE_DEVOLUCION);
                ps.setInt(1,idAlquiler);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "El libro con isbn: " + isbn + " se ha devuelto correctamente", "El libro con isbn: " + isbn + " se ha devuelto correctamente", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            setDevuelto(isbn);

        }else JOptionPane.showMessageDialog(null, "El libro con isbn: " + isbn + " no esta alquilado o el isbn es incorrecto", "El libro con isbn: " + isbn + " no esta alquilado o el isbn es incorrecto", JOptionPane.WARNING_MESSAGE);

    }

    /**
     * Pone el campo "ALQUILADO" de la tabla libros a true
     * @param isbn
     */
    public void setAlquilado(String isbn){
        PreparedStatement ps = null;
        try {
            ps = this.conn.prepareStatement(SQL_UPDATE_ALQUILER);
            ps.setBoolean(1,true);
            ps.setString(2,isbn);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pone el campo "ALQUILADO" de la tabla libros a false
     * @param isbn
     */
    public void setDevuelto(String isbn){
        PreparedStatement ps = null;
        try {
            ps = this.conn.prepareStatement(SQL_UPDATE_ALQUILER);
            ps.setBoolean(1,false);
            ps.setString(2,isbn);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Devuelve un histórico de los alquileres de los libros
     * @return ArrayList<Alquiler>
     */

    public ArrayList<Alquiler> getHistorico(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Alquiler> alquileres = null;
        try {
            ps = this.conn.prepareStatement(SQL_SELECT_HISTORICO);
            rs = ps.executeQuery();

            alquileres = new ArrayList<>();
            Alquiler alquiler = null;
            while (rs.next()){
                alquiler = new Alquiler(rs.getString("ISBN_LIBRO"),rs.getString("DNI_SOCIO"),rs.getString("FECHA_ALQUILER"),rs.getString("FECHA_DEVOLUCION"));
                alquileres.add(alquiler);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alquileres;
    }


    /**
     * Comprueba si un libro ALQUILADO existe
     * @param isbn
     * @return true | false dependiendo si el libro EXISTE y esta ALQUILADO
     */
    public boolean checkIfLibroAlquiladoExists(String isbn){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = this.conn.prepareStatement(SQL_CHECK_IF_LIBRO_EXIST);
            ps.setString(1,isbn);
            rs = ps.executeQuery();
            while (rs.next()){
                if (rs.getString("ISBN").equals(isbn)){
                    System.out.println(rs.getString("ISBN"));
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
