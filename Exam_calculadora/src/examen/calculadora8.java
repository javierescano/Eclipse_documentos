package examen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculadora8 extends JFrame {

    private JRadioButton eurOrigen, usdOrigen, btcOrigen;
    private JRadioButton eurDestino, usdDestino, btcDestino;
    private JTextField inputField;
    private JLabel origenLabel;
    private JButton convertirBtn;

    public calculadora8() {
        setTitle("Conversor monedas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);

        // Usar BorderLayout para la ventana principal
        setLayout(new BorderLayout(10, 10));

        // ---------- NORTE: Título ----------
        JLabel title = new JLabel("Conversor de monedas", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        add(title, BorderLayout.NORTH);

        // ---------- OESTE: Panel Origen ----------
        JPanel panelOrigen = new JPanel(new GridLayout(3, 1, 5, 5));
        panelOrigen.setBorder(BorderFactory.createTitledBorder("Origen"));
        eurOrigen = new JRadioButton("EUR");
        usdOrigen = new JRadioButton("USD");
        btcOrigen = new JRadioButton("BTC");
        ButtonGroup groupOrigen = new ButtonGroup();
        groupOrigen.add(eurOrigen);
        groupOrigen.add(usdOrigen);
        groupOrigen.add(btcOrigen);
        panelOrigen.add(eurOrigen);
        panelOrigen.add(usdOrigen);
        panelOrigen.add(btcOrigen);
        add(panelOrigen, BorderLayout.WEST);

        // ---------- ESTE: Panel Destino ----------
        JPanel panelDestino = new JPanel(new GridLayout(3, 1, 5, 5));
        panelDestino.setBorder(BorderFactory.createTitledBorder("Destino"));
        eurDestino = new JRadioButton("EUR");
        usdDestino = new JRadioButton("USD");
        btcDestino = new JRadioButton("BTC");
        ButtonGroup groupDestino = new ButtonGroup();
        groupDestino.add(eurDestino);
        groupDestino.add(usdDestino);
        groupDestino.add(btcDestino);
        eurDestino.setSelected(true);
        panelDestino.add(eurDestino);
        panelDestino.add(usdDestino);
        panelDestino.add(btcDestino);
        add(panelDestino, BorderLayout.EAST);

        // ---------- CENTRO: PANEL DE RESULTADO ----------
        JPanel resultadoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        resultadoPanel.setBackground(Color.YELLOW);
        resultadoPanel.setPreferredSize(new Dimension(370, 30));
        resultadoPanel.add(centerPanel);
        add(panelSur, BorderLayout.SOUTH);
        
        
        // ---------- SUR: PANEL DE ENTRADA Y BOTÓN ----------
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputField = new JTextField(10);
        origenLabel = new JLabel("______");
        origenLabel.setFont(new Font("Arial", Font.BOLD, 18));
        convertirBtn = new JButton("Convertir");
        inputPanel.add(inputField);
        inputPanel.add(origenLabel);
        inputPanel.add(convertirBtn);

        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(inputPanel);
        centerPanel.add(Box.createVerticalGlue());

        // ---------- CENTRO: PANEL DE RESULTADO ----------
        JPanel resultadoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        resultadoPanel.setBackground(Color.YELLOW);
        resultadoPanel.setPreferredSize(new Dimension(370, 30));
        resultadoPanel.add(centerPanel);
        add(panelSur, BorderLayout.SOUTH);
        
        // ---------- PANEL SUR COMBINADO ----------
        JPanel panelSur = new JPanel();
        panelSur.setLayout(new BoxLayout(panelSur, BoxLayout.Y_AXIS));

        panelSur.add(resultadoPanel);

        // Añadir el panel combinado al SUR
        

        // Lógica del conversor (ActionListener externo)
        convertirBtn.addActionListener(new ConvertirActionListener(this));
    }

    /**
     * Realiza la conversión entre monedas usando tasas fijas.
     */
    public double convertirMoneda(String origen, String destino, double cantidad) {
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

    // Métodos para acceder a los componentes desde el ActionListener externo
    public JRadioButton getEurOrigen() { return eurOrigen; }
    public JRadioButton getUsdOrigen() { return usdOrigen; }
    public JRadioButton getBtcOrigen() { return btcOrigen; }
    public JRadioButton getEurDestino() { return eurDestino; }
    public JRadioButton getUsdDestino() { return usdDestino; }
    public JRadioButton getBtcDestino() { return btcDestino; }
    public JTextField getInputField() { return inputField; }
    public JLabel getOrigenLabel() { return origenLabel; }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new calculadora8().setVisible(true);
        });
    }
}

// ActionListener externo, no anidado
class ConvertirActionListener implements ActionListener {
    private calculadora8 frame;

    public ConvertirActionListener(calculadora8 frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String origen = frame.getEurOrigen().isSelected() ? "EUR" :
                        frame.getUsdOrigen().isSelected() ? "USD" : "BTC";
        String destino = frame.getEurDestino().isSelected() ? "EUR" :
                         frame.getUsdDestino().isSelected() ? "USD" : "BTC";
        String inputText = frame.getInputField().getText().trim();
        double cantidad;

        try {
            cantidad = Double.parseDouble(inputText);
        } catch (NumberFormatException ex) {
            frame.getOrigenLabel().setText("Error");
            return;
        }

        double resultado = frame.convertirMoneda(origen, destino, cantidad);
        String resultadoStr = String.format("%.6f", resultado);

        // Si el destino no es BTC, muestra solo 2 decimales
        if (!destino.equals("BTC")) {
            resultadoStr = String.format("%.2f", resultado);
        }

        frame.getOrigenLabel().setText(resultadoStr + " " + destino);
    }
}
