package graficos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Eventos_JuegoCirculo extends JFrame {
    private int puntos = 0;
    private int x, y, radio = 40;
    private Color colorCirculo = Color.RED;
    private final Random rand = new Random();
    private PanelJuego panel;

    public Eventos_JuegoCirculo() {
        setTitle("Haz clic en el círculo");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel de juego personalizado
        panel = new PanelJuego();
        panel.setFocusable(true);

        // Añadir listeners no anónimos
        panel.addMouseListener(new MouseClicCirculoListener());
        panel.addKeyListener(new TeclaCambioColorListener());

        add(panel);
        moverCirculo(); // Posición inicial
        setVisible(true);
    }

    // Panel personalizado para el juego
    private class PanelJuego extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(colorCirculo);
            g.fillOval(x, y, radio * 2, radio * 2);
            g.setColor(Color.BLACK);
            g.drawString("Puntos: " + puntos, 10, 20);
        }
    }

    // Listener de ratón
    private class MouseClicCirculoListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            int mx = e.getX(), my = e.getY();
            int cx = x + radio, cy = y + radio;
            double distancia = Math.sqrt(Math.pow(mx - cx, 2) + Math.pow(my - cy, 2));
            if (distancia <= radio) {
                puntos++;
                moverCirculo();
                if (puntos >= 10) {
                    JOptionPane.showMessageDialog(panel, "¡Has ganado!");
                    System.exit(0);
                }
            }
        }
    }

    // Listener de teclado
    private class TeclaCambioColorListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                colorCirculo = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
                panel.repaint();
            }
        }
    }

    private void moverCirculo() {
        int ancho = panel.getWidth() > 0 ? panel.getWidth() : 400;
        int alto = panel.getHeight() > 0 ? panel.getHeight() : 400;
        x = rand.nextInt(ancho - 2 * radio);
        y = rand.nextInt(alto - 2 * radio);
        panel.repaint();
        panel.requestFocusInWindow();
    }

    public static void main(String[] args) {
        new Eventos_JuegoCirculo();
    }
}
