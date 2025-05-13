package graficos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Practica_20 extends JFrame {

    private JLabel etiquetaSaludo;
    private JButton btnEspanol, btnIngles, btnFrances;

    public Practica_20() {
        super("Saludador Multilingüe");

        setLayout(new FlowLayout());

        // Configuracion la frame
        setSize(300, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla
        setVisible(true);
        setResizable(false);
        
        // Creo botones
        btnEspanol = new JButton("Español");
        btnIngles = new JButton("Inglés");
        btnFrances = new JButton("Francés");

        // Creo etiqueta inicial
        etiquetaSaludo = new JLabel("Pulsa un botón para saludar");
        etiquetaSaludo.setPreferredSize(new Dimension(250, 30));
        etiquetaSaludo.setHorizontalAlignment(SwingConstants.CENTER);

        // Añado componentes a la ventana
        add(btnEspanol);
        add(btnIngles);
        add(btnFrances);
        add(etiquetaSaludo);

        // Asignamos el mismo listener a todos los botones
        ManejadorEventos manejador = new ManejadorEventos();
        btnEspanol.addActionListener(manejador);
        btnIngles.addActionListener(manejador);
        btnFrances.addActionListener(manejador);
    }

    // Clase interna para manejar eventos de los botones
    private class ManejadorEventos implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object fuente = e.getSource();

            if (fuente == btnEspanol) {
                etiquetaSaludo.setText("¡Hola, buenos días!");
                etiquetaSaludo.setForeground(new Color(204, 0, 0)); // Rojo
            } else if (fuente == btnIngles) {
                etiquetaSaludo.setText("Hello, good morning!");
                etiquetaSaludo.setForeground(new Color(0, 0, 204)); // Azul
            } else if (fuente == btnFrances) {
                etiquetaSaludo.setText("Bonjour, bon matin!");
                etiquetaSaludo.setForeground(new Color(0, 153, 0)); // Verde
            }
        }
    }

    public static void main(String[] args) {
        new Practica_20();
    }
}

