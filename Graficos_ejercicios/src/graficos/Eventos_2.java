package graficos;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Eventos_2 {
	
		public static void main (String[] args) {
			
			MarcoBotones2 mimarco=new MarcoBotones2();
			
			mimarco.setVisible(true);
			
			mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}
}

class MarcoBotones2 extends JFrame{
	
	public MarcoBotones2() {
		
		setTitle("Eventos y botones5");
		
		setBounds(400,300,500,400);
		
		LaminaBotones2 milamina= new LaminaBotones2();
		
		add(milamina);
		
	}
}

class LaminaBotones2 extends JPanel implements ActionListener{
	
	JButton botonAzul=new JButton("Azul");
	
	public LaminaBotones2(){
		
		add(botonAzul);
		
		botonAzul.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
			
		setBackground(Color.blue);
		
	}
	
}