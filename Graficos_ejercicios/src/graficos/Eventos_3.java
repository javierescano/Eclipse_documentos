package graficos;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Eventos_3 {
	
		public static void main (String[] args) {
			
			MarcoBotones3 mimarco=new MarcoBotones3();
			
			mimarco.setVisible(true);
			
			mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
	}
}

class MarcoBotones3 extends JFrame{
	
	public MarcoBotones3() {
		
		setTitle("Eventos y botones6");
		
		setBounds(400,300,500,400);
		
		LaminaBotones3 milamina= new LaminaBotones3();
		
		add(milamina);
		
	}
}

class LaminaBotones3 extends JPanel implements ActionListener{
	
	JButton botonAzul=new JButton("Azul");

	JButton botonAmarillo=new JButton("Amarillo");
	
	JButton botonRojo=new JButton("Rojo");
	
	public LaminaBotones3(){
		
		add(botonAzul);
		
		add(botonAmarillo);
		
		add(botonRojo);
		
		
		botonAzul.addActionListener(this);

		botonAmarillo.addActionListener(this);
		
		botonRojo.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Object botonPulsado=e.getSource();
		
		if (botonPulsado==botonAzul) {
			
			setBackground(Color.blue);
			}
			else if (botonPulsado==botonAmarillo) {
				
				setBackground(Color.yellow);
			}
		
			else {
				setBackground(Color.red);
			}
		}
	
}