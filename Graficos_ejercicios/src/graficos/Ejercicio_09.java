// Diapositiva 37. JComboBox

package graficos;

import javax.swing.*;

public class Ejercicio_09 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Programación Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Opción 1");
        comboBox.addItem("Opción 2");
        comboBox.addItem("Opción 3");

        JPanel contentPane = new JPanel();
        contentPane.add(comboBox);
        frame.setContentPane(contentPane);

        frame.setSize(400, 150);
        frame.setVisible(true);
    }
}
