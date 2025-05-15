package graficos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Eventos_ahorcado extends JFrame {
    // Componentes de la primera pantalla
    private JTextField inputPalabra;
    private JButton botonEmpezar;
    private JLabel labelInstruccion;
    private JLabel labelAdvertencia;

    // Componentes de la segunda pantalla
    private JPanel panelJuego;
    private JLabel labelHuecos;
    private JTextField inputLetra;
    private JButton botonProbar;
    private JLabel labelIntentos;
    private JLabel labelMensaje;

    // Lógica del juego
    private String palabraSecreta;
    private char[] palabraAdivinada;
    private int intentosFallidos;
    private int intentosMaximos;

    public Eventos_ahorcado() {
        setTitle("Ahorcado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // --------- Pantalla de introducción de palabra ---------
        JPanel panelInicio = new JPanel();
        panelInicio.setLayout(new GridLayout(4, 1, 5, 5));
        labelInstruccion = new JLabel("Introduce la palabra secreta (menos de 5 letras, solo minúsculas):");
        inputPalabra = new JTextField(10);
        botonEmpezar = new JButton("Empezar");
        labelAdvertencia = new JLabel("");
        labelAdvertencia.setForeground(Color.RED);

        panelInicio.add(labelInstruccion);
        panelInicio.add(inputPalabra);
        panelInicio.add(botonEmpezar);
        panelInicio.add(labelAdvertencia);

        add(panelInicio, BorderLayout.CENTER);

        // --------- Pantalla del juego ---------
        panelJuego = new JPanel();
        panelJuego.setLayout(new GridLayout(5, 1, 5, 5));
        labelHuecos = new JLabel("", SwingConstants.CENTER);
        inputLetra = new JTextField(2);
        botonProbar = new JButton("Probar letra");
        labelIntentos = new JLabel("", SwingConstants.CENTER);
        labelMensaje = new JLabel("", SwingConstants.CENTER);
        labelMensaje.setFont(labelMensaje.getFont().deriveFont(Font.BOLD));
        panelJuego.add(labelHuecos);

        JPanel panelLetra = new JPanel();
        panelLetra.add(new JLabel("Letra:"));
        panelLetra.add(inputLetra);
        panelLetra.add(botonProbar);
        panelJuego.add(panelLetra);

        panelJuego.add(labelIntentos);
        panelJuego.add(labelMensaje);

        panelJuego.setVisible(false);
        add(panelJuego, BorderLayout.SOUTH);

        // --------- Acción botón Empezar ---------
        botonEmpezar.addActionListener(e -> {
            palabraSecreta = inputPalabra.getText().trim();
            if (!palabraSecreta.matches("[a-z]{1,4}")) {
                labelAdvertencia.setText("Error: Solo letras minúsculas y menos de 5 caracteres.");
            } else {
                labelAdvertencia.setText("");
                iniciarJuego();
                panelInicio.setVisible(false);
                panelJuego.setVisible(true);
                pack();
            }
        });

        // --------- Acción botón Probar letra ---------
        botonProbar.addActionListener(e -> probarLetra());

        // Permitir pulsar Enter en el campo de letra
        inputLetra.addActionListener(e -> probarLetra());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void iniciarJuego() {
        palabraAdivinada = new char[palabraSecreta.length()];
        for (int i = 0; i < palabraAdivinada.length; i++) palabraAdivinada[i] = '_';
        intentosFallidos = 0;
        intentosMaximos = palabraSecreta.length() * 3;
        actualizarPantallaJuego();
        labelMensaje.setText("");
        botonProbar.setEnabled(true);
        inputLetra.setEnabled(true);
        inputLetra.setText("");
    }

    private void probarLetra() {
        String entrada = inputLetra.getText().trim();
        if (entrada.length() != 1 || !entrada.matches("[a-z]")) {
            labelMensaje.setForeground(Color.RED);
            labelMensaje.setText("Introduce solo UNA letra minúscula.");
            inputLetra.setText("");
            return;
        }
        char letra = entrada.charAt(0);
        boolean acierto = false;
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra && palabraAdivinada[i] == '_') {
                palabraAdivinada[i] = letra;
                acierto = true;
            }
        }
        if (!acierto) {
            intentosFallidos++;
        }
        inputLetra.setText("");
        actualizarPantallaJuego();

        // Comprobar victoria
        if (new String(palabraAdivinada).equals(palabraSecreta)) {
            labelMensaje.setForeground(new Color(0, 128, 0));
            labelMensaje.setText("¡Felicidades! Has adivinado la palabra.");
            botonProbar.setEnabled(false);
            inputLetra.setEnabled(false);
        }
        // Comprobar derrota
        else if (intentosFallidos >= intentosMaximos) {
            labelMensaje.setForeground(Color.RED);
            labelMensaje.setText("Has perdido. La palabra era: " + palabraSecreta);
            botonProbar.setEnabled(false);
            inputLetra.setEnabled(false);
            // Mostrar la palabra completa
            for (int i = 0; i < palabraSecreta.length(); i++) palabraAdivinada[i] = palabraSecreta.charAt(i);
            actualizarPantallaJuego();
        } else {
            labelMensaje.setText(""); // Quitar mensajes de error si todo va bien
        }
    }

    private void actualizarPantallaJuego() {
        // Mostrar los huecos y las letras acertadas
        StringBuilder sb = new StringBuilder();
        for (char c : palabraAdivinada) {
            sb.append(c).append(' ');
        }
        labelHuecos.setText(sb.toString().trim());
        labelIntentos.setText("Intentos fallidos: " + intentosFallidos + " de " + intentosMaximos);
    }

    public static void main(String[] args) {
        new Eventos_ahorcado();
    }
}