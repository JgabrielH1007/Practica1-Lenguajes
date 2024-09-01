/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author gabrielh
 */
public class Cuadricula extends JPanel{
    private int filas;
    private int columnas;

    public Cuadricula(int filas, int columnas){
        this.filas = filas;
        this.columnas = columnas;
        
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setLayout(new java.awt.GridLayout(filas, columnas));
        Dimension tamaño = new Dimension((350), (350));
        
        setPreferredSize(tamaño);
        
    }
    
public void leerTexto(String texto) {
    // Limpiar el panel antes de llenar con nuevos cuadros
    removeAll();

    // Dividir el texto en líneas
    String[] lineas = texto.split("\\n");

    List<Cuadro> cuadros = new ArrayList<>();
    String[] operadoresCompuestos = {"==", ">=", "<=", "<>", "+=", "-=", "*=", "/="};

    for (String linea : lineas) {
        if (linea.trim().startsWith("'")) {
            // Tratar todo lo que sigue al apóstrofe como un comentario
            String comentario = linea.trim();
            Cuadro cuadro = new Cuadro(comentario);
            cuadros.add(cuadro);
        } else {
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < linea.length(); i++) {
                char c = linea.charAt(i);

                boolean isCompoundOperator = false;
                for (String op : operadoresCompuestos) {
                    if (linea.startsWith(op, i)) {
                        if (buffer.length() > 0) {
                            cuadros.add(new Cuadro(buffer.toString().trim()));
                            buffer.setLength(0);
                        }
                        cuadros.add(new Cuadro(op));
                        i += op.length() - 1; // Saltar el tamaño del operador compuesto
                        isCompoundOperator = true;
                        break;
                    }
                }

                if (!isCompoundOperator) {
                    // Verificar si es una expresión de color
                    if (linea.startsWith("Square.Color(", i)) {
                        int endIndex = linea.indexOf(")", i);
                        if (endIndex != -1) {
                            String colorExpr = linea.substring(i, endIndex + 1);
                            cuadros.add(new Cuadro(colorExpr));
                            i = endIndex; // Saltar el final de la expresión
                        }
                    } else if (Character.isWhitespace(c) || !Character.isLetterOrDigit(c)) {
                        if (buffer.length() > 0) {
                            cuadros.add(new Cuadro(buffer.toString().trim()));
                            buffer.setLength(0);
                        }
                        if (!Character.isWhitespace(c)) {
                            cuadros.add(new Cuadro(String.valueOf(c)));
                        }
                    } else {
                        buffer.append(c);
                    }
                }
            }

            if (buffer.length() > 0) {
                cuadros.add(new Cuadro(buffer.toString().trim()));
            }
        }
    }

    // Asegurarse de que no exceda el número de celdas
    int maxCeldas = filas * columnas;
    if (cuadros.size() > maxCeldas) {
        JOptionPane.showMessageDialog(this,
            "El texto contiene más palabras de las que el tablero puede mostrar.",
            "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    // Llenar el tablero con los cuadros
    for (int i = 0; i < maxCeldas; i++) {
        if (i < cuadros.size()) {
            add(cuadros.get(i));
        } else {
            add(new Cuadro()); // Agregar cuadros vacíos para llenar el espacio
        }
    }

    revalidate();
    repaint();
}





}





