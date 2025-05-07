package graficos;

import javax.swing.*;
import java.awt.*;

public class Ejercicio_00 {
    public static void main(String[] args) {
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("ToplevelDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the menu bar. Make it have a green background.
        JMenuBar greenMenuBar = new JMenuBar();
        greenMenuBar.setOpaque(true);
        greenMenuBar.setBackground(new Color(154, 165, 127));
        greenMenuBar.setPreferredSize(new Dimension(200, 20));

        // Create a yellow label to put in the content pane.
        JLabel yellowLabel = new JLabel();
        yellowLabel.setOpaque(true);
        yellowLabel.setBackground(new Color(248, 213, 131));
        yellowLabel.setPreferredSize(new Dimension(200, 180));

        // Create OK button
        JButton btnOK = new JButton("OK");
        btnOK.setBackground(new Color(254, 8, 2));

        // Set the menu bar and add the label and button to the content pane.
        frame.setJMenuBar(greenMenuBar);
        frame.getContentPane().add(yellowLabel);
        frame.getContentPane().add(btnOK, BorderLayout.PAGE_END);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
