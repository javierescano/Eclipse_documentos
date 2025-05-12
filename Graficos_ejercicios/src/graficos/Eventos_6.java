//Ejemplo con etiqueta y botón.

package graficos;

import javax.swing.*;
import java.awt.event.*;

public class Eventos_6 extends JFrame {
    private JButton boton;
    private JLabel etiqueta;

    public Eventos_6() {
        setTitle("Ejemplo de Evento");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        etiqueta = new JLabel("Esperando acción...");
        etiqueta.setBounds(80, 30, 150, 30);
        add(etiqueta);

        boton = new JButton("Haz clic aquí");
        boton.setBounds(90, 70, 120, 30);
        add(boton);

        boton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                etiqueta.setText("¡Botón pulsado!");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Eventos_6();
    }
}
