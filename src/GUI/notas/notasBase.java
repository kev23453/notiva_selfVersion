package GUI.notas;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public abstract class notasBase extends JFrame {
    protected JTextField txtTitulo;
    protected JTextArea textArea;
    protected JScrollPane scrollPane;

    public notasBase() {
        setLayout(null);
        setSize(304, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        txtTitulo = new JTextField("Agrgar titulo");
        txtTitulo.setBounds(10, 50, 252, 30);
        ((AbstractDocument) txtTitulo.getDocument()).setDocumentFilter(new DocumentFilter() {
            private final int MAX_LENGTH = 30;

            @Override
            public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr)
                    throws javax.swing.text.BadLocationException {
                if ((fb.getDocument().getLength() + string.length()) <= MAX_LENGTH) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs)
                    throws javax.swing.text.BadLocationException {
                if ((fb.getDocument().getLength() - length + text.length()) <= MAX_LENGTH) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        add(txtTitulo);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10, 120, 270, 181);
        add(scrollPane);

        configurarComponentes();
    }

    protected abstract void configurarComponentes(); // Se implementa en cada subclase
}