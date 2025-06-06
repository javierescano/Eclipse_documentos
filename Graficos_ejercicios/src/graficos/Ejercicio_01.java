package graficos;

import javax.swing.*;
import java.awt.*;

public class Ejercicio_01 {

    public static void main(String[] args) {
        // Ejecutar la interfaz gráfica en el hilo de eventos de Swing
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                crearYMostrarGUI();
            }
        });
    }

    private static void crearYMostrarGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("JPanelDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the menu bar. Make it have a green background.
        JMenuBar greenMenuBar = new JMenuBar();
        greenMenuBar.setOpaque(true);
        greenMenuBar.setBackground(new Color(154, 165, 127));
        greenMenuBar.setPreferredSize(new Dimension(200, 20));

        // Create a yellow label to put in the content pane.
        JLabel yellowLabel = new JLabel("Etiqueta amarilla", JLabel.CENTER);
        yellowLabel.setOpaque(true);
        yellowLabel.setBackground(new Color(248, 213, 131));
        yellowLabel.setPreferredSize(new Dimension(200, 180));

        // Create OK button
        JButton btnOk = new JButton();
        // Puedes poner cualquier texto si no tienes la imagen
        btnOk.setText("OK");
        // Si tienes la imagen, descomenta la línea siguiente y asegúrate de que la ruta es correcta
        // ImageIcon imagenBoton = new ImageIcon("img/boton_rojo.png");
        // btnOk.setIcon(imagenBoton);
        btnOk.setBackground(new Color(248, 10, 0));
        btnOk.setFocusable(false);

        // Create a panel and add components to it.
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(yellowLabel, BorderLayout.CENTER);
        contentPane.add(btnOk, BorderLayout.PAGE_END);

        // Set the menu bar and add the label and button to the content pane.
        frame.setJMenuBar(greenMenuBar);
        frame.setContentPane(contentPane);

        // Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null); // Centrar la ventana
        frame.setVisible(true);
    }
}
