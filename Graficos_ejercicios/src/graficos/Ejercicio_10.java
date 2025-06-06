//Diapositiva 39. JSlider

package graficos;

import javax.swing.*;

public class Ejercicio_10 {

	public static void main(String[] args) {
        JFrame frame = new JFrame("Programación Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 
        //Crea un slider horizontal, valor mínimo = 0, máximo = 40, valor inicial = 10
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 40, 10);
        
        //Establece separación entre las marcas menores (líneas pequeñas)
        slider.setMinorTickSpacing(5);
        
        //Establece la separación entre las marcas mayores (líneas largas y con etiqueta)
        slider.setMajorTickSpacing(20);
        
        //Dibujar marcas menores y mayores en el slider.
        slider.setPaintTicks(true);
        
        //Dibujar etiquetas numéricas junto a las marcas mayores en el slider.
        slider.setPaintLabels(true);
        
        //Añadir etiquetas numéricas personalizadas al slider.
        slider.setLabelTable(slider.createStandardLabels(10));

        
        JPanel contentPane = new JPanel();
        contentPane.add(slider);
        frame.setContentPane(contentPane);

        frame.setSize(400, 150);
        frame.setVisible(true);
	    }
	}
