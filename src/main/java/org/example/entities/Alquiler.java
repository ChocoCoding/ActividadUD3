package org.example.entities;

import java.util.Date;

public class Alquiler {
    private int idAlquiler;
    private String isbn;
    private String DNI;
    private String fechaAlquiler;
    private String fechaDevolucion;

    public Alquiler(int idAlquiler, String isbn, String DNI, String fechaAlquiler, String fechaDevolucion) {
        this.idAlquiler = idAlquiler;
        this.isbn = isbn;
        this.DNI = DNI;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Alquiler(String isbn, String DNI, String fechaAlquiler, String fechaDevolucion) {
        this.isbn = isbn;
        this.DNI = DNI;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(String fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}
