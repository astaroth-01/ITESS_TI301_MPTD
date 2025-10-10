/*
 * ITESS - TICS 2025 ESTRUCTURA Y ORGANIZACIÓN DE DATOS
 * Periodo: Agosto - Diciembre 2025
 * Docente: Francisco Montecillo Puente 
 * Estudiante / Programador: Angel Flores
 * Descripción: Creación del GUI para el método simplex.
 * E-mail: miguelangelflto6@gmail.com
 * E-mail institucional: ti24110044@itess.edu.mx
 * Fecha: 10 - 10 - 2025
 */

package unidad1.proglineal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SimplexGUI extends javax.swing.JFrame {

    private JPanel controlesPanel;
    private JPanel matrizPanel;
    private JPanel matrizSolPanel;
    private JPanel tablasPanel;
    private JTextField txtColumnas;
    private JTextField txtRenglones;
    private JButton matrizBttn;
    private JButton solucionarBttn;
    private DefaultTableModel modeloMatriz;
    private DefaultTableModel modeloMatrizSol;
    private JTable matrizInicialTable;
    private JTable matrizSolTable;
    private JScrollPane scrollMatriz;
    private JScrollPane scrollResultado;

    public SimplexGUI() {
        initComponents();
        setTitle("Método Simplex");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setLocationRelativeTo(null);
    }

    public void initComponents() {
        // Panel Principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Configuraciones del panel de controles
        controlesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        controlesPanel.setBorder(BorderFactory.createTitledBorder("Configuración de matriz"));

        controlesPanel.add(new JLabel("Renglones:"));
        txtRenglones = new JTextField("0", 5);
        controlesPanel.add(txtRenglones);

        controlesPanel.add(new JLabel("Columnas:"));
        txtColumnas = new JTextField("0", 5);
        controlesPanel.add(txtColumnas);

        matrizBttn = new JButton("Crear Matriz");
        controlesPanel.add(matrizBttn);

        solucionarBttn = new JButton("Solucionar");
        solucionarBttn.setEnabled(false);
        controlesPanel.add(solucionarBttn);

        // Configuración del panel para mostrar matriz inicial
        matrizPanel = new JPanel(new BorderLayout());
        matrizPanel.setBorder(BorderFactory.createTitledBorder("Matriz inicial"));

        // Configuración para mostrar la matriz resultado
        matrizSolPanel = new JPanel(new BorderLayout());
        matrizSolPanel.setBorder(BorderFactory.createTitledBorder("Matriz Solución"));

        // Configuración de panel para tablas
        tablasPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        tablasPanel.setPreferredSize(new Dimension(1000, 450));
        tablasPanel.add(matrizPanel);
        tablasPanel.add(matrizSolPanel);

        // Agregar elementos del panel principal
        panelPrincipal.add(controlesPanel, BorderLayout.NORTH);
        panelPrincipal.add(tablasPanel, BorderLayout.SOUTH);

        // Agregar elementos al frame
        add(panelPrincipal);

        // Crear matriz inicial
        matrizBttn.addActionListener((ActionEvent e) -> {
            crearMatriz();
        });
        
        solucionarBttn.addActionListener((ActionEvent e) -> {
            solucionarSimplex();
        });
    }

    private void crearMatriz() {
        try {
            int renglones = Integer.parseInt(txtRenglones.getText());
            int columnas = Integer.parseInt(txtColumnas.getText());

            if (renglones <= 0 || columnas <= 0) {
                JOptionPane.showMessageDialog(this, "Las filas y columnas deben ser mayores a 0");
                return;
            }
            
            // Crear modelo de tabla inicial
            String[] sb = new String[columnas];

            sb[0] = "Z";
            for(int i = 1; i < columnas - 1; i++) 
                    sb[i] ="x" + i;
            sb[columnas - 1] = "R";
            
            modeloMatriz = new DefaultTableModel(sb, renglones) {
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return String.class;
                }
            };
            
            for(int i = 0; i < renglones; i++) 
                for(int j = 0; j < columnas; j++)
                    modeloMatriz.setValueAt("0", i, j);
            
            matrizInicialTable = new JTable(modeloMatriz);
            matrizInicialTable.setRowHeight(25);
            matrizInicialTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
            scrollMatriz = new JScrollPane(matrizInicialTable);
            
            // Reconfigar panel de matriz inicial
            matrizPanel.removeAll();
            matrizPanel.add(scrollMatriz, BorderLayout.CENTER);
            matrizPanel.revalidate();
            matrizPanel.repaint();
            
            solucionarBttn.setEnabled(true);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido mayor a cero");
        }
    }
    
    private void solucionarSimplex() {
        if(modeloMatriz == null) {
            JOptionPane.showMessageDialog(this, "Primero debe crear la matriz");
            return;
        }
            
        try {
            double[][] pl = obtenerMatriz();
            
            // Resolver problema
            Simplex simplex = new Simplex(pl);
            simplex.resolver();
           
            // Mostrar matriz solución
            mostrarResultados(pl);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void mostrarResultados(double pl[][]) {
        // Formatear encabezados
        String[] sb = new String[pl[0].length];
        
        sb[0] = "Z";
        sb[pl[0].length - 1] = "R";
        
        // Configurar modelo de tabla solución
        for(int i = 0; i < pl.length - 1; i++) {
            sb[i] = "x" + i;
        }
        
        modeloMatrizSol = new DefaultTableModel(sb, pl.length) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
        };
        
        for(int i = 0; i < pl.length; i++)
            for(int j = 0; j < pl[i].length; j++)
                modeloMatrizSol.setValueAt(String.format("%.4f", pl[i][j]), i, j);
        
        // Configurar tabla solución
        matrizSolTable = new JTable(modeloMatrizSol);
        matrizSolTable.setRowHeight(25);
        matrizSolTable.setPreferredScrollableViewportSize(new Dimension(500, 200));
        scrollResultado = new JScrollPane(matrizSolTable);
        
        // Reconfigurar panel matriz solución
        matrizSolPanel.removeAll();
        matrizSolPanel.add(scrollResultado);
        matrizSolPanel.revalidate();
        matrizSolPanel.repaint();
    }
    
    private double[][] obtenerMatriz() {
        int renglones = modeloMatriz.getRowCount();
        int columnas = modeloMatriz.getColumnCount();
        double[][] matriz = new double[renglones][columnas];
        
        for(int i = 0; i < renglones; i++) {
            for(int j = 0; j < columnas; j++) {
                Object value = modeloMatriz.getValueAt(i, j);
                
                try {
                    if(value != null)
                        matriz[i][j] = Double.parseDouble(value.toString());
                    else
                        matriz[i][j] = 0.0;
                } catch (NumberFormatException e) {
                    matriz[i][j] = 0.0;
                }
            }
        }
        
        return matriz;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                crearYMostrarGUI();
            }
        });
    }

    private static void crearYMostrarGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
        }

        new SimplexGUI().setVisible(true);
    }
}
