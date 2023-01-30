package principal;


import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.html.HTMLEditorKit;

import logica.GenerarPublicaciones;
import logica.Publicacion;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JEditorPane;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	private ArrayList<Publicacion> listaPublicacion = GenerarPublicaciones.devolverPublicaciones();

	private JPanel contentPane;
	private JLabel lblNewLabel;

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
			//try	{
				/*URI url = new URI(link);
				desktop.browse(url);
				*/
				
				/*try {
					webView.setPage(link);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			/*} catch (URISyntaxException | IOException e)	{
				e.printStackTrace();
			}*/
		}
	}

	public void mostrarTitulo(String titulo)	{
		lblNewLabel.setText(titulo);
	}
	
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1184, 721);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 140, 755, 531);
		contentPane.add(scrollPane);

		String[] cols = { "Titulo", "Usuario", "Link Usuario", "Link" };

		DefaultTableModel modeloTabla = new DefaultTableModel(cols, 0);
		asignarPublicaciones(modeloTabla);
		JTable table = new JTable(modeloTabla);
		table.setRowHeight(50);
		table.setAutoscrolls(true);
		table.setDefaultEditor(Object.class, null);
		
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(300);
		columnModel.getColumn(1).setPreferredWidth(40);
		columnModel.getColumn(2).setPreferredWidth(10);
		columnModel.getColumn(3).setPreferredWidth(180);

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				int col = table.columnAtPoint(e.getPoint());
				
				String link = (String) table.getValueAt(row, col);
				String titulo = (String) table.getValueAt(row, 0);
				
				mostrarTitulo(titulo);
				
				if (col >= 2 && e.getClickCount() == 2) {
					abrirLink(link);
				}
				
			}
		});

		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 755, 118);
		contentPane.add(lblNewLabel);
		
		JEditorPane webView = new JEditorPane();
		
		try {
			webView.setPage("https://www.google.es/");
		} catch (IOException e1) {
			webView.setContentType("text/html");
			webView.setText("<html>Connection issues!</html>");
		}
		webView.setBounds(775, 11, 383, 660);
		webView.setEditable(false);
		contentPane.add(webView);
		
		JScrollPane scrollPane_1 = new JScrollPane(webView);
		scrollPane_1.setBounds(775, 11, 383, 660);
		contentPane.add(scrollPane_1);
	}
}
