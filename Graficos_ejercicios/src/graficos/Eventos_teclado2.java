package graficos;

import javax.swing.*;
import java.awt.event.*;

public class Eventos_teclado2 extends JFrame implements KeyListener {

    private JLabel etiqueta;

    public Eventos_teclado2() {
        setTitle("Ejemplo KeyListener");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        etiqueta = new JLabel("Pulsa una tecla...");
        //cambiar tamaño letra
        etiqueta.setFont(etiqueta.getFont().deriveFont(24f));
        add(etiqueta);

        // Registrar el listener en el JFrame
        this.addKeyListener(this);
        setFocusable(true); // Necesario para recibir eventos de teclado
        setVisible(true);
        requestFocusInWindow(); // Solicitar el foco después de hacer visible la ventana
    }

    @Override
    public void keyPressed(KeyEvent e) {
        etiqueta.setText("Tecla presionada: " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        etiqueta.setText("Tecla liberada: " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Puedes mostrar el carácter si lo prefieres:
        etiqueta.setText("Tecla tecleada: " + e.getKeyChar());
    }

    public static void main(String[] args) {
        new Eventos_teclado2();
    }
}
