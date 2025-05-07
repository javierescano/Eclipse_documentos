// JTree

package graficos;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Ejercicio_13 {

    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Programación Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear el nodo raíz y el modelo del árbol
        DefaultMutableTreeNode abuelo = new DefaultMutableTreeNode("abuelo");
        DefaultTreeModel modelo = new DefaultTreeModel(abuelo);

        // Crear el JTree con el modelo
        JTree tree = new JTree(modelo);

        // Crear nodos hijos y nietos
        DefaultMutableTreeNode padre = new DefaultMutableTreeNode("padre");
        DefaultMutableTreeNode tio = new DefaultMutableTreeNode("tio");
        DefaultMutableTreeNode hijo = new DefaultMutableTreeNode("hijo");
        DefaultMutableTreeNode hija = new DefaultMutableTreeNode("hija");

        // Insertar nodos en el modelo en el orden y ubicación deseados.
        modelo.insertNodeInto(padre, abuelo, 0);
        modelo.insertNodeInto(tio, abuelo, 1);
        modelo.insertNodeInto(hijo, padre, 0);
        modelo.insertNodeInto(hija, padre, 1);

        // Añadir el árbol al contenido del frame
        frame.getContentPane().add(tree);

        // Configurar tamaño y hacer visible la ventana
        frame.setSize(250, 250);
        frame.setLocationRelativeTo(null); // Centrar ventana
        frame.setVisible(true);
    }
}
