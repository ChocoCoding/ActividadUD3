package org.example.controller;

import org.example.entities.Socio;
import org.example.model.Model;
import org.example.view.VentanaPrincipal;
import org.example.view.VentanaVerSocios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller implements ActionListener {

    private Model model;
    private VentanaPrincipal v1;
    private VentanaVerSocios v2;


    public Controller(Model model, VentanaPrincipal v1){
        this.model = model;
        this.v1 = v1;
    }


    public Controller(Model model, VentanaVerSocios v2) {
        this.model = model;
        this.v2 = v2;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
