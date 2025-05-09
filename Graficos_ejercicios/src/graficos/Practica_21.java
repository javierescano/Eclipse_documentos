package graficos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Practica_21 extends JFrame {
    private JTextField campoOperando1, campoOperando2;
    private JComboBox<String> comboOperacion;
    private JButton botonCalcular, botonGuardar;
    private JTextArea areaHistorial;
    private JScrollPane scrollHistorial;

    public Practica_21() {
        setTitle("Calculadora Simple");
        setBounds(400, 200, 450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior: entrada de datos y selección de operación
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new FlowLayout());

        campoOperando1 = new JTextField(6);
        campoOperando2 = new JTextField(6);

        String[] operaciones = {"+", "-", "×", "÷"};
        comboOperacion = new JComboBox<>(operaciones);

        botonCalcular = new JButton("Calcular");
        botonGuardar = new JButton("Guardar");

        panelEntrada.add(new JLabel("Operando 1:"));
        panelEntrada.add(campoOperando1);
        panelEntrada.add(comboOperacion);
        panelEntrada.add(new JLabel("Operando 2:"));
        panelEntrada.add(campoOperando2);
        panelEntrada.add(botonCalcular);
        panelEntrada.add(botonGuardar);

        add(panelEntrada, BorderLayout.NORTH);

        // Área de historial (no editable)
        areaHistorial = new JTextArea(10, 35);
        areaHistorial.setEditable(false);
        scrollHistorial = new JScrollPane(areaHistorial);
        add(scrollHistorial, BorderLayout.CENTER);

        // Acción del botón Calcular
        botonCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularOperacion();
            }
        });

        // Acción del botón Guardar
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarHistorial();
            }
        });
    }

    private void calcularOperacion() {
        String op1Texto = campoOperando1.getText().trim();
        String op2Texto = campoOperando2.getText().trim();

        if (op1Texto.isEmpty() || op2Texto.isEmpty()) {
            mostrarError("Debes introducir ambos operandos.");
            return;
        }

        double op1, op2;
        try {
            op1 = Double.parseDouble(op1Texto);
            op2 = Double.parseDouble(op2Texto);
        } catch (NumberFormatException ex) {
            mostrarError("Los operandos deben ser números válidos.");
            return;
        }

        String operacion = (String) comboOperacion.getSelectedItem();
        double resultado = 0;
        String lineaOperacion;

        switch (operacion) {
            case "+":
                resultado = op1 + op2;
                break;
            case "-":
                resultado = op1 - op2;
                break;
            case "×":
                resultado = op1 * op2;
                break;
            case "÷":
                if (op2 == 0) {
                    mostrarError("No se puede dividir por
