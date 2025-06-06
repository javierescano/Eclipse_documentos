//Ejemplo casilla de verificación

package graficos;

import javax.swing.*;
import java.awt.event.*;

public class Eventos_7 extends JFrame {
    private JCheckBox casilla;
    private JLabel etiqueta;

    public Eventos_7() {
        setTitle("Ejemplo con JCheckBox");
        setSize(300, 160);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        casilla = new JCheckBox("¿Te gusta Java?");
        casilla.setBounds(30, 20, 150, 30);
        add(casilla);

        etiqueta = new JLabel("Selecciona la casilla");
        etiqueta.setBounds(30, 60, 200, 30);
        add(etiqueta);

        // Registrar el listener usando una clase interna con nombre
        casilla.addItemListener(new EscuchaCasilla());

        setVisible(true);
    }

    // Clase interna para manejar los eventos del JCheckBox
    class EscuchaCasilla implements ItemListener {
    	//Nuestro ActionListener >> itemStateChanged
        public void itemStateChanged(ItemEvent e) {
            if (casilla.isSelected()) {
                etiqueta.setText("¡A ti te gusta Java!");
            } else {
                etiqueta.setText("No te gusta Java...");
            }
        }
    }

    public static void main(String[] args) {
        new Eventos_7();
    }
}