import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Practica20 extends JFrame {

	public static void main(String[] args) {
		Practica20 app = new Practica20();
		app.ventana();
	}

	public void ventana() {
		JFrame frame = new JFrame("Practica 20");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		JPanel norte = new JPanel(new FlowLayout(FlowLayout.CENTER));
		norte.setBackground(new Color(124, 252, 0));

		JLabel titulo = new JLabel("Idiomas");
		norte.add(titulo);
		frame.add(norte, BorderLayout.NORTH);

		JPanel centro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 80));
		centro.setBackground(new Color(128, 0, 128));

		JButton castellano = new JButton("Español");
		JButton ingles = new JButton("Inglés");
		JButton frances = new JButton("Francés");

		centro.add(castellano);
		centro.add(ingles);
		centro.add(frances);

		frame.add(centro, BorderLayout.CENTER);

		JPanel sur = new JPanel(new FlowLayout(FlowLayout.CENTER));
		sur.setBackground(new Color(124, 252, 0));

		JLabel etiquetaSaludo = new JLabel("Pulsa un botón para saludar");
		sur.add(etiquetaSaludo);
		frame.add(sur, BorderLayout.SOUTH);

		class Manejador implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case "Español" -> etiquetaSaludo.setText("¡Hola, buenos días!");
				case "Inglés" -> etiquetaSaludo.setText("Hello, good morning!");
				case "Francés" -> etiquetaSaludo.setText("Bonjour, bon matin!");
				}
			}
		}
		Manejador manejador = new Manejador();
		castellano.addActionListener(manejador);
		ingles.addActionListener(manejador);
		frances.addActionListener(manejador);

		frame.setSize(new Dimension(500, 300));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
