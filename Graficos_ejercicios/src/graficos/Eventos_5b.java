//Programa sensible a eventos de raton
//Clases anónimas.

package graficos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Eventos_5b extends JFrame {
    private JLabel etiqueta;

    public Eventos_5b() {
        setTitle("Eventos de Ratón y Teclado");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Área sensible a eventos de ratón
        JPanel area = new JPanel();
        area.setBackground(Color.LIGHT_GRAY);
        etiqueta = new JLabel("Interactúa con el área o pulsa una tecla");
        area.add(etiqueta);

        // MouseAdapter para entrada y salida del ratón
        area.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                etiqueta.setText("¡El ratón ha entrado en el área!");
            }
            public void mouseExited(MouseEvent e) {
                etiqueta.setText("El ratón ha salido del área.");
            }
        });

        // KeyAdapter para pulsaciones de teclas
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                etiqueta.setText("Tecla pulsada: " + KeyEvent.getKeyText(e.getKeyCode()));
            }
        });

        add(area, BorderLayout.CENTER);
        setFocusable(true); // Importante para recibir eventos de teclado
        setVisible(true);
    }

    public static void main(String[] args) {
        new Eventos_5b();
    }
}