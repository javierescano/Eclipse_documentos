package graficos;

import javax.swing.*;
//import java.awt.*;

public class Eventos_1 {
	
		public static void main(String[] args) {
			
			MarcoBotones1 mimarco=new MarcoBotones1();
			
			mimarco.setVisible(true);
			
			mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}
}

class MarcoBotones1 extends JFrame{
	
	public MarcoBotones1() {
		
		setTitle("Eventos y botones_1");
		
		setBounds(400,300,500,400);
		
		LaminaBotones1 milamina= new LaminaBotones1();
		
		add(milamina);
		
	}
}

class LaminaBotones1 extends JPanel{
	
}