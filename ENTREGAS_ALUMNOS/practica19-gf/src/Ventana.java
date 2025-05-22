import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame{
	public Ventana() {		
		this.setLayout(new BorderLayout());
		Container contenido = this.getContentPane();
		
		JPanel norte = new JPanel(new FlowLayout());
		JPanel centro = new JPanel(new FlowLayout());
		JPanel sur = new JPanel(new FlowLayout());
		
		JLabel lbN = new JLabel("Gaojie Fu");
		norte.add(lbN);
		this.add(norte, BorderLayout.PAGE_START);
		
		JButton btAceptar = new JButton("Aceptar");
		JButton btCancelar = new JButton("Cancelar");
		centro.add(btAceptar);
		centro.add(btCancelar);
		this.add(centro, BorderLayout.CENTER);
		
		JLabel lbS = new JLabel("Ejercicio de Layouts - DAM");
		sur.add(lbS);
		this.add(sur, BorderLayout.PAGE_END);
		
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
