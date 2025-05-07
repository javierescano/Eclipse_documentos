package graficos;

import javax.swing.*;
import java.awt.*;

// Grid Layout

public class Ejercicio_03 {

    public static void main(String[] args) {
        // Ejecutar la GUI en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> crearYMostrarGUI());
    }

    private static void crearYMostrarGUI() {
        JFrame frame = new JFrame("GridLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contenido = frame.getContentPane();

        // Panel principal con GridLayout 2x2
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.setPreferredSize(new Dimension(365, 100));

        panel.add(new JButton("Botón 1"));
        panel.add(new JButton("Botón 2"));
        panel.add(new JButton("Botón 3"));
        panel.add(new JButton("Botón 4 con nombre largo"));

        // Panel de controles con GridLayout 2x4
        JPanel controles = new JPanel();
        controles.setLayout(new GridLayout(2, 4));

        JComboBox<String> combobox1 = new JComboBox<>(new String[]{"0", "10", "15", "20"});
        JComboBox<String> combobox2 = new JComboBox<>(new String[]{"0", "5", "10", "15", "20"});
        JButton aplicarButton = new JButton("Calcular");

        controles.add(new JLabel("Ataque:"));
        controles.add(combobox1);
        controles.add(new JLabel("Defensa:"));
        controles.add(combobox2);
        controles.add(new JLabel(""));
        controles.add(new JLabel(""));
        controles.add(new JLabel(""));
        controles.add(aplicarButton);

        // Añadir componentes al contenido del frame
        contenido.add(panel, BorderLayout.PAGE_START);
        contenido.add(new JSeparator(), BorderLayout.CENTER);
        contenido.add(controles, BorderLayout.PAGE_END);

        frame.pack();
        frame.setLocationRelativeTo(null); // Centrar ventana
        frame.setVisible(true);
    }
}
