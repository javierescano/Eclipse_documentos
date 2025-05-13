//Programa sensible a eventos de raton con todos sus listener


package graficos;

import javax.swing.*;
import java.awt.event.*;

public class Eventos_5a extends JFrame implements MouseListener {

    public Eventos_5a() {
        setTitle("Ejemplo MouseListener");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Registrar el listener en el JFrame
        this.addMouseListener(this);
        setVisible(true);
    }
    // Obligatorio implementar todos los métodos de MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("1.");}
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("2.");}
    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("3.");}
        // Método vacío, pero debe estar presente}
    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("4.");}
    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("5.");}
    	
    public static void main(String[] args) {
        new Eventos_5a();
    }
}
