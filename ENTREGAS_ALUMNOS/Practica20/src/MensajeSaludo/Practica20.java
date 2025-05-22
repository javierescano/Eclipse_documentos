package MensajeSaludo;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Practica20 extends JFrame implements ActionListener {
	public static void main(String[] args) {
		new Practica20();

	}
    private JLabel mensajeLabel;

	public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
        case "Español":
            mensajeLabel.setText("¡Hola, buenos días!");
            break;
        case "Ingles":
            mensajeLabel.setText("Hello, good morning!");
            break;
        case "Frances":
            mensajeLabel.setText("Bonjour, bon matin!");
            break;
        default:
            mensajeLabel.setText("Pulsa un botón para saludar");
            break;
    }
}
	public Practica20() {
		JFrame Frame = new JFrame();
		
        setTitle("Boton Saludo");

        
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Frame.setLocation(800,450);
		
		Frame.setPreferredSize(new Dimension(400,400));

		 mensajeLabel = new JLabel("Pulsa un botón para saludar");
		
		Frame.add(mensajeLabel);
		
		JPanel Panel = new JPanel();
		
		
		BoxLayout layout = new BoxLayout(Panel, BoxLayout.X_AXIS);
		
		Panel.setLayout(layout);
		
		
		JButton Español=new JButton("Español");
		JButton Frances=new JButton("Frances");
		JButton Ingles=new JButton("Ingles");
		
		Español.setAlignmentX(Component.LEFT_ALIGNMENT);
		Frances.setAlignmentX(Component.CENTER_ALIGNMENT);
		Ingles.setAlignmentX(Component.RIGHT_ALIGNMENT);


		
		Panel.add(Español);
		Panel.add(Ingles);
		Panel.add(Frances);
		
		Frame.add(Panel,BorderLayout.SOUTH);
		
		
		 Frame.setLayout(new FlowLayout());
		 
		 Frame.pack();
		 
		 Frame.setVisible(true);
		 
		 Español.addActionListener(this);
		 
		 Frances.addActionListener(this);
		 
		 Ingles.addActionListener(this);

	}
	
	
}
	