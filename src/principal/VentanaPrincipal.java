package principal;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logica.GenerarPublicaciones;
import logica.Publicacion;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;

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
	
	private void asignarPublicaciones(DefaultListModel modeloLista)	{
		for (int i = 0; i < listaPublicacion.size(); i++) {
			modeloLista.add(i, listaPublicacion.get(i));
		}
	}
	
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 110, 747, 355);
		contentPane.add(scrollPane);
		
		DefaultListModel modeloLista = new DefaultListModel();
		JList list = new JList();
		list.setEnabled(false);
		list.setModel(modeloLista);
		scrollPane.setViewportView(list);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 747, 89);
		contentPane.add(panel);
	}
}
