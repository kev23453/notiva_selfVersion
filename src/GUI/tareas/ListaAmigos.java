//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package GUI.tareas;

import App.conexion;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListaAmigos {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JButton btnSeleccionar;

    public ListaAmigos() {
        this.initialize();
        this.cargarUsuariosDesdeBD();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ListaAmigos window = new ListaAmigos();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    private void initialize() {
        this.frame = new JFrame("Lista de Amigos");
        this.frame.getContentPane().setBackground(new Color(255, 255, 255));
        this.frame.setBounds(100, 100, 500, 300);
        this.frame.setDefaultCloseOperation(3);
        this.frame.getContentPane().setLayout((LayoutManager)null);
        this.model = new DefaultTableModel(new Object[]{"Nombres"}, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table = new JTable(this.model);
        this.table.setSelectionMode(2);
        JScrollPane scrollPane = new JScrollPane(this.table);
        scrollPane.setBounds(0, 0, 484, 230);
        this.frame.getContentPane().add(scrollPane);
        this.btnSeleccionar = new JButton("Seleccionar");
        this.btnSeleccionar.setBackground(new Color(192, 192, 192));
        this.btnSeleccionar.setBounds(190, 238, 110, 23);
        this.frame.getContentPane().add(this.btnSeleccionar);
        this.btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ListaAmigos.this.agregarUsuariosSeleccionadosABaseDeDatos();
            }
        });
    }

    private void cargarUsuariosDesdeBD() {
        try (Connection con = conexion.obtenerConexion()) {
            String sql = "SELECT username FROM usuario";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                String username = rs.getString("username");
                this.model.addRow(new Object[]{username});
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this.frame, "Error al cargar usuarios: " + e.getMessage());
        }

    }

    private void agregarUsuariosSeleccionadosABaseDeDatos() {
        int[] selectedRows = this.table.getSelectedRows();
        if (selectedRows.length == 0) {
            JOptionPane.showMessageDialog(this.frame, "Por favor, selecciona al menos un usuario.");
        } else {
            try (Connection con = conexion.obtenerConexion()) {
                String sql = "INSERT INTO tarea_compartida (id_tarea,usuario_compartido) VALUES (?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);

                for(int row : selectedRows) {
                    String username = (String)this.model.getValueAt(row, 0);
                    stmt.setString(1, username);
                    stmt.addBatch();
                }

                stmt.executeBatch();
                stmt.close();
                JOptionPane.showMessageDialog(this.frame, "Usuarios agregados correctamente a tareas_compartida.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this.frame, "Error al insertar usuarios: " + e.getMessage());
            }

        }
    }
}