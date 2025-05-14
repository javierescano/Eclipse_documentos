package graficos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Contador extends JFrame {
    private int contador = 0;
    private JLabel etiquetaContador;
    private int paso = 1; // Valor por defecto para el paso

    public Contador() {
        setTitle("Contador Multieventos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        // Etiqueta del contador
        etiquetaContador = new JLabel(String.valueOf(contador), SwingConstants.CENTER);
        etiquetaContador.setFont(new Font("Arial", Font.BOLD, 40));

        // Botones
        JButton btnIncrementar = new JButton("Incrementar (+1)");
        JButton btnDecrementar = new JButton("Decrementar (-1)");

        // Combo box para seleccionar el paso
        String[] pasos = {"Paso 1", "Paso 5", "Paso 10"};
        JComboBox<String> comboPasos = new JComboBox<>(pasos);

        // Añadir componentes
        add(etiquetaContador);
        add(btnIncrementar);
        add(btnDecrementar);
        add(comboPasos);

        // ===== MANEJADORES DE EVENTOS =====

        // Evento de clic en botones
        btnIncrementar.addActionListener(e -> actualizarContador(paso));
        btnDecrementar.addActionListener(e -> actualizarContador(-paso));

        // Evento de teclado (teclas + y -)
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() =='a') actualizarContador(paso);
                if (e.getKeyChar() == 'z') actualizarContador(-paso);
            }
        });

        // Evento de cambio en el combo box
        comboPasos.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String seleccion = (String) comboPasos.getSelectedItem();
                switch (seleccion) {
                    case "Paso 1": paso = 1; break;
                    case "Paso 5": paso = 5; break;
                    case "Paso 10": paso = 10; break;
                }
            }
        });

        // Configuración final
        setFocusable(true); // Para que funcione el KeyListener
        pack();
        setSize(300, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void actualizarContador(int cambio) {
        contador += cambio;
        etiquetaContador.setText(String.valueOf(contador));
    }

    public static void main(String[] args) {
        // Mejorar aspecto en sistemas modernos
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        new Contador();
    }
}
