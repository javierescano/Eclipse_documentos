package examen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculadora9 extends JFrame {

    private JRadioButton eurOrigen, usdOrigen, btcOrigen;
    private JRadioButton eurDestino, usdDestino, btcDestino;
    private JTextField inputField;
    private JLabel resultadoLabel;
    private JLabel tipoMonedaLabel;
    private JButton convertirBtn;

    public calculadora9() {
        setTitle("Conversor de Monedas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        // ---------- NORTE: Título ----------
        JLabel titulo = new JLabel("CONVERSOR DE MONEDAS", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(Color.BLUE);
        add(titulo, BorderLayout.NORTH);

        // ---------- OESTE: Origen ----------
        JPanel panelOrigen = new JPanel(new GridLayout(3, 1, 10, 10));
        panelOrigen.setBorder(BorderFactory.createTitledBorder("MONEDA ORIGEN"));
        eurOrigen = new JRadioButton("EUR");
        usdOrigen = new JRadioButton("USD");
        btcOrigen = new JRadioButton("BTC");

        ButtonGroup grupoOrigen = new ButtonGroup();
        grupoOrigen.add(eurOrigen);
        grupoOrigen.add(usdOrigen);
        grupoOrigen.add(btcOrigen);

        panelOrigen.add(eurOrigen);
        panelOrigen.add(usdOrigen);
        panelOrigen.add(btcOrigen);
        
        panelOrigen.setPreferredSize(new Dimension(100, 100));
        add(panelOrigen, BorderLayout.WEST);

        // ---------- ESTE: Destino ----------
        JPanel panelDestino = new JPanel(new GridLayout(3, 1, 10, 10));
        panelDestino.setBorder(BorderFactory.createTitledBorder("MONEDA DESTINO"));
        eurDestino = new JRadioButton("EUR");
        usdDestino = new JRadioButton("USD");
        btcDestino = new JRadioButton("BTC");

        ButtonGroup grupoDestino = new ButtonGroup();
        grupoDestino.add(eurDestino);
        grupoDestino.add(usdDestino);
        grupoDestino.add(btcDestino);
        eurDestino.setSelected(true);

        panelDestino.add(eurDestino);
        panelDestino.add(usdDestino);
        panelDestino.add(btcDestino);
        
        panelDestino.setPreferredSize(new Dimension(100, 100));
        add(panelDestino, BorderLayout.EAST);

        // ---------- CENTRO: Resultado ----------
        resultadoLabel = new JLabel("0.00", SwingConstants.CENTER);
        resultadoLabel.setOpaque(true);
        resultadoLabel.setBackground(Color.YELLOW);
        resultadoLabel.setFont(new Font("Arial", Font.BOLD, 32));
        //resultadoLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        add(resultadoLabel, BorderLayout.CENTER);

        // ---------- SUR: Controles ----------
        // Para colocar los controles en una sola línea: FlowLayout
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelSur.setBorder(BorderFactory.createEtchedBorder());

        inputField = new JTextField(15);
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));

        tipoMonedaLabel = new JLabel("____");
        tipoMonedaLabel.setFont(new Font("Arial", Font.BOLD, 16));

        convertirBtn = new JButton("CONVERTIR");
        convertirBtn.setFont(new Font("Arial", Font.BOLD, 14));
        convertirBtn.setBackground(new Color(0, 150, 0));
        convertirBtn.setForeground(Color.WHITE);

        panelSur.add(new JLabel("Cantidad: "));
        panelSur.add(inputField);
        panelSur.add(tipoMonedaLabel);
        panelSur.add(convertirBtn);
        add(panelSur, BorderLayout.SOUTH);

        // Configurar listeners (sin clases anónimas)
        eurOrigen.addActionListener(new MonedaOrigenListener(this));
        usdOrigen.addActionListener(new MonedaOrigenListener(this));
        btcOrigen.addActionListener(new MonedaOrigenListener(this));
        convertirBtn.addActionListener(new ConvertirListener(this));
    }

    public double convertir(String origen, String destino, double cantidad) {
        double tasaCambioEUR = 1.0;
        double tasaCambioUSD = 0.93;
        double tasaCambioBTC = 45000.0;

        // Convertir a EUR primero
        double enEUR;
        
        // con if
        if ("EUR".equals(origen)) {
            enEUR = cantidad;
        } else if ("USD".equals(origen)) {
            enEUR = cantidad * tasaCambioUSD;
        } else if ("BTC".equals(origen)) {
            enEUR = cantidad * tasaCambioBTC;
        } else {
            enEUR = 0;
        }

        // con switch
/*        switch (origen) {
            case "EUR":
                enEUR = cantidad;
                break;
            case "USD":
                enEUR = cantidad * tasaCambioUSD;
                break;
            case "BTC":
                enEUR = cantidad * tasaCambioBTC;
                break;
            default:
                enEUR = 0;
                break;
        }
*/

        // Convertir a moneda destino
        switch (destino) {
        case "EUR":
            return enEUR;
        case "USD":
            return enEUR / tasaCambioUSD;
        case "BTC":
            return enEUR / tasaCambioBTC;
        default:
            return 0;
        }

    }

    // Getters para los listeners externos
    public JRadioButton getEurOrigen() { return eurOrigen; }
    public JRadioButton getUsdOrigen() { return usdOrigen; }
    public JRadioButton getBtcOrigen() { return btcOrigen; }
    public JRadioButton getEurDestino() { return eurDestino; }
    public JRadioButton getUsdDestino() { return usdDestino; }
    public JRadioButton getBtcDestino() { return btcDestino; }
    public JTextField getInputField() { return inputField; }
    public JLabel getResultadoLabel() { return resultadoLabel; }
    public JLabel getTipoMonedaLabel() { return tipoMonedaLabel; }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new calculadora9().setVisible(true);
        });
    }
}

// Listener para actualizar la etiqueta de tipo de moneda origen
class MonedaOrigenListener implements ActionListener {
    private final calculadora9 ventana;

    public MonedaOrigenListener(calculadora9 ventana_parametro) {
        this.ventana = ventana_parametro;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ventana.getEurOrigen().isSelected())
            ventana.getTipoMonedaLabel().setText("EURO");
        else if (ventana.getUsdOrigen().isSelected())
            ventana.getTipoMonedaLabel().setText("DÓLAR");
        else if (ventana.getBtcOrigen().isSelected())
            ventana.getTipoMonedaLabel().setText("BITCOIN");
    }
}

// Listener para el botón de conversión 
class ConvertirListener implements ActionListener {
    private final calculadora9 ventana;

    public ConvertirListener(calculadora9 ventana_parametro) {
        this.ventana = ventana_parametro;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String origen = ventana.getEurOrigen().isSelected() ? "EUR" :
                            ventana.getUsdOrigen().isSelected() ? "USD" : "BTC";
            String destino = ventana.getEurDestino().isSelected() ? "EUR" :
                             ventana.getUsdDestino().isSelected() ? "USD" : "BTC";
            double cantidad = Double.parseDouble(ventana.getInputField().getText().trim());
            double resultado = ventana.convertir(origen, destino, cantidad);

            String formato = destino.equals("BTC") ? "%,.6f" : "%,.2f";
            ventana.getResultadoLabel().setText(String.format(formato + " " + destino, resultado));
        } catch (NumberFormatException ex) {
            ventana.getResultadoLabel().setText("ERROR: Valor no válido");
        }
    }
}

