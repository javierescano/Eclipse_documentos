//Diapositiva 32. JLabel

package graficos;

import javax.swing.*;

public class Ejercicio_04 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Programación Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = new JPanel();

        BoxLayout gestor = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
        contentPane.setLayout(gestor);

        JLabel etiqueta1 = new JLabel();
        JLabel etiqueta2 = new JLabel();
        JLabel etiqueta3 = new JLabel();
        JLabel etiqueta4 = new JLabel();
        JLabel etiqueta5 = new JLabel();

        etiqueta1.setText("Etiqueta de texto");

        etiqueta2.setIcon(new ImageIcon("icono.png"));

        etiqueta3.setHorizontalTextPosition(SwingConstants.LEFT);
        etiqueta3.setText("Icono a la derecha");
        etiqueta3.setIcon(new ImageIcon("img/icono.png"));

        etiqueta4.setHorizontalTextPosition(SwingConstants.RIGHT);
        etiqueta4.setText("Icono a la izquierda");
        etiqueta4.setIcon(new ImageIcon("img/icono.png"));

        etiqueta5.setText("Icono arriba");
        etiqueta5.setIcon(new ImageIcon("img/icono.png"));
        etiqueta5.setHorizontalTextPosition(SwingConstants.CENTER);
        etiqueta5.setVerticalTextPosition(SwingConstants.BOTTOM);

        contentPane.add(etiqueta1);
        contentPane.add(etiqueta2);
        contentPane.add(etiqueta3);
        contentPane.add(etiqueta4);
        contentPane.add(etiqueta5);

        frame.setContentPane(contentPane);
        frame.setSize(300, 400);
        frame.setVisible(true);
            }
        }
