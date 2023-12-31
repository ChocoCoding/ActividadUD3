package org.example.view;

import org.example.entities.Alquiler;
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

public class VentanaVerHistorico extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	public void actualizarDatos(ArrayList<Alquiler> listaAlquileres) {
		String[] columnHeaders = {"LIBRO", "SOCIO", "FECHA_ALQUILER", "FECHA_DEVOLUCION"};
		DefaultTableModel model = new DefaultTableModel(columnHeaders, 0);

		table = new JTable();
		scrollPane.setViewportView(table);
		for (Alquiler al : listaAlquileres) {
			model.addRow(new Object[] {al.getIsbn(), al.getDNI(), al.getFechaAlquiler(),al.getFechaDevolucion()});
		}

		table.setModel(model);
	}

	public VentanaVerHistorico() {
		setTitle("APP BIBLIOTECA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDisponibles = new JLabel("HISTÓRICO DE ALQUILERES");
		lblDisponibles.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDisponibles.setBounds(100, 23, 253, 27);
		contentPane.add(lblDisponibles);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 60, 550, 174);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "LIBRO", "SOCIO", "FECHA_ALQUILER", "FECHA_DEVOLUCION" }));
	}

}
