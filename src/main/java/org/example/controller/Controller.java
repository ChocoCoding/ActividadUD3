package org.example.controller;

import org.example.entities.Socio;
import org.example.model.Model;
import org.example.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller implements ActionListener {

    private Model model;
    private VentanaPrincipal vPrincipal;
    private VentanaVerSocios vSocios;
    private VentanaAlquilarLibro vAlqLibros;
    private VentanaLibrosDisponibles vLibrosDisp;
    private VentanaDevolverLibro vDevLib;
    private VentanaLibrosAlquilados vLibAlquilados;
    private VentanaVerHistorico vHistorico;


    public Controller(Model model, VentanaPrincipal vPrincipal){
        this.model = model;
        this.vPrincipal = vPrincipal;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("VER_SOCIOS")){
            vSocios = new VentanaVerSocios();
            vSocios.setVisible(true);
            vSocios.actualizarDatos(model.getListaSocios());
        }if (e.getActionCommand().equals("LIBROS_DISPONIBLES")){
            vLibrosDisp = new VentanaLibrosDisponibles();
            vLibrosDisp.setVisible(true);
            vLibrosDisp.actualizarDatos(model.getLibrosDisponibles());
        }if (e.getActionCommand().equals("LIBROS_ALQUILADOS")){
            vLibAlquilados = new VentanaLibrosAlquilados();
            vLibAlquilados.setVisible(true);
            vLibAlquilados.actualizarDatos(model.getLibrosAlquilados());
        }if (e.getActionCommand().equals("ALQUILAR_LIBRO")){
            vAlqLibros = new VentanaAlquilarLibro();
            vAlqLibros.setVisible(true);
            vAlqLibros.setController(this);
        }
        if (e.getActionCommand().equals("ALQUILAR")){
            model.alquilarLibro(vAlqLibros.getIsbn(),vAlqLibros.getDNISocio());
            model.setAlquilado(vAlqLibros.getIsbn());
        }if (e.getActionCommand().equals("CANCELAR_ALQUILER")){
            vAlqLibros.setVisible(false);
        }if (e.getActionCommand().equals("DEVOLVER_LIBRO")){
            vDevLib = new VentanaDevolverLibro();
            vDevLib.setVisible(true);
            vDevLib.setController(this);
        }if (e.getActionCommand().equals("BOTON_DEVOLVER")){
            model.devolverLibro(vDevLib.getIsbn());
        }if (e.getActionCommand().equals("DEVOLVER_CANCELAR")){
            vDevLib.setVisible(false);
        }if (e.getActionCommand().equals("HISTORICO")){
            vHistorico = new VentanaVerHistorico();
            vHistorico.setVisible(true);
            vHistorico.actualizarDatos(model.getHistorico());
        }
    }

}
