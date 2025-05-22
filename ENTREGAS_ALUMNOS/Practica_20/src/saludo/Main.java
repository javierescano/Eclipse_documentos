package saludo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    private JLabel etiquetaSaludo;

    public Main() {
        setTitle("Saludos en diferentes idiomas");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        etiquetaSaludo = new JLabel("Pulsa un botón para saludar", JLabel.CENTER);
        etiquetaSaludo.setFont(new Font("Arial", Font.BOLD, 16));
        etiquetaSaludo.setPreferredSize(new Dimension(400, 100));


        JPanel panelBotones = new JPanel(new FlowLayout());

        JButton botonEspanol = new JButton("Español");
        JButton botonIngles = new JButton("Inglés");
        JButton botonFrances = new JButton("Francés");
        JButton botonAleman = new JButton("Alemán");
        JButton botonItaliano = new JButton("Italiano");


        botonEspanol.addActionListener(new ManejadorBotones("¡Hola, buenos días!", Color.RED));
        botonIngles.addActionListener(new ManejadorBotones("Hello, good morning!", Color.BLUE));
        botonFrances.addActionListener(new ManejadorBotones("Bonjour, bon matin!", Color.MAGENTA));
        botonAleman.addActionListener(new ManejadorBotones("Guten Morgen!", Color.GREEN));
        botonItaliano.addActionListener(new ManejadorBotones("Buongiorno!", Color.ORANGE));

        
        panelBotones.add(botonEspanol);
        panelBotones.add(botonIngles);
        panelBotones.add(botonFrances);
        panelBotones.add(botonAleman);
        panelBotones.add(botonItaliano);

        
        add(panelBotones, BorderLayout.NORTH);
        add(etiquetaSaludo, BorderLayout.CENTER);

        setVisible(true);
    }


    private class ManejadorBotones implements ActionListener {
        private String saludo;
        private Color color;

        public ManejadorBotones(String saludo, Color color) {
            this.saludo = saludo;
            this.color = color;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            etiquetaSaludo.setText(saludo);
            etiquetaSaludo.setForeground(color);
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}