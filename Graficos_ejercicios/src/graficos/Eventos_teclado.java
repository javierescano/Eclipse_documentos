package graficos;

import javax.swing.*;
import java.awt.event.*;

public class Eventos_teclado extends JFrame implements KeyListener {

    public Eventos_teclado() {
        setTitle("Ejemplo KeyListener");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Registrar el listener en el JFrame
        this.addKeyListener(this);
        setFocusable(true); // Necesario para recibir eventos de teclado
        requestFocusInWindow(); // Solicitar el foco
        setVisible(true);
    }

    // Obligatorio implementar todos los métodos de KeyListener
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("1. Tecla presionada: " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("2. Tecla liberada: " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("3. Tecla tecleada: " + e.getKeyChar());
    }

    public static void main(String[] args) {
        new Eventos_teclado();
    }
}
