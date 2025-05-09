package graficos;

import javax.swing.*;
import java.awt.*; 

public class Ejemplo_33 {

    public static void main(String[] args) {
        MarcoConTeclas mimarco = new MarcoConTeclas();
        mimarco.setVisible(true);
        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MarcoConTeclas extends JFrame {

    public MarcoConTeclas() {
    	
        setTitle("Eventos");
        
        setBounds(400, 300, 500, 400);
        
        LaminaBotones milamina = new LaminaBotones();
        
        add(milamina);
    }
}

class LaminaBotones extends JPanel {

    JButton botonAzul=new JButton("Azul");

    public LaminaBotones() {
    	
        add(botonAzul);

        // Ejemplo de manejo de evento:
        //botonAzul.addActionListener(e -> setBackground(Color.BLUE));
    }
}
