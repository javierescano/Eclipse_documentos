package graficos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Practica_21 extends JFrame {
    private JTabbedPane tabbedPane = new JTabbedPane();

    public Practica_21() {
        configurarGUI();
    }

    private void configurarGUI() {
        setTitle("Gestión Hospitalaria");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        tabbedPane.addTab("Pacientes", new PanelPacientes());
        tabbedPane.addTab("Médicos", new PanelMedicos());
        tabbedPane.addTab("Ingresos", new PanelIngresos());

        add(tabbedPane);
        setVisible(true);
    }

    // Panel de Pacientes
    class PanelPacientes extends JPanel {
        private DefaultTableModel modelo = new DefaultTableModel();
        private JTable tabla = new JTable(modelo);

        public PanelPacientes() {
            setLayout(new BorderLayout());
            modelo.setColumnIdentifiers(new String[]{"ID", "Nombre", "Apellidos", "Teléfono", "NSS"});

            JPanel controles = new JPanel(new GridLayout(1, 4, 10, 0));
            JButton btnAdd = new JButton("Añadir");
            JButton btnMod = new JButton("Modificar");
            JButton btnDel = new JButton("Eliminar");
            JButton btnLimpiar = new JButton("Limpiar");

            controles.add(btnAdd);
            controles.add(btnMod);
            controles.add(btnDel);
            controles.add(btnLimpiar);

            add(new JScrollPane(tabla), BorderLayout.CENTER);
            add(controles, BorderLayout.SOUTH);
        }
    }

    // Panel de Médicos
    class PanelMedicos extends JPanel {
        private DefaultTableModel modelo = new DefaultTableModel();
        private JTable tabla = new JTable(modelo);

        public PanelMedicos() {
            setLayout(new BorderLayout());
            modelo.setColumnIdentifiers(new String[]{"ID", "Nombre", "Apellidos", "Especialidad", "Nº Colegiado", "Cargo"});

            JPanel controles = new JPanel(new GridLayout(1, 4, 10, 0));
            JButton btnAdd = new JButton("Añadir");
            JButton btnMod = new JButton("Modificar");
            JButton btnDel = new JButton("Eliminar");
            JButton btnLimpiar = new JButton("Limpiar");

            controles.add(btnAdd);
            controles.add(btnMod);
            controles.add(btnDel);
            controles.add(btnLimpiar);

            add(new JScrollPane(tabla), BorderLayout.CENTER);
            add(controles, BorderLayout.SOUTH);
        }
    }

    // Panel de Ingresos
    class PanelIngresos extends JPanel {
        private DefaultTableModel modelo = new DefaultTableModel();
        private JTable tabla = new JTable(modelo);

        public PanelIngresos() {
            setLayout(new BorderLayout());
            modelo.setColumnIdentifiers(new String[]{"ID", "Paciente", "Médico", "Fecha", "Planta", "Cama"});

            JPanel panelForm = new JPanel(new GridLayout(2, 4, 10, 10));
            panelForm.add(new JLabel("Paciente:"));
            panelForm.add(new JComboBox<>());
            panelForm.add(new JLabel("Médico:"));
            panelForm.add(new JComboBox<>());
            panelForm.add(new JLabel("Fecha:"));
            panelForm.add(new JTextField());
            panelForm.add(new JLabel("Planta:"));
            panelForm.add(new JTextField());
            panelForm.add(new JLabel("Cama:"));
            panelForm.add(new JTextField());

            JPanel controles = new JPanel(new GridLayout(1, 4, 10, 0));
            JButton btnAdd = new JButton("Añadir");
            JButton btnMod = new JButton("Modificar");
            JButton btnDel = new JButton("Eliminar");
            JButton btnLimpiar = new JButton("Limpiar");

            controles.add(btnAdd);
            controles.add(btnMod);
            controles.add(btnDel);
            controles.add(btnLimpiar);

            add(panelForm, BorderLayout.NORTH);
            add(new JScrollPane(tabla), BorderLayout.CENTER);
            add(controles, BorderLayout.SOUTH);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Practica_21());
    }
}
