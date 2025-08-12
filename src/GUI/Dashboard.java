package GUI;

import App.conexion;
import GUI.notas.notasFeedInicio;
import GUI.tareas.*;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import GUI.feed.Feed;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dashboard {

	JFrame frame;
	private JLabel tareasPendientes;
	private JLabel tareasCreadas;
	private JLabel tareasRealizadas;
	private JLabel tareasConjunto;
	private DefaultPieDataset pie;
	private ChartPanel chartPanel;

	public Dashboard() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 938, 557);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 924, 21);
		toolBar.setToolTipText("");
		frame.getContentPane().add(toolBar);

		JButton btnNewButton = new JButton("Dashboard");
		toolBar.add(btnNewButton);

		JButton btnNewButton_4 = new JButton("feed");
		toolBar.add(btnNewButton_4);

		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Feed mostrarFeed = new Feed();
				mostrarFeed.setVisible(true);
			}
		});

		JButton btnNewButton_1 = new JButton("Tareas");
		toolBar.add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tareasFeedInicio ventanaFeed = new tareasFeedInicio();
				ventanaFeed.setVisible(true);
			}
		});
		

		JButton btnNewButton_2 = new JButton("Notas");
		toolBar.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notasFeedInicio ventananotas = new notasFeedInicio();
				ventananotas.setVisible(true);
			}
		});

		JButton btnNewButton_3 = new JButton("Perfil");
		toolBar.add(btnNewButton_3);

		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datosPersonales ventana = new datosPersonales();
				ventana.setVisible(true);
			}
		});

		JButton btnNewButton_5 = new JButton("Cerrar sesion");
		toolBar.add(btnNewButton_5);

		btnNewButton_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int opcion = JOptionPane.showConfirmDialog(frame, "¿Estás seguro?", "Cerrar sesión", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			        if (opcion == JOptionPane.YES_OPTION) {
			            Login cerrarSesion = new Login();
			            cerrarSesion.frame.setVisible(true);
			            frame.dispose();
			        }
			}
		});

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(27, 98, 201, 133);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Tareas pendientes");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 20, 115, 21);
		panel.add(lblNewLabel_1);
		pie = new DefaultPieDataset();
		tareasPendientes = new JLabel("10");
		tareasPendientes.setFont(new Font("Tahoma", Font.BOLD, 36));
		tareasPendientes.setBounds(20, 43, 46, 31);
		panel.add(tareasPendientes);

		Connection connec = App.conexion.obtenerConexion();
		if(connec ==  null) {
			javax.swing.JOptionPane.showMessageDialog(frame,
					"Pendiente noo se pudo conectar a la base de datos",
					"Error",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return;
		}
		String queryyyy = "SELECT COUNT(*) AS TOTAL FROM tarea WHERE fecha_expiracion >= CURDATE()";
		try(PreparedStatement statement = connec.prepareStatement(queryyyy)) {
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				int totalpendiente  = rs.getInt("TOTAL");
				tareasPendientes.setText(String.valueOf(totalpendiente));
				pie.setValue("tareaspendientes", totalpendiente);
			}
			rs.close();
		} catch (SQLException error) {
			javax.swing.JOptionPane.showMessageDialog(frame,
					"No se pudo conectar con la base de datos",
					"Error",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return;
		}
		finally {
			App.conexion.cerrarConexion(connec);
		}


		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(250, 98, 201, 133);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_1 = new JLabel("Tareas Creadas");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(10, 23, 102, 13);
		panel_1.add(lblNewLabel_1_1);

		tareasCreadas = new JLabel("0");
		tareasCreadas.setFont(new Font("Tahoma", Font.BOLD, 36));
		tareasCreadas.setBounds(10, 38, 46, 36);
		panel_1.add(tareasCreadas);

			Connection conn = App.conexion.obtenerConexion();
			if(conn ==  null) {
				javax.swing.JOptionPane.showMessageDialog(frame,
						"Creadas no se pudo conectar a la base de datos",
						"Error",
						javax.swing.JOptionPane.ERROR_MESSAGE);
				return;
			}
			String query = "SELECT COUNT(*) AS TOTAL FROM tarea";
			try(PreparedStatement statement = conexion.obtenerConexion().prepareStatement(query)) {
				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					int totalcreadas  = rs.getInt("TOTAL");
					tareasCreadas.setText(String.valueOf(totalcreadas));
					pie.setValue("tareascreadas", totalcreadas);
				}
				rs.close();
	} catch (SQLException error) {
				javax.swing.JOptionPane.showMessageDialog(frame,
						"No se pudo conectar con la base de datos",
						"Error",
						javax.swing.JOptionPane.ERROR_MESSAGE);
				return;
		}
			finally {
				App.conexion.cerrarConexion(conn);
			}

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(475, 98, 201, 133);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_1_2 = new JLabel("Tareas Realizadas");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setBounds(10, 20, 103, 13);
		panel_2.add(lblNewLabel_1_2);

		tareasRealizadas = new JLabel("0");
		tareasRealizadas.setFont(new Font("Tahoma", Font.BOLD, 36));
		tareasRealizadas.setBounds(10, 44, 46, 36);
		panel_2.add(tareasRealizadas);
		Connection con = App.conexion.obtenerConexion();
		if(con ==  null) {
			javax.swing.JOptionPane.showMessageDialog(frame,
					"No se pudo conectar a la base de datos",
					"Error",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return;
		}
		String queryy = "SELECT COUNT(*) AS TOTAL FROM tarea WHERE fecha_expiracion < CURDATE()";
		try(PreparedStatement statement = con.prepareStatement(queryy)) {
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				int totalrealizadas  = rs.getInt("TOTAL");
				tareasRealizadas.setText(String.valueOf(totalrealizadas));
				pie.setValue("tareasrealizadas", totalrealizadas);
			}
			rs.close();
		} catch (SQLException error) {
			javax.swing.JOptionPane.showMessageDialog(frame,
					"No se pudo conectar con la base de datos",
					"Error",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return;
		}
		finally {
			App.conexion.cerrarConexion(con);
		}

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(698, 98, 201, 133);
		frame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_1_3 = new JLabel("Tareas en conjunto");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_3.setBounds(10, 21, 118, 13);
		panel_3.add(lblNewLabel_1_3);

		tareasConjunto = new JLabel("0");
		tareasConjunto.setFont(new Font("Tahoma", Font.BOLD, 36));
		tareasConjunto.setBounds(10, 45, 46, 36);
		panel_3.add(tareasConjunto);
		Connection conne = App.conexion.obtenerConexion();
		if(conne ==  null) {
			javax.swing.JOptionPane.showMessageDialog(frame,
					"Conjunto no se pudo conectar a la base de datos",
					"Error",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return;
		}
		String queryyy = "SELECT COUNT(*) AS TOTAL FROM tarea_compartida";
		try(PreparedStatement statement = conexion.obtenerConexion().prepareStatement(queryyy)) {
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				int totalconjunto  = rs.getInt("TOTAL");
				tareasConjunto.setText(String.valueOf(totalconjunto));
				pie.setValue("tareaconjunto", totalconjunto);
			}
			rs.close();
		} catch (SQLException error) {
			javax.swing.JOptionPane.showMessageDialog(frame,
					"No se pudo conectar con la base de datos",
					"Error",
					javax.swing.JOptionPane.ERROR_MESSAGE);
			return;
		}
		finally {
			App.conexion.cerrarConexion(conne);
		}

		JLabel lblNewLabel = new JLabel("Dashboard");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBounds(27, 64, 128, 21);
		frame.getContentPane().add(lblNewLabel);

		pie = new DefaultPieDataset();
		actualizarGrafico();

		JFreeChart chart = ChartFactory.createPieChart("Resumen de tareas", pie, true, true, false);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(10, 252, 474, 268);
		chartPanel.setVisible(true);
		chartPanel.repaint();
		frame.getContentPane().add(chartPanel);

	}

	// se  obtienen los datos de los paneles y los refleja en la grafica
	private void actualizarGrafico() {
		int pendientes = obtenerValorNumerico(tareasPendientes);
		int creadas = obtenerValorNumerico(tareasCreadas);
		int realizadas = obtenerValorNumerico(tareasRealizadas);
		int conjunto = obtenerValorNumerico(tareasConjunto);

		pie.setValue("Tareas pendientes", pendientes);
		pie.setValue("Tareas Creadas", creadas);
		pie.setValue("Tareas Realizadas", realizadas);
		pie.setValue("Tareas conjunto", conjunto);
	}
	private int obtenerValorNumerico(JLabel label) {
		try {
			return Integer.parseInt(label.getText().trim());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}