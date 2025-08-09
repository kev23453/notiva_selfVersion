//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package GUI;

import App.conexion;
import Modules.User;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class datosPersonales extends JFrame {
    private JTextField txtName;
    private JTextField txtLastName;
    private JTextArea txtBiografy;
    private JComboBox<String> cbSexo;
    private JLabel lblAvatarImagen;

    public datosPersonales() {
        this.initialize();
    }

    private void initialize() {
        this.setTitle("Datos Personales");
        this.setBounds(100, 100, 1064, 636);
        this.setDefaultCloseOperation(2);
        this.getContentPane().setLayout((LayoutManager)null);
        JToolBar toolBar = new JToolBar();
        toolBar.setBounds(0, 0, 1051, 21);
        this.getContentPane().add(toolBar);
        toolBar.add(new JButton("Dashboard"));
        toolBar.add(new JButton("Tareas"));
        toolBar.add(new JButton("feed"));
        toolBar.add(new JButton("Perfil"));
        toolBar.add(new JButton("Notas"));
        toolBar.add(new JButton("Cerrar sesion"));
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(10, 20, 1030, 600);
        this.getContentPane().add(panel);
        panel.setLayout((LayoutManager)null);
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(264, 22, 497, 541);
        panel.add(panel_1);
        panel_1.setLayout((LayoutManager)null);
        this.lblAvatarImagen = new JLabel("\tElegir Avatar", 0);
        this.lblAvatarImagen.setBounds(177, 20, 138, 138);
        panel_1.add(this.lblAvatarImagen);
        this.lblAvatarImagen.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        this.lblAvatarImagen.setCursor(new Cursor(12));
        this.lblAvatarImagen.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                datosPersonales.this.lblAvatarImagen.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }

            public void mouseExited(MouseEvent evt) {
                datosPersonales.this.lblAvatarImagen.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
            }

            public void mouseClicked(MouseEvent evt) {
                avatar ventanaAvatar = new avatar(datosPersonales.this);
                ventanaAvatar.setVisible(true);
                datosPersonales.this.actualizarAvatar();
            }
        });
        this.txtName = new JTextField();
        this.txtName.setBounds(46, 219, 183, 29);
        panel_1.add(this.txtName);
        this.txtName.setColumns(10);
        this.txtLastName = new JTextField();
        this.txtLastName.setBounds(260, 219, 193, 29);
        panel_1.add(this.txtLastName);
        this.txtLastName.setColumns(10);
        JLabel lblNewLabel_1 = new JLabel("Nombre");
        lblNewLabel_1.setBounds(44, 196, 69, 13);
        panel_1.add(lblNewLabel_1);
        JLabel lblNewLabel_2 = new JLabel("Apellido");
        lblNewLabel_2.setBounds(260, 196, 69, 13);
        panel_1.add(lblNewLabel_2);
        JLabel lblNewLabel_3 = new JLabel("Biografia");
        lblNewLabel_3.setBounds(46, 258, 67, 13);
        panel_1.add(lblNewLabel_3);
        this.txtBiografy = new JTextArea();
        this.txtBiografy.setBounds(46, 279, 407, 125);
        panel_1.add(this.txtBiografy);
        this.cbSexo = new JComboBox();
        this.cbSexo.setModel(new DefaultComboBoxModel(new String[]{"Masculino", "Femenino", "No definido"}));
        this.cbSexo.setBounds(46, 440, 259, 29);
        panel_1.add(this.cbSexo);
        JLabel lblNewLabel_4 = new JLabel("Sexo");
        lblNewLabel_4.setBounds(46, 417, 45, 13);
        panel_1.add(lblNewLabel_4);
        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBounds(331, 440, 122, 29);
        panel_1.add(btnModificar);
        this.cargarDatosUsuario();
        this.actualizarAvatar();
        btnModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!datosPersonales.this.txtName.getText().trim().isEmpty() && !datosPersonales.this.txtLastName.getText().trim().isEmpty()) {
                    Connection conexionDB = conexion.obtenerConexion();
                    if (conexionDB == null) {
                        JOptionPane.showMessageDialog((Component)null, "No se pudo conectar a la base de datos.", "Error de conexión", 0);
                    } else {
                        try {
                            if (datosPersonales.this.existeDatosUsuario(conexionDB, User.getCurrentUser().getUserIdString())) {
                                String sqlUpdate = "UPDATE datos_usuario SET nombre = ?, apellido = ?, biografia = ?, sexo = ? WHERE id_usuario = ?";
                                PreparedStatement stmt = conexionDB.prepareStatement(sqlUpdate);

                                try {
                                    stmt.setString(1, datosPersonales.this.txtName.getText().trim());
                                    stmt.setString(2, datosPersonales.this.txtLastName.getText().trim());
                                    stmt.setString(3, datosPersonales.this.txtBiografy.getText().trim());
                                    stmt.setString(4, (String)datosPersonales.this.cbSexo.getSelectedItem());
                                    stmt.setString(5, User.getCurrentUser().getUserIdString());
                                    int filas = stmt.executeUpdate();
                                    if (filas > 0) {
                                        JOptionPane.showMessageDialog((Component)null, "Datos actualizados correctamente.", "Éxito", 1);
                                    } else {
                                        JOptionPane.showMessageDialog((Component)null, "No se encontró registro para actualizar.", "Aviso", 2);
                                    }
                                } catch (Throwable var17) {
                                    if (stmt != null) {
                                        try {
                                            stmt.close();
                                        } catch (Throwable var15) {
                                            var17.addSuppressed(var15);
                                        }
                                    }

                                    throw var17;
                                }

                                if (stmt != null) {
                                    stmt.close();
                                }
                            } else {
                                String sqlInsert = "INSERT INTO datos_usuario (id_usuario, nombre, apellido, biografia, sexo) VALUES (?, ?, ?, ?, ?)";
                                PreparedStatement stmt = conexionDB.prepareStatement(sqlInsert);

                                try {
                                    stmt.setString(1, User.getCurrentUser().getUserIdString());
                                    stmt.setString(2, datosPersonales.this.txtName.getText().trim());
                                    stmt.setString(3, datosPersonales.this.txtLastName.getText().trim());
                                    stmt.setString(4, datosPersonales.this.txtBiografy.getText().trim());
                                    stmt.setString(5, (String)datosPersonales.this.cbSexo.getSelectedItem());
                                    int filas = stmt.executeUpdate();
                                    if (filas > 0) {
                                        JOptionPane.showMessageDialog((Component)null, "Datos guardados correctamente.", "Éxito", 1);
                                    }
                                } catch (Throwable var16) {
                                    if (stmt != null) {
                                        try {
                                            stmt.close();
                                        } catch (Throwable var14) {
                                            var16.addSuppressed(var14);
                                        }
                                    }

                                    throw var16;
                                }

                                if (stmt != null) {
                                    stmt.close();
                                }
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog((Component)null, "Error al guardar datos:\n" + ex.getMessage(), "Error SQL", 0);
                            ex.printStackTrace();
                        } finally {
                            conexion.cerrarConexion(conexionDB);
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog((Component)null, "Por favor, complete todos los campos.", "Campos vacíos", 2);
                }
            }
        });
    }

    private void actualizarAvatar() {
        User usuarioActivo = User.getCurrentUser();
        if (usuarioActivo != null) {
            String rutaAvatar = conexion.obtenerRutaAvatarPorUsuario(usuarioActivo.getUserId());
            if (rutaAvatar != null) {
                ImageIcon icono = new ImageIcon(this.getClass().getResource(rutaAvatar));
                Image imgEscalada = icono.getImage().getScaledInstance(138, 138, 4);
                this.lblAvatarImagen.setIcon(new ImageIcon(imgEscalada));
                this.lblAvatarImagen.setText((String)null);
            }
        }

    }

    private void cargarDatosUsuario() {
        User usuarioActivo = User.getCurrentUser();
        if (usuarioActivo != null) {
            Connection conexionDB = conexion.obtenerConexion();
            if (conexionDB != null) {
                String sql = "SELECT nombre, apellido, biografia, sexo FROM datos_usuario WHERE id_usuario = ?";

                try {
                    PreparedStatement stmt = conexionDB.prepareStatement(sql);

                    try {
                        stmt.setString(1, usuarioActivo.getUserIdString());
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            this.txtName.setText(rs.getString("nombre"));
                            this.txtLastName.setText(rs.getString("apellido"));
                            this.txtBiografy.setText(rs.getString("biografia"));
                            this.cbSexo.setSelectedItem(rs.getString("sexo"));
                        }
                    } catch (Throwable var13) {
                        if (stmt != null) {
                            try {
                                stmt.close();
                            } catch (Throwable var12) {
                                var13.addSuppressed(var12);
                            }
                        }

                        throw var13;
                    }

                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    conexion.cerrarConexion(conexionDB);
                }

            }
        }
    }

    private boolean existeDatosUsuario(Connection conexionDB, String idUsuario) throws SQLException {
        String sql = "SELECT 1 FROM datos_usuario WHERE id_usuario = ?";

        try (PreparedStatement stmt = conexionDB.prepareStatement(sql)) {
            stmt.setString(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
}