package org.example.view;

import org.example.controller.Controller;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;

	private Controller controller;

	JButton btnAlquilarLibro;
	JButton btnDevolverLibro;
	JButton btnLibrosDisponibles;
	JButton btnVerSocios;
	JButton btnLibrosAlquilados;
	JButton btnHistorico;

	public void setController(Controller controller){
		this.controller = controller;
		btnAlquilarLibro.addActionListener(this.controller);
		btnAlquilarLibro.setActionCommand("ALQUILAR_LIBRO");

		btnDevolverLibro.addActionListener(this.controller);
		btnDevolverLibro.setActionCommand("DEVOLVER_LIBRO");

		btnLibrosDisponibles.addActionListener(this.controller);
		btnLibrosDisponibles.setActionCommand("LIBROS_DISPONIBLES");

		btnVerSocios.addActionListener(this.controller);
		btnVerSocios.setActionCommand("VER_SOCIOS");

		btnLibrosAlquilados.addActionListener(this.controller);
		btnLibrosAlquilados.setActionCommand("LIBROS_ALQUILADOS");

		btnHistorico.addActionListener(this.controller);
		btnHistorico.setActionCommand("HISTORICO");
	}

	public void arrancar() {
		setLocationRelativeTo(null);
		setVisible(true);
	}


	public VentanaPrincipal() {
		setTitle("APP BIBLIOTECA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 392, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAlquilarLibro = new JButton("Alquilar libro");
		btnAlquilarLibro.setBounds(220, 57, 131, 23);
		contentPane.add(btnAlquilarLibro);

		btnDevolverLibro = new JButton("Devolver libro");
		btnDevolverLibro.setBounds(220, 91, 131, 23);
		contentPane.add(btnDevolverLibro);

		btnLibrosDisponibles = new JButton("Ver libros disponibles");
		btnLibrosDisponibles.setBounds(21, 91, 131, 23);
		contentPane.add(btnLibrosDisponibles);

		btnVerSocios = new JButton("Ver socios");
		btnVerSocios.setBounds(21, 57, 131, 23);
		contentPane.add(btnVerSocios);

		btnLibrosAlquilados = new JButton("Ver libros alquilados");
		btnLibrosAlquilados.setBounds(21, 130, 131, 23);
		contentPane.add(btnLibrosAlquilados);

		btnHistorico = new JButton("Ver histórico");
		btnHistorico.setBounds(220, 130, 131, 23);
		contentPane.add(btnHistorico);
	}
}
