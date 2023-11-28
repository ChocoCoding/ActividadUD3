package org.example.view;

import org.example.entities.Libro;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaLibrosAlquilados extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	public void actualizarDatos(ArrayList<Libro> listaLibros) {
		String[] columnHeaders = {"ISBN", "Titulo", "Autor"};
		DefaultTableModel model = new DefaultTableModel(columnHeaders, 0);

		table = new JTable();
		scrollPane.setViewportView(table);
		for (Libro lb : listaLibros) {
			model.addRow(new Object[] {lb.getIsbn(), lb.getTitulo(), lb.getAutor()});
		}

		table.setModel(model);
	}

	public VentanaLibrosAlquilados() {
		setTitle("APP BIBLIOTECA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAlquilados = new JLabel("LIBROS ALQUILADOS");
		lblAlquilados.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAlquilados.setBounds(128, 24, 192, 27);
		contentPane.add(lblAlquilados);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 60, 392, 174);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Titulo", "Socio", "Fecha" }));
	}
}
