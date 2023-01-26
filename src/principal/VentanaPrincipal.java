package principal;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.GenerarPublicaciones;
import logica.Publicacion;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaPrincipal extends JFrame {

	private ArrayList<Publicacion> listaPublicacion = GenerarPublicaciones.devolverPublicaciones();

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	private void asignarPublicaciones(DefaultTableModel modeloTabla)	{
		for (int i = 0; i < listaPublicacion.size(); i++) {

			String titulo = listaPublicacion.get(i).getTitulo();
			String nom_usuario = listaPublicacion.get(i).getUsuario().getNick();
			String link_usuario = listaPublicacion.get(i).getUsuario().getUrl();
			String link = listaPublicacion.get(i).getLink();

			Object[] data = { titulo, nom_usuario, link_usuario, link };
			modeloTabla.addRow(data);
		}
	}

	public void abrirLink(String link)	{
		if (link.contains("http"))	{
			Desktop desktop = java.awt.Desktop.getDesktop();
			try	{
				URI url = new URI(link);
				desktop.browse(url);
			} catch (URISyntaxException | IOException e)	{
				e.printStackTrace();
			}
		}
	}

	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1184, 721);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 140, 1148, 531);
		contentPane.add(scrollPane);

		String[] cols = { "Titulo", "Usuario", "Link Usuario", "Link" };

		DefaultTableModel modeloTabla = new DefaultTableModel(cols, 0);
		asignarPublicaciones(modeloTabla);
		JTable table = new JTable(modeloTabla);
		table.setRowHeight(50);
		table.setAutoscrolls(true);
		table.setDefaultEditor(Object.class, null);

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				String link = (String) table.getValueAt(row, col);
				
				if (row >= 0 && col >= 0 && e.getClickCount() == 2) {
					abrirLink(link);
				}
			}
		});

		scrollPane.setViewportView(table);
	}
}
