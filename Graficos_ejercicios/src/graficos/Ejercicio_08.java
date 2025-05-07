// Diapositiva 36. JCheckBox

package graficos;

import javax.swing.*;
import java.awt.*;

public class Ejercicio_08 {
	
    public static void main(String[] args) {
        JFrame frame = new JFrame("Programación Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setPreferredSize(new Dimension(300, 100));

        JCheckBox chckbxOpcion = new JCheckBox("Opción 1", true);
        contentPane.add(chckbxOpcion);

        JCheckBox chckbxNewCheckBox = new JCheckBox("Opción 2", true);
        contentPane.add(chckbxNewCheckBox);

        JCheckBox chckbxOpcion_1 = new JCheckBox("Opción 3", false);
        contentPane.add(chckbxOpcion_1);

        frame.setContentPane(contentPane);

        frame.pack();
        frame.setVisible(true);
    }
}
