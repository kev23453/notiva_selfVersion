//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package GUI;

import App.conexion;
import Modules.User;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class avatar extends JDialog {
    private List<JButton> botones = new ArrayList();
    private JButton avatarSeleccionado = null;
    private final int[] idsAvatares = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
    private final String[] rutasAvatares = new String[]{"/iconos/avatar1.png", "/iconos/avatar2.png", "/iconos/avatar3.png", "/iconos/avatar4.png", "/iconos/avatra5.png", "/iconos/avatra6.png", "/iconos/avatra7.png", "/iconos/avatra8.png", "/iconos/avatra9.png"};

    public avatar(JFrame parent) {
        super(parent, "Seleccionar Avatar", true);
        this.initialize();
    }

    private void estilizarBoton(JButton boton) {
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
    }

    private void seleccionarBoton(JButton botonSeleccionado) {
        this.avatarSeleccionado = botonSeleccionado;

        for(JButton boton : this.botones) {
            if (boton == botonSeleccionado) {
                boton.setBorderPainted(true);
                boton.setBorder(BorderFactory.createLineBorder(Color.PINK, 3));
            } else {
                this.estilizarBoton(boton);
                boton.setBorder((Border)null);
            }
        }

    }

    private void initialize() {
        this.setSize(520, 550);
        this.setLocationRelativeTo((Component)null);
        this.setLayout((LayoutManager)null);

        for(int i = 0; i < this.rutasAvatares.length; ++i) {
            int x = 10 + i % 3 * 160;
            int y = 57 + i / 3 * 134;
            this.agregarAvatar(this.rutasAvatares[i], x, y);
        }

        JLabel lblTitulo = new JLabel("Choose avatar");
        lblTitulo.setFont(new Font("Tahoma", 1, 18));
        lblTitulo.setHorizontalAlignment(0);
        lblTitulo.setBounds(170, 11, 160, 22);
        this.add(lblTitulo);
        JButton btnContinuar = new JButton("Continuar");
        btnContinuar.setBackground(new Color(192, 192, 192));
        btnContinuar.setBounds(200, 455, 112, 33);
        this.add(btnContinuar);
        btnContinuar.addActionListener((e) -> {
            if (this.avatarSeleccionado != null) {
                int indice = this.botones.indexOf(this.avatarSeleccionado);
                int idAvatarSeleccionado = this.idsAvatares[indice];
                User usuarioActivo = User.getCurrentUser();
                if (usuarioActivo != null) {
                    boolean exito = conexion.actualizarAvatarUsuario(usuarioActivo.getUserId(), idAvatarSeleccionado);
                    if (exito) {
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al guardar el avatar.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "No seleccionaste ningún avatar.");
            }

        });
    }

    private void agregarAvatar(String ruta, int x, int y) {
        ImageIcon icono = new ImageIcon(this.getClass().getResource(ruta));
        Image imagenEscalada = icono.getImage().getScaledInstance(150, 110, 4);
        JButton boton = new JButton(new ImageIcon(imagenEscalada));
        boton.setBounds(x, y, 150, 110);
        this.estilizarBoton(boton);
        boton.addActionListener((e) -> this.seleccionarBoton(boton));
        this.botones.add(boton);
        this.add(boton);
    }
}