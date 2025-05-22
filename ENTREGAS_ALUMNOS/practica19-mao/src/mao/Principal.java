package mao;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Principal {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelNorte = new JPanel(new FlowLayout());
		JPanel panelCentro = new JPanel(new FlowLayout());
		JPanel panelSur = new JPanel(new FlowLayout());

		panelNorte.add(new JLabel("Mario Álvarez Ortega"));

		panelCentro.add(new JButton("Aceptar"));
		panelCentro.add(new JButton("Cancelar"));

		panelSur.add(new JLabel("Ejercicio de layouts - DAM"));

		frame.add(panelNorte, BorderLayout.NORTH);
		frame.add(panelCentro, BorderLayout.CENTER);
		frame.add(panelSur, BorderLayout.SOUTH);

		frame.pack();
		frame.setVisible(true);
	}

}
