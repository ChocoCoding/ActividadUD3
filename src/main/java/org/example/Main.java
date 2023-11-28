package org.example;

import org.example.controller.Controller;
import org.example.model.Model;
import org.example.view.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();

        VentanaPrincipal v1 = new VentanaPrincipal();

        Controller controller = new Controller(model,v1);

        v1.arrancar();

        v1.setController(controller);



    }
}