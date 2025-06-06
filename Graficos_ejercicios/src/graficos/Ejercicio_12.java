//Diapositiva 41. JList 

package graficos;

import javax.swing.*;
import java.awt.*;

public class Ejercicio_12 {


    public static void main(String[] args) {
        JFrame frame = new JFrame("Programación Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final int MAX = 10;
        String[] listElems = new String[MAX];
        for (int i = 0; i < MAX; i++) {
            listElems[i] = "Elemento " + i;
        }

        JList<String> list = new JList<>(listElems);
        
        //Añadir barra de desplazamiento.
        JScrollPane scroll = new JScrollPane(list);

        JButton btnGet = new JButton("Seleccionar");

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scroll, BorderLayout.CENTER);
        contentPane.add(btnGet, BorderLayout.SOUTH);
        frame.setContentPane(contentPane);

        frame.setSize(200, 150);
        frame.setVisible(true);
    }
}

