package graficos;

import javax.swing.*;
import java.awt.*;

public class Practica_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Instanciamos
		MarcoConTexto mimarco = new MarcoConTexto();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}

class MarcoConTexto extends JFrame{
	
	public MarcoConTexto() {
		
		setVisible(true);
		
		setSize(600,400);
		
		setLocation(400,200);
		
		setTitle("Marco con texto");
		
		Lamina miLamina=new Lamina();
		
		add(miLamina);
	}
}


class Lamina extends JPanel {
	
		public void paintComponent(Graphics g) {
			
		super.paintComponent(g);
		
		g.drawString("Hola mundo", 100, 100);
	}
}