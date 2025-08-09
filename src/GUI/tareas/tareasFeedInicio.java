//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package GUI.tareas;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class tareasFeedInicio extends JFrame {
    public tareasFeedInicio() {
        this.initialize();
    }

    private void initialize() {
        this.setTitle("Tareas");
        this.setBounds(100, 100, 1065, 581);
        this.setDefaultCloseOperation(2);
        this.getContentPane().setLayout((LayoutManager)null);
        JToolBar toolBar = new JToolBar();
        toolBar.setToolTipText("");
        toolBar.setBounds(0, 0, 1051, 21);
        this.getContentPane().add(toolBar);
        JButton btnNewButton = new JButton("Dashboard");
        btnNewButton.setEnabled(false);
        toolBar.add(btnNewButton);
        JButton btnNewButton_1 = new JButton("Tareas");
        toolBar.add(btnNewButton_1);
        JButton btnNewButton_4 = new JButton("Feed");
        btnNewButton_4.setEnabled(false);
        toolBar.add(btnNewButton_4);
        JButton btnNewButton_3 = new JButton("Perfil");
        btnNewButton_3.setEnabled(false);
        toolBar.add(btnNewButton_3);
        JButton btnNewButton_2 = new JButton("Notas");
        btnNewButton_2.setEnabled(false);
        toolBar.add(btnNewButton_2);
        JButton btnNewButton_5 = new JButton("Cerrar sesión");
        btnNewButton_5.setEnabled(false);
        toolBar.add(btnNewButton_5);
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(10, 23, 1031, 511);
        this.getContentPane().add(panel);
        panel.setLayout((LayoutManager)null);
        JButton btnNewButton_6 = new JButton("Agregar tarea");
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NuevaTarea crearTarea = new NuevaTarea();
                crearTarea.setVisible(true);
            }
        });
        btnNewButton_6.setBounds(23, 59, 125, 27);
        panel.add(btnNewButton_6);
        JLabel lblNewLabel = new JLabel("Listado de tareas");
        lblNewLabel.setFont(new Font("Tahoma", 1, 20));
        lblNewLabel.setBounds(23, 22, 196, 27);
        panel.add(lblNewLabel);
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(23, 108, 281, 221);
        panel.add(panel_1);
    }
}