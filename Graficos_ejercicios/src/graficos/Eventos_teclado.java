package graficos;

import javax.swing.*;
import java.awt.event.*;

public class Eventos_teclado extends JFrame {

    private JLabel etiqueta;

    public Eventos_teclado() {
        super("Eventos con Teclado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);

        // Creamos un panel para capturar los eventos
        JPanel panel = new JPanel(null); // null layout
        add(panel);

        etiqueta = new JLabel("Pulsa una tecla...");
        etiqueta.setBounds(50, 40, 200, 30);
        panel.add(etiqueta);

        // Añadir el KeyListener al panel en lugar de al JFrame
        panel.setFocusable(true);
        panel.requestFocusInWindow(); // Asegura que el foco esté en el panel
        panel.addKeyListener(new ManejadorTeclado());

        setVisible(true);
    }

    // Clase interna para manejar eventos de teclado
    private class ManejadorTeclado extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            etiqueta.setText("Tecla pulsada: " + e.getKeyChar());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Eventos_teclado::new);
    }
}

