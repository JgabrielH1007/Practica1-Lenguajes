/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author gabrielh
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.List;

public class Reporte extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public Reporte(List<Cuadro> cuadros) {
        setTitle("Reporte de Tokens");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"Token", "Lexema", "Fila", "Columna", "Cuadro"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Deshabilitar la edición para todas las celdas
                return false;
            }
        };
        table = new JTable(tableModel);

        // Ajustar la altura de las filas
        table.setRowHeight(65); // Ajusta el valor a la altura deseada

        // Evitar que las columnas se puedan mover
        table.getTableHeader().setReorderingAllowed(false);

        // Configurar el renderizador de celdas para la columna "Cuadro"
        table.getColumnModel().getColumn(4).setCellRenderer(new MultiLineCellRenderer());

        populateTable(cuadros);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void populateTable(List<Cuadro> cuadros) {
        for (Cuadro cuadro : cuadros) {
            String token = identifyToken(cuadro.getTexto(), cuadro); // Implement this method based on your needs
            String lexema = cuadro.getTexto();
            int linea = cuadro.getFila();
            int columna = cuadro.getColumna();
            String colorHex = colorToHex(cuadro.getColorActual()); // Obtener el código de color
            String cuadroInfo = String.format("Fila: %d\nColumna: %d\nColor: %s", 
                cuadro.getFilaCuadricula(), 
                cuadro.getColumnaCuadricula(), 
                colorHex); // Actualizar el color en formato hexadecimal
            
            tableModel.addRow(new Object[]{token, lexema, linea, columna, cuadroInfo});
        }
    }

    private String identifyToken(String texto, Cuadro cuadro) {
       return cuadro.determinarTipoExpresion(texto);
    }

    private String colorToHex(Color color) {
        return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }

    // Clase para el renderizador de celdas multi-línea
    private static class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {
        public MultiLineCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value == null ? "" : value.toString());
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
            return this;
        }
    }
}






