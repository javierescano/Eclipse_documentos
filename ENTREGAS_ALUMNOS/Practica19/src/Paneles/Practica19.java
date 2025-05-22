package Paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Practica19 extends JFrame{
	//Zona Norte
	private JLabel labelNorte;
	private JPanel panelNorte;
	//Zona Centro
	private JButton botonAceptar;
	private JButton botonCancelar;
	private JPanel panelCentro;
	//Zona Sur
	private JLabel labelSur;
	private JPanel panelSur;
	
	
	
	public static void main(String[] args) {
		new Practica19();
	}
	public Practica19() {
		
		/**
		 * Configuramos las opciones basicas del Jframe
		 */
		setTitle("Layout_Ejercicios");
		setSize(400,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		//Creamos Los paneles y las etiquetas de texto de los diferentes paneles
		labelNorte = new JLabel("Javier Cereceda Gómez");
		panelNorte = new JPanel(new FlowLayout());
		panelNorte.add(labelNorte);
		
		botonAceptar = new JButton("Aceptar");
		botonCancelar = new JButton("Cancelar");
		panelCentro = new JPanel(new FlowLayout());
		
		panelCentro.add(botonAceptar);
		panelCentro.add(botonCancelar);
		
		labelSur = new JLabel("“Ejercicio de layouts - DAM”.");
		panelSur = new JPanel(new FlowLayout());
		
		panelSur.add(labelSur);
		
		// Añadimos los paneles al Frame
		add(panelSur,BorderLayout.SOUTH);
		add(panelCentro,BorderLayout.CENTER);
		add(panelNorte,BorderLayout.NORTH);
		
		setVisible(true);
		
	}
	
	
}
