//Diapositiva 34.JToggleButton

package graficos;

import javax.swing.*;

public class Ejercicio_06 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Programación Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JToggleButton b1 = new JToggleButton("Botón interruptor", true);

        JPanel contentPane = new JPanel();
        contentPane.add(b1);
        frame.setContentPane(contentPane);

        frame.setSize(400, 150);
        frame.setVisible(true);
    }
}
