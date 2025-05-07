//Diapositiva 35. JRadioButton

package graficos;

import javax.swing.*;
import java.awt.*;


public class Ejercicio_07 {
	
    public static void main(String[] args) {
        JFrame frame = new JFrame("Programación Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setPreferredSize(new Dimension(300, 100));

        JRadioButton rdbtnOpcion = new JRadioButton("Opcion 1", true);
        rdbtnOpcion.setBounds(43, 194, 109, 23);
        contentPane.add(rdbtnOpcion);

        JRadioButton rdbtnOpcion_1 = new JRadioButton("Opcion 2", false);
        rdbtnOpcion_1.setBounds(43, 220, 109, 23);
        contentPane.add(rdbtnOpcion_1);

        JRadioButton rdbtnOpcion_2 = new JRadioButton("Opcion 3", false);
        rdbtnOpcion_2.setBounds(43, 246, 109, 23);
        contentPane.add(rdbtnOpcion_2);

        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(rdbtnOpcion);
        bgroup.add(rdbtnOpcion_1);
	    bgroup.add(rdbtnOpcion_2);
	        
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setVisible(true);
    }
}

