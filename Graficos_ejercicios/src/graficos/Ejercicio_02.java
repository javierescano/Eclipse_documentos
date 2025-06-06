package graficos;

import javax.swing.*;
import java.awt.*;

//Box layout
	public class Ejercicio_02 {

	    public static void main(String[] args) {
	        // Ejecutar la GUI en el hilo de eventos de Swing
	        SwingUtilities.invokeLater(() -> crearYMostrarGUI());
	    }

	    private static void crearYMostrarGUI() {
	        JFrame frame = new JFrame("BoxLayout");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        // Crear botones
	        JButton jb1 = new JButton("Botón 1 -->");
	        JButton jb2 = new JButton("Botón 2 -->");
	        JButton jb3 = new JButton("Botón 3 -->");
	        JButton jb4 = new JButton("Botón 4 -->");
	        JButton jb5 = new JButton("Botón 5 -->");
	        JButton jb6 = new JButton("Botón 6 -->");

	        // Crear paneles
	        JPanel panel1 = new JPanel();
	        JPanel panel2 = new JPanel();
	        JPanel panel3 = new JPanel();

	        // Añadir bordes titulados
	        panel1.setBorder(BorderFactory.createTitledBorder("LEFT"));
	        panel2.setBorder(BorderFactory.createTitledBorder("CENTER"));
	        panel3.setBorder(BorderFactory.createTitledBorder("RIGHT"));

	        // Configurar alineación de botones y añadir a paneles
	        jb1.setAlignmentX(Component.LEFT_ALIGNMENT);
	        jb2.setAlignmentX(Component.LEFT_ALIGNMENT);
	        panel1.add(jb1);
	        panel1.add(jb2);

	        jb3.setAlignmentX(Component.CENTER_ALIGNMENT);
	        jb4.setAlignmentX(Component.CENTER_ALIGNMENT);
	        panel2.add(jb3);
	        panel2.add(jb4);

	        jb5.setAlignmentX(Component.RIGHT_ALIGNMENT);
	        jb6.setAlignmentX(Component.RIGHT_ALIGNMENT);
	        panel3.add(jb5);
	        panel3.add(jb6);

	        // Establecer BoxLayout vertical en cada panel
	        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
	        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
	        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

	        // Establecer layout del frame y añadir paneles
	        frame.setLayout(new FlowLayout());
	        frame.add(panel1);
	        frame.add(panel2);
	        frame.add(panel3);

	        // Ajustar tamaño y mostrar ventana
	        frame.pack();
	        frame.setLocationRelativeTo(null); // Centrar ventana
	        frame.setVisible(true);
	    }
	}

