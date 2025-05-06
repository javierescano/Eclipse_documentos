//Diapositiva 40. JSpinner
package graficos;

import javax.swing.*;
import java.awt.*;

public class Ejercicio_11 {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Programación Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SpinnerNumberModel snm = new SpinnerNumberModel(
            Integer.valueOf(0),    //Valor inicial
            Integer.valueOf(0),    //Valor mínimo 
            Integer.valueOf(100),  //Valor máximo
            Integer.valueOf(5)     //Valor incremento/decremento
        );
        JSpinner spnNumber = new JSpinner(snm);

        String[] colors = {"Rojo", "Verde", "Azul"};
        SpinnerModel snl = new SpinnerListModel(colors);
        JSpinner spnList = new JSpinner(snl);
        spnList.setPreferredSize(new Dimension(100, 30));

        JPanel contentPane = new JPanel();
        contentPane.add(spnNumber);
        contentPane.add(spnList);
        frame.setContentPane(contentPane);

        frame.setSize(400, 150);
        frame.setVisible(true);
    }
}
