package calculadora;

import javax.swing.*;
import java.awt.*;

public class calculadora extends JFrame {

    private JRadioButton eurOrigen, usdOrigen, btcOrigen;
    private JRadioButton eurDestino, usdDestino, btcDestino;
    private JTextField txtCantidad;
    private JLabel lblMoneda;
    private JLabel lblResultado;

    public calculadora() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 320);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Panel superior: iconos y título
        JPanel panelTitulo = new JPanel(new BorderLayout());
        JPanel panelIconos = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        panelIconos.add(new JLabel("\uD83D\uDCB0")); // Emoji moneda
        panelIconos.add(new JLabel("\uD83D\uDCB5"));
        panelIconos.add(new JLabel("\uD83D\uDCB8"));
        panelTitulo.add(panelIconos, BorderLayout.WEST);

        JLabel lblTitulo = new JLabel("Currency Converter", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        add(panelTitulo, BorderLayout.NORTH);

        // Panel central: controles
        JPanel panelCentral = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        // Panel Origen
        JPanel panelOrigen = new JPanel(new GridLayout(3, 1));
        panelOrigen.setBorder(BorderFactory.createTitledBorder("Origen"));
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

        // Panel Destino
        JPanel panelDestino = new JPanel(new GridLayout(3, 1));
        panelDestino.setBorder(BorderFactory.createTitledBorder("Destino"));
        eurDestino = new JRadioButton("EUR");
        usdDestino = new JRadioButton("USD");
        btcDestino = new JRadioButton("BTC");
        ButtonGroup grupoDestino = new ButtonGroup();
        grupoDestino.add(eurDestino);
        grupoDestino.add(usdDestino);
        grupoDestino.add(btcDestino);
        panelDestino.add(eurDestino);
        panelDestino.add(usdDestino);
        panelDestino.add(btcDestino);

        // Añadir paneles de radio a la rejilla
        gbc.gridx = 0; gbc.gridy = 0;
        panelCentral.add(panelOrigen, gbc);
        gbc.gridx = 1;
        panelCentral.add(panelDestino, gbc);

        // Campo de cantidad y etiqueta de moneda
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        JPanel panelCantidad = new JPanel(new BorderLayout(5, 5));
        txtCantidad = new JTextField(10);
        lblMoneda = new JLabel("bitcoins");
        lblMoneda.setFont(new Font("Arial", Font.BOLD, 16));
        panelCantidad.add(txtCantidad, BorderLayout.CENTER);
        panelCantidad.add(lblMoneda, BorderLayout.EAST);
        panelCentral.add(panelCantidad, gbc);

        // Botón convertir
        gbc.gridy = 2; gbc.gridwidth = 2;
        JButton btnConvertir = new JButton("Convertir");
        panelCentral.add(btnConvertir, gbc);

        add(panelCentral, BorderLayout.CENTER);

        // Panel inferior: resultado
        lblResultado = new JLabel(" ", SwingConstants.CENTER);
        lblResultado.setOpaque(true);
        lblResultado.setBackground(Color.YELLOW);
        lblResultado.setFont(new Font("Arial", Font.BOLD, 16));
        lblResultado.setPreferredSize(new Dimension(0, 40));
        add(lblResultado, BorderLayout.SOUTH);

        // Selección por defecto
        eurOrigen.setSelected(true);
        eurDestino.setSelected(true);

        // Listener para cambiar la etiqueta de moneda según selección
        eurOrigen.addActionListener(e -> lblMoneda.setText("euros"));
        usdOrigen.addActionListener(e -> lblMoneda.setText("dólares"));
        btcOrigen.addActionListener(e -> lblMoneda.setText("bitcoins"));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(calculadora::new);
    }
}
