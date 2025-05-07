//JMenuBar

package graficos;

import javax.swing.*;
import java.awt.event.*;

public class Ejercicio_16 {

    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Programación Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear la barra de menú
        JMenuBar menuBar = new JMenuBar();

        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        // Primer menú
        menu = new JMenu("Primer menú");
        menu.setMnemonic(KeyEvent.VK_P);
        menuBar.add(menu);

        // Item de texto solamente
        menuItem = new JMenuItem("A text-only menu item", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Has seleccionado: A text-only menu item"));
        menu.add(menuItem);

        // Item con texto e icono
        // Nota: Asegúrate de que la ruta de la imagen es correcta o usa un icono propio
        ImageIcon icon = new ImageIcon("img/middle.gif");
        menuItem = new JMenuItem("Both text and icon", icon);
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Has seleccionado: Both text and icon"));
        menu.add(menuItem);

        // Item con solo icono
        menuItem = new JMenuItem(icon);
        menuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Has seleccionado: Icon only item"));
        menu.add(menuItem);

        menu.addSeparator();

        // Grupo de botones de radio
        ButtonGroup group = new ButtonGroup();

        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        rbMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Has seleccionado: Radio button 1"));
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Another one");
        rbMenuItem.setMnemonic(KeyEvent.VK_0);
        rbMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Has seleccionado: Radio button 2"));
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        menu.addSeparator();

        // Check box menu items
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        cbMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Has toggled: Check box 1"));
        menu.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        cbMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Has toggled: Check box 2"));
        menu.add(cbMenuItem);

        menu.addSeparator();

        // Submenú
        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem("An item in the submenu");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Has seleccionado: Item en submenu 1"));
        submenu.add(menuItem);

        menuItem = new JMenuItem("Another item");
        menuItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Has seleccionado: Item en submenu 2"));
        submenu.add(menuItem);

        menu.add(submenu);

        // Segundo menú
        menu = new JMenu("Segundo menú");
        menu.setMnemonic(KeyEvent.VK_N);
        menuBar.add(menu);

        // Puedes añadir items al segundo menú si quieres

        // Añadir la barra de menú a la ventana
        frame.setJMenuBar(menuBar);

        // Configurar tamaño y mostrar ventana
        frame.setSize(550, 200);
        frame.setLocationRelativeTo(null); // Centrar ventana
        frame.setVisible(true);
    }
}
