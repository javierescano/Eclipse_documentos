package graficos;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Practica_20 {
    public static void main(String[] args) {
        //Creación de JFrame
    	MarcoSaludos miMarco = new MarcoSaludos();
        miMarco.setVisible(true);
        miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MarcoSaludos extends JFrame {
    
	public MarcoSaludos() {
    	
        setTitle("Práctica: saludar en varios idiomas");
        setBounds(400, 300, 500, 200);
        
        //Creación de lámina
        LaminaSaludos miLamina = new LaminaSaludos();
        
        add(miLamina);
    }
}

class LaminaSaludos extends JPanel {
	   
	//Inicialización de botonoes y etiqueta.
	JButton botonEspanol = new JButton("Español");
    JButton botonIngles = new JButton("Inglés");
    JButton botonFrances = new JButton("Francés");
    JLabel etiquetaSaludo = new JLabel("Pulsa un botón para saludar");

    public LaminaSaludos() {
        
        // Añadir componentes al panel
        add(botonEspanol);
        add(botonIngles);
        add(botonFrances);
        add(etiquetaSaludo);

        // Crear y asociar los listeners
        botonEspanol.addActionListener(new CambiaSaludo("¡Hola, buenos días!"));
        botonIngles.addActionListener(new CambiaSaludo("Hello, good morning!"));
        botonFrances.addActionListener(new CambiaSaludo("Bonjour, bon matin!"));
    }

    // Clase interna para manejar los eventos de los botones
    private class CambiaSaludo implements ActionListener {
        private String sms;

        //Constructor
        public CambiaSaludo(String mensaje) {
            this.sms = mensaje;
        }

        // Método
        public void actionPerformed(ActionEvent e) {
            etiquetaSaludo.setText(sms);
        }
    }
}
