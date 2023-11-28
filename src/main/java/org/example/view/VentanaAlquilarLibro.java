package org.example.view;

import org.example.controller.Controller;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaAlquilarLibro extends JFrame {

	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textDNI;
	JButton btnAlquilar;
	JButton btnCancelar;

	private Controller controller;

	public void setController(Controller controller){
		this.controller = controller;
		btnAlquilar.addActionListener(this.controller);
		btnAlquilar.setActionCommand("ALQUILAR");

		btnCancelar.addActionListener(this.controller);
		btnCancelar.setActionCommand("CANCELAR_ALQUILER");

	}


	public VentanaAlquilarLibro() {
		setTitle("APP BIBLIOTECA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAlquiler = new JLabel("ALQUILER LIBRO");
		lblAlquiler.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAlquiler.setBounds(140, 29, 147, 20);
		contentPane.add(lblAlquiler);

		textCodigo = new JTextField();
		textCodigo.setBounds(201, 90, 104, 20);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);

		textDNI = new JTextField();
		textDNI.setColumns(10);
		textDNI.setBounds(201, 133, 104, 20);
		contentPane.add(textDNI);

		JLabel lblCodigo = new JLabel("Código libro:");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(102, 93, 89, 17);
		contentPane.add(lblCodigo);

		JLabel lblDni = new JLabel("DNI socio:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(117, 136, 74, 14);
		contentPane.add(lblDni);

		btnAlquilar = new JButton("Alquilar");
		btnAlquilar.setBounds(306, 227, 89, 23);
		contentPane.add(btnAlquilar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(26, 227, 89, 23);
		contentPane.add(btnCancelar);
	}

	public String getIsbn(){
		return textCodigo.getText();
	}

	public String getDNISocio(){
		return textDNI.getText();
	}
}
