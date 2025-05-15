package graficos;

public class Eventos_JuegoNumeros {

}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Eventos_JuegoNumeros extends JFrame {
    private final int NUM_NUMEROS = 5;
    private JButton[] botonesNumeros = new JButton[NUM_NUMEROS];
    private ArrayList<Integer> secuencia = new ArrayList<>();
    private ArrayList<Integer> intento = new ArrayList<>();
    private JButton btnIniciar = new JButton("Iniciar");
    private JButton btnComprobar = new JButton("Comprobar");

    public Eventos_JuegoNumeros() {
        setTitle("Juego de Memoria con Números");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel panelNumeros = new JPanel(new GridLayout(1, NUM_NUMEROS));
        for(int i = 0; i < NUM_NUMEROS; i++) {
            botonesNumeros[i] = new JButton();
            botonesNumeros[i].setEnabled(false);
            panelNumeros.add(botonesNumeros[i]);
        }

        JPanel panelControles = new JPanel();
        panelControles.add(btnIniciar);
        panelControles.add(btnComprobar);

        add(panelNumeros, BorderLayout.CENTER);
        add(panelControles, BorderLayout.SOUTH);

        btnIniciar.addActionListener(e -> iniciarJuego());
        btnComprobar.addActionListener(e -> comprobarSecuencia());

        for(JButton boton : botonesNumeros) {
            boton.addActionListener(e -> {
                JButton origen = (JButton)e.getSource();
                intento.add(Arrays.asList(botonesNumeros).indexOf(origen));
                origen.setText(String.valueOf(intento.size()));
            });
        }

        pack();
        setSize(600, 200);
        setLocationRelativeTo(null);
    }

    private void iniciarJuego() {
        secuencia.clear();
        intento.clear();
        Random rand = new Random();
        
        for(int i = 0; i < NUM_NUMEROS; i++) {
            secuencia.add(rand.nextInt(101)); // números entre 0 y 100
            botonesNumeros[i].setText(String.valueOf(secuencia.get(i)));
            botonesNumeros[i].setEnabled(false);
        }

        Timer timer = new Timer(2000, e -> {
            for(int i = 0; i < NUM_NUMEROS; i++) {
                botonesNumeros[i].setText("?");
                botonesNumeros[i].setEnabled(true);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void comprobarSecuencia() {
        boolean correcto = true;
        if (intento.size() != NUM_NUMEROS) {
            correcto = false;
        } else {
            for(int i = 0; i < NUM_NUMEROS; i++) {
                int indice = intento.get(i);
                if(secuencia.get(i) != secuencia.get(indice)) {
                    correcto = false;
                    break;
                }
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
        SwingUtilities.invokeLater(() -> new Eventos_JuegoNumeros().setVisible(true));
    }
}
