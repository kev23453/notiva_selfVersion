package GUI.feed;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class Feed extends JFrame {
    private JTextField textField;

    public Feed() {
        this.initialize();
    }

    private void initialize() {
        this.setTitle("Feed");
        this.setBounds(100, 100, 940, 560);
        this.setDefaultCloseOperation(2);
        this.getContentPane().setLayout((LayoutManager)null);
        JToolBar toolBar = new JToolBar();
        toolBar.setBounds(0, 0, 1051, 21);
        this.getContentPane().add(toolBar);
        JButton btnDashboard = new JButton("Dashboard");
        btnDashboard.setEnabled(false);
        toolBar.add(btnDashboard);
        JButton btnTareas = new JButton("Tareas");
        btnTareas.setEnabled(false);
        toolBar.add(btnTareas);
        JButton btnFeed = new JButton("Feed");
        btnFeed.setEnabled(true);
        toolBar.add(btnFeed);
        JButton btnPerfil = new JButton("Perfil");
        btnPerfil.setEnabled(false);
        toolBar.add(btnPerfil);
        JButton btnNotas = new JButton("Notas");
        btnNotas.setEnabled(false);
        toolBar.add(btnNotas);
        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setEnabled(false);
        toolBar.add(btnCerrarSesion);
        JPanel panelLateral = new JPanel();
        panelLateral.setBounds(692, 21, 222, 489);
        panelLateral.setBackground(new Color(192, 192, 192));
        panelLateral.setLayout((LayoutManager)null);
        this.getContentPane().add(panelLateral);
        this.textField = new JTextField();
        this.textField.setBounds(8, 11, 103, 20);
        panelLateral.add(this.textField);
        this.textField.setColumns(10);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(Color.WHITE);
        btnBuscar.setBounds(121, 10, 75, 23);
        panelLateral.add(btnBuscar);
        JPanel panelAmigos = new JPanel();
        panelAmigos.setBackground(new Color(255, 128, 192));
        panelAmigos.setPreferredSize(new Dimension(220, 600));
        JScrollPane scrollAmigos = new JScrollPane(panelAmigos);
        scrollAmigos.setBounds(0, 71, 222, 418);
        scrollAmigos.setVerticalScrollBarPolicy(22);
        panelLateral.add(scrollAmigos);
        panelAmigos.setLayout((LayoutManager)null);
        JLabel lblAmigos = new JLabel("Amigos");
        lblAmigos.setBounds(78, 10, 68, 23);
        lblAmigos.setFont(new Font("Tahoma", 1, 14));
        panelAmigos.add(lblAmigos);
        ImageIcon iconoM = new ImageIcon(this.getClass().getResource("/iconos/amigos.png"));
        Image imagen = iconoM.getImage().getScaledInstance(50, 50, 4);
        ImageIcon iconoA = new ImageIcon(imagen);
        JLabel lblIconoAmigos = new JLabel("", iconoA, 2);
        lblIconoAmigos.setBounds(21, 4, 59, 50);
        panelAmigos.add(lblIconoAmigos);
        JPanel panelFeed = new JPanel();
        panelFeed.setBackground(new Color(64, 128, 140));
        panelFeed.setPreferredSize(new Dimension(580, 1500));
        panelFeed.setLayout((LayoutManager)null);
        JLabel lblMensaje = new JLabel("Comparte tus experiencias con tus amigos");
        lblMensaje.setFont(new Font("Tahoma", 3, 17));
        lblMensaje.setBounds(50, 20, 480, 30);
        panelFeed.add(lblMensaje);
        JPanel panel = new JPanel();
        panel.setBounds(10, 112, 652, 352);
        panelFeed.add(panel);
        panel.setLayout((LayoutManager)null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 652, 352);
        panel.add(scrollPane);
        JButton btnNewButton = new JButton("Hacer publicacion");
        btnNewButton.setBounds(471, 69, 155, 21);
        panelFeed.add(btnNewButton);
        panelFeed.setBounds(10, 31, 672, 479);
        this.getContentPane().add(panelFeed);
    }
}