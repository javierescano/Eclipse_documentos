// JTable


package graficos;

import javax.swing.*;
import java.awt.*;

public class Ejercicio_14 {

    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Programación Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Definir columnas y datos para la tabla
        String[] columns = {"Code", "Name", "High", "Low", "Close", "Volume", "Change", "Change %"};
        Object[][] data = {
            {"MBF", "CITYGROUP", 10.16, 10.16, 10.16, 200, 0.08, 0.79},
            {"MBL", "BANK OF AMERICA", 12.66, 12.66, 12.66, 6600, 0.13, 1.04},
            {"MJP", "Morgan Stanley Dean Witter & Co.", 24.97, 24.97, 24.97, 1000, -0.04, -0.16}
        };

        // Crear la tabla con los datos y columnas
        JTable table = new JTable(data, columns);
        table.setFillsViewportHeight(true);

        // Crear un scroll pane para la tabla
        JScrollPane scrollPane = new JScrollPane(table);

        // Crear una etiqueta para el título
        JLabel lblHeading = new JLabel("Acciones:");
        lblHeading.setFont(new Font("Arial", Font.TRUETYPE_FONT, 24));

        // Configurar el layout del frame y añadir componentes
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(lblHeading, BorderLayout.PAGE_START);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Configurar tamaño y hacer visible la ventana
        frame.setSize(550, 200);
        frame.setLocationRelativeTo(null); // Centrar ventana
        frame.setVisible(true);
    }
}

