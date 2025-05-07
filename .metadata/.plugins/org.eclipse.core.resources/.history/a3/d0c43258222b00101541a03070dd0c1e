//Diapositiva 33. JButton

package graficos;

import javax.swing.*;
import java.awt.event.*;

public class Ejercicio_5 {
	
	    public static void main(String[] args) {

	        JFrame frame = new JFrame("Programación Demo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        ImageIcon leftButtonIcon = new ImageIcon("img/right.gif");
	        ImageIcon middleButtonIcon = new ImageIcon("img/middle.gif");
	        ImageIcon rightButtonIcon = new ImageIcon("img/left.gif");

	        JButton b1 = new JButton("Botón habilitado", leftButtonIcon);
	        b1.setVerticalTextPosition(AbstractButton.CENTER);
	        b1.setHorizontalTextPosition(AbstractButton.LEADING);
	        b1.setMnemonic(KeyEvent.VK_D);

	        JButton b2 = new JButton("Botón del medio", middleButtonIcon);
	        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
	        b2.setHorizontalTextPosition(AbstractButton.CENTER);
	        b2.setMnemonic(KeyEvent.VK_M);

	        JButton b3 = new JButton("Botón deshabilitado", rightButtonIcon);
	        b3.setMnemonic(KeyEvent.VK_E);
	        b3.setEnabled(false);

	        b1.setToolTipText("Mensaje de ayuda Botón 1");
	        b2.setToolTipText("Mensaje de ayuda Botón 2");
	        b3.setToolTipText("Mensaje de ayuda Botón 3");

	        JPanel contentPane = new JPanel();
	        contentPane.add(b1);
	        contentPane.add(b2);
	        contentPane.add(b3);
	        frame.setContentPane(contentPane);

	        frame.pack();
	        frame.setVisible(true);
	    }
	}
