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

    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public Reporte(List<Cuadro> cuadros) {
        setTitle("Reporte de Tokens");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] nombresColumnas = {"Token", "Lexema", "Fila", "Columna", "Cuadro"};
        modeloTabla = new DefaultTableModel(nombresColumnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                // Deshabilitar la edición para todas las celdas
                return false;
            }
        };
        tabla = new JTable(modeloTabla);

        // Ajustar la altura de las filas
        tabla.setRowHeight(65); // Ajusta el valor a la altura deseada

        // Evitar que las columnas se puedan mover
        tabla.getTableHeader().setReorderingAllowed(false);

        // Configurar el renderizador de celdas para la columna "Cuadro"
        tabla.getColumnModel().getColumn(4).setCellRenderer(new RenderizadorCeldaMultiLinea());

        llenarTabla(cuadros);

        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void llenarTabla(List<Cuadro> cuadros) {
        for (Cuadro cuadro : cuadros) {
            String token = identificarToken(cuadro.getTexto(), cuadro); // Implementar este método según sea necesario
            String lexema = cuadro.getTexto();
            int fila = cuadro.getFila();
            int columna = cuadro.getColumna();
            String colorHex = colorAHex(cuadro.getColorActual()); // Obtener el código de color
            String infoCuadro = String.format("Fila: %d\nColumna: %d\nColor: %s", 
                cuadro.getFilaCuadricula(), 
                cuadro.getColumnaCuadricula(), 
                colorHex); // Actualizar el color en formato hexadecimal
            
            modeloTabla.addRow(new Object[]{token, lexema, fila, columna, infoCuadro});
        }
    }

    private String identificarToken(String texto, Cuadro cuadro) {
       return cuadro.determinarTipoExpresion(texto);
    }

    private String colorAHex(Color color) {
        return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }

    // Clase para el renderizador de celdas multi-línea
    private static class RenderizadorCeldaMultiLinea extends JTextArea implements TableCellRenderer {
        public RenderizadorCeldaMultiLinea() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable tabla, Object valor,
                                                       boolean estaSeleccionado, boolean tieneFoco, int fila, int columna) {
            setText(valor == null ? "" : valor.toString());
            setBackground(estaSeleccionado ? tabla.getSelectionBackground() : tabla.getBackground());
            setForeground(estaSeleccionado ? tabla.getSelectionForeground() : tabla.getForeground());
            return this;
        }
    }
}







