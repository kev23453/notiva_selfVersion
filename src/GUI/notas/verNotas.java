package GUI.notas;

import App.conexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class verNotas extends notasBase {
    private String tituloOriginal;
    private JButton btnGuardarCambios;
    private notasFeedInicio ventanaPrincipal;

    public verNotas(notasFeedInicio ventanaPrincipal, String titulo, String contenido) {
        super();
        setTitle("Editar Nota");
        this.ventanaPrincipal = ventanaPrincipal;
        this.tituloOriginal = titulo;

        txtTitulo.setText(titulo);
        txtTitulo.setEditable(true);

        textArea.setText(contenido);
        textArea.setEditable(true);
    }

    @Override
    protected void configurarComponentes() {
        btnGuardarCambios = new JButton("Guardar");
        btnGuardarCambios.setBounds(150, 311, 130, 30);
        add(btnGuardarCambios);

        btnGuardarCambios.addActionListener(e -> {
            String nuevoTitulo = txtTitulo.getText();
            String nuevoContenido = textArea.getText();

            if (!nuevoTitulo.isEmpty() && !nuevoContenido.isEmpty()) {
                try (Connection conn = conexion.obtenerConexion()) {
                    String sql = "UPDATE notas SET titulo = ?, contenido = ? WHERE titulo = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, nuevoTitulo);
                    stmt.setString(2, nuevoContenido);
                    stmt.setString(3, tituloOriginal);

                    stmt.executeUpdate();

                    // Actualizar feed y cerrar
                    ventanaPrincipal.actualizarFeed();
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al actualizar la nota.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Completa ambos campos.");
            }
        });
    }
}