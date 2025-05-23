package examen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculadora6 extends JFrame {

    public calculadora6() {
        setTitle("Cunversor de monedas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        // Título
        JLabel title = new JLabel("Conversor de monedas");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setBounds(70, 50, 350, 40);
        add(title);

        // Panel Origen
        JPanel panelOrigen = new JPanel();
        panelOrigen.setLayout(new GridLayout(3, 1));
        panelOrigen.setBounds(80, 100, 70, 100);
        panelOrigen.setBorder(BorderFactory.createTitledBorder("Origen"));
        JRadioButton eurOrigen = new JRadioButton("EUR");
        JRadioButton usdOrigen = new JRadioButton("USD");
        JRadioButton btcOrigen = new JRadioButton("BTC");
        ButtonGroup groupOrigen = new ButtonGroup();
        groupOrigen.add(eurOrigen);
        groupOrigen.add(usdOrigen);
        groupOrigen.add(btcOrigen);
        panelOrigen.add(eurOrigen);
        panelOrigen.add(usdOrigen);
        panelOrigen.add(btcOrigen);
        add(panelOrigen);

        // Panel Destino
        JPanel panelDestino = new JPanel();
        panelDestino.setLayout(new GridLayout(3, 1));
        panelDestino.setBounds(290, 100, 70, 100);
        panelDestino.setBorder(BorderFactory.createTitledBorder("Destino"));
        JRadioButton eurDestino = new JRadioButton("EUR");
        JRadioButton usdDestino = new JRadioButton("USD");
        JRadioButton btcDestino = new JRadioButton("BTC");
        ButtonGroup groupDestino = new ButtonGroup();
        groupDestino.add(eurDestino);
        groupDestino.add(usdDestino);
        groupDestino.add(btcDestino);
        eurDestino.setSelected(true);
        panelDestino.add(eurDestino);
        panelDestino.add(usdDestino);
        panelDestino.add(btcDestino);
        add(panelDestino);

        // Campo de introducción de texto.
        JTextField inputField = new JTextField();
        inputField.setBounds(110, 230, 120, 30);
        add(inputField);

        // Etiqueta tipo moneda origen.
        JLabel origenLabel = new JLabel("______");
        origenLabel.setFont(new Font("Arial", Font.BOLD, 18));
        origenLabel.setBounds(240, 230, 80, 30);
        add(origenLabel);

        // Botón convertir.
        JButton convertirBtn = new JButton("Convertir");
        convertirBtn.setBounds(330, 230, 90, 30);
        add(convertirBtn);

        // Barra de resultado (amarilla)
        JPanel resultadoPanel = new JPanel();
        resultadoPanel.setBackground(Color.YELLOW);
        resultadoPanel.setBounds(60, 270, 370, 30);
        add(resultadoPanel);

        // Lógica del conversor
        convertirBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String origen = eurOrigen.isSelected() ? "EUR" : usdOrigen.isSelected() ? "USD" : "BTC";
                String destino = eurDestino.isSelected() ? "EUR" : usdDestino.isSelected() ? "USD" : "BTC";
                String inputText = inputField.getText().trim();
                double cantidad;

                try {
                    cantidad = Double.parseDouble(inputText);
                } catch (NumberFormatException ex) {
                    origenLabel.setText("Error");
                    return;
                }

                double resultado = convertirMoneda(origen, destino, cantidad);
                String resultadoStr = String.format("%.6f", resultado);

                // Si el destino no es BTC, muestra solo 2 decimales
                if (!destino.equals("BTC")) {
                    resultadoStr = String.format("%.2f", resultado);
                }

                origenLabel.setText(resultadoStr + " " + destino);
            }
        });
    }

    /**
     * Realiza la conversión entre monedas usando tasas fijas.
     */
    private double convertirMoneda(String origen, String destino, double cantidad) {
        // Primero, convierte a EUR como moneda base
        double enEUR = 0.0;

        switch (origen) {
            case "EUR": enEUR = cantidad; break;
            case "USD": enEUR = cantidad * 0.93; break; // 1 USD = 0.93 EUR
            case "BTC": enEUR = cantidad * 45000; break; // 1 BTC = 45.000 EUR
        }

        // Ahora, de EUR a destino
        switch (destino) {
            case "EUR": return enEUR;
            case "USD": return enEUR * 1.08; // 1 EUR = 1.08 USD
            case "BTC": return enEUR * 0.000022; // 1 EUR = 0.000022 BTC
        }
        return 0.0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new calculadora6().setVisible(true);
        });
    }
}
