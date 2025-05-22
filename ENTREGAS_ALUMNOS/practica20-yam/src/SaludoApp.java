import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SaludoApp extends JFrame {

    private JLabel etiquetaSaludo;

    public SaludoApp() {
        // Configuración de la ventana principal
        setTitle("Saludos en varios idiomas");
        setSize(300, 125);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel para los botones
        JPanel panelBotones = new JPanel();
        JPanel panelBotones2 = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones2.setLayout(new FlowLayout());

        // Crear botones
        JButton botonEspanol = new JButton("Español");
        JButton botonIngles = new JButton("Inglés");
        JButton botonFrances = new JButton("Francés");
        JButton botonAleman = new JButton("Alemán");
        JButton botonItaliano = new JButton("Italiano");
        JButton botonPortugues = new JButton("Portugues");


        // Añadir botones al panel
        panelBotones.add(botonEspanol);
        panelBotones.add(botonIngles);
        panelBotones.add(botonFrances);
        panelBotones2.add(botonAleman);
        panelBotones2.add(botonItaliano);
        panelBotones2.add(botonPortugues);

        // Etiqueta inicial
        etiquetaSaludo = new JLabel("Pulsa un botón para saludar", SwingConstants.CENTER);

        // Añadir componentes al frame
        add(panelBotones, BorderLayout.NORTH);
        add(panelBotones2, BorderLayout.CENTER);
        add(etiquetaSaludo, BorderLayout.SOUTH);

        // Añadir ActionListener con clase interna
        botonEspanol.addActionListener(new BotonListener("¡Hola, buenos días!", "RED"));
        botonIngles.addActionListener(new BotonListener("Hello, good morning!", "BLUE"));
        botonFrances.addActionListener(new BotonListener("Bonjour, bon matin!", "GRAY"));
        botonAleman.addActionListener(new BotonListener("Hallo guten Morgen!", "ORANGE"));
        botonItaliano.addActionListener(new BotonListener("Ciao, buongiorno!", "GREEN"));
        botonPortugues.addActionListener(new BotonListener("Olá, bom dia!", "YELLOW"));
    }

    // Clase interna que implementa ActionListener
    private class BotonListener implements ActionListener {
        private String mensaje;
        private String color;


        public BotonListener(String mensaje, String color) {
            this.mensaje = mensaje;
            this.color = color;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            etiquetaSaludo.setText(mensaje);
            if (color.equals("RED")) {
            	etiquetaSaludo.setForeground(Color.RED); 
            } else if (color.equals("BLUE")) {
            	etiquetaSaludo.setForeground(Color.BLUE); 
            } else if (color.equals("GRAY")) {
            	etiquetaSaludo.setForeground(Color.GRAY); 
            } else if (color.equals("ORANGE")) {
            	etiquetaSaludo.setForeground(Color.ORANGE); 
            } else if (color.equals("GREEN")) {
            	etiquetaSaludo.setForeground(Color.GREEN); 
            } else if (color.equals("YELLOW")) {
            	etiquetaSaludo.setForeground(Color.YELLOW); 
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SaludoApp app = new SaludoApp();
            app.setVisible(true);
        });
    }
}






