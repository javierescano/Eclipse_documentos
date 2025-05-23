package examen;

import javax.swing.*;
import java.awt.*;

public class calculadora extends JFrame {

    public calculadora() {
        setTitle("Currency Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        // Icono (opcional, si tienes un archivo .ico)
        // setIconImage(new ImageIcon("ruta/icono.png").getImage());

        // Título
        JLabel title = new JLabel("Currency Converter");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setBounds(80, 50, 350, 40);
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

        // Campo de texto y botón
        JTextField inputField = new JTextField();
        inputField.setBounds(110, 230, 120, 30);
        add(inputField);

        JLabel btcLabel = new JLabel("______");
        btcLabel.setFont(new Font("Arial", Font.BOLD, 18));
        btcLabel.setBounds(240, 230, 80, 30);
        add(btcLabel);

        JButton convertButton = new JButton("Convertir");
        convertButton.setBounds(330, 230, 90, 30);
        add(convertButton);

        // Barra de resultado simulada (amarilla)
        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(Color.YELLOW);
        resultPanel.setBounds(60, 270, 370, 30);
        add(resultPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new calculadora().setVisible(true);
        });
    }
}
