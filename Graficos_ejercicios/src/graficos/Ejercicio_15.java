//JFileChooser 

package graficos;

import javax.swing.JFrame;
import javax.swing.JFileChooser;
import java.io.File;

public class Ejercicio_15 {

	
    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Programación Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 200);
        frame.setLocationRelativeTo(null); // Centrar ventana

        // Mostrar la ventana (opcional si solo quieres el diálogo)
        frame.setVisible(true);

        // Crear el JFileChooser
        JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(true);
        fc.setCurrentDirectory(new File("C:\\Users\\Héctor\\Documents"));

        // Mostrar el diálogo de selección de archivos
        int retVal = fc.showOpenDialog(frame);

        // Procesar la selección
        if (retVal == JFileChooser.APPROVE_OPTION) {
            File[] archivosSeleccionados = fc.getSelectedFiles();
            System.out.println("Archivos seleccionados:");
            for (File archivo : archivosSeleccionados) {
                System.out.println(archivo.getAbsolutePath());
            }
        } else {
            System.out.println("No se seleccionó ningún archivo.");
        }
    }
}

