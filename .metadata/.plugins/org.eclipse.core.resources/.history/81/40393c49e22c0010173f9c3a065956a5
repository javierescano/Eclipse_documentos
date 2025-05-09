package graficos;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Eventos_4 {
	
		public static void main (String[] args) {
			
			MarcoBotones4 mimarco=new MarcoBotones4();
			
			mimarco.setVisible(true);
			
			mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}
}

class MarcoBotones4 extends JFrame{
	
	public MarcoBotones4() {
		
		setTitle("Eventos y botones6");
		
		setBounds(400,300,500,400);
		
		LaminaBotones4 milamina= new LaminaBotones4();
		
		add(milamina);
		
	}
}

class LaminaBotones4 extends JPanel{
	
	JButton botonAzul=new JButton("Azul");

	JButton botonAmarillo=new JButton("Amarillo");
	
	JButton botonRojo=new JButton("Rojo");
	
	public LaminaBotones4(){
		
		add(botonAzul);
		
		add(botonAmarillo);
		
		add(botonRojo);
		
		//
		ColorFondo Amarillo=new ColorFondo(Color.yellow);
		
		ColorFondo Rojo=new ColorFondo(Color.red);
		
		ColorFondo Azul=new ColorFondo(Color.blue);
		
		botonAzul.addActionListener(Azul);

		botonAmarillo.addActionListener(Amarillo);
		
		botonRojo.addActionListener(Rojo);
		
	}
	
	private class ColorFondo implements ActionListener{
		
		public ColorFondo(Color c) {
			
			colorDeFondo=c;
			
		}
		
		public void actionPerformed (ActionEvent e) {
			
			setBackground(colorDeFondo);
		}
		
		private Color colorDeFondo;
	}
	
}

