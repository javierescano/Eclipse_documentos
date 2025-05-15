package graficos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Eventos_JuegoCartas extends JFrame {
    private final int NUM_CARTAS = 5;
    private JButton[] botonesCartas = new JButton[NUM_CARTAS];
    private ArrayList<Integer> secuencia = new ArrayList<>();
    private ArrayList<Integer> intento = new ArrayList<>();
    private JButton btnIniciar = new JButton("Iniciar");
    private JButton btnComprobar = new JButton("Comprobar");

    public Eventos_JuegoCartas() {
        setTitle("Juego de Memoria");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Panel de cartas
        JPanel panelCartas = new JPanel(new GridLayout(1, NUM_CARTAS));
        for(int i = 0; i < NUM_CARTAS; i++) {
            botonesCartas[i] = new JButton();
            botonesCartas[i].setEnabled(false);
            panelCartas.add(botonesCartas[i]);
        }

        // Panel de controles
        JPanel panelControles = new JPanel();
        panelControles.add(btnIniciar);
        panelControles.add(btnComprobar);

        add(panelCartas, BorderLayout.CENTER);
        add(panelControles, BorderLayout.SOUTH);

        // Lógica del juego
        btnIniciar.addActionListener(e -> iniciarJuego());
        btnComprobar.addActionListener(e -> comprobarSecuencia());

        for(JButton boton : botonesCartas) {
            boton.addActionListener(e -> {
                JButton origen = (JButton)e.getSource();
                intento.add(Arrays.asList(botonesCartas).indexOf(origen) + 1);
                origen.setText(String.valueOf(intento.size()));
            });
        }

        pack();
        setSize(500, 200);
        setLocationRelativeTo(null);
    }

    private void iniciarJuego() {
        secuencia.clear();
        intento.clear();
        Random rand = new Random();
        
        // Generar secuencia aleatoria
        for(int i = 0; i < NUM_CARTAS; i++) {
            secuencia.add(i + 1);
            botonesCartas[i].setText(String.valueOf(i + 1));
        }
        Collections.shuffle(secuencia);

        // Mostrar secuencia inicial
        Timer timer = new Timer(1000, e -> {
            for(int i = 0; i < NUM_CARTAS; i++) {
                botonesCartas[i].setText(String.valueOf(secuencia.get(i)));
                botonesCartas[i].setEnabled(false);
            }
        });
        timer.setRepeats(false);
        timer.start();

        // Preparar para el intento
        Timer timer2 = new Timer(2000, e -> {
            for(int i = 0; i < NUM_CARTAS; i++) {
                botonesCartas[i].setText("?");
                botonesCartas[i].setEnabled(true);
            }
        });
        timer2.setRepeats(false);
        timer2.start();
    }

    private void comprobarSecuencia() {
        boolean correcto = true;
        for(int i = 0; i < NUM_CARTAS; i++) {
            if(intento.size() <= i || !intento.get(i).equals(secuencia.get(i))) {
                correcto = false;
                break;
            }
        }
        
        if(correcto) {
            JOptionPane.showMessageDialog(this, "¡Correcto! ¡Buen trabajo!");
        } else {
            JOptionPane.showMessageDialog(this, "Incorrecto. Inténtalo de nuevo.");
        }
        iniciarJuego();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Eventos_JuegoCartas().setVisible(true));
    }
}
