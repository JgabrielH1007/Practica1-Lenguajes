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

    for (int lineaNum = 0; lineaNum < lineas.length; lineaNum++) {
        String linea = lineas[lineaNum];
        int columnaNum = 0;
        boolean dentroComentario = false;
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);

            // Manejar el inicio del comentario
            if (c == '\'' && !dentroComentario) {
                // Si hay contenido en el buffer antes del comentario, agregarlo como un cuadro
                if (buffer.length() > 0) {
                    cuadros.add(new Cuadro(buffer.toString().trim(), lineaNum + 1, columnaNum + 1));
                    buffer.setLength(0);
                }
                dentroComentario = true;
                buffer.append(linea.substring(i)); // Agregar el comentario al buffer
                break; // Terminar el procesamiento de la línea ya que todo el resto es un comentario
            }

            if (dentroComentario) {
                // Continuar agregando al buffer mientras estamos en el comentario
                buffer.append(c);
                continue;
            }

            // Manejar cadenas entre comillas dobles
            if (c == '"') {
                int endIndex = linea.indexOf('"', i + 1);
                if (endIndex != -1) {
                    String cadena = linea.substring(i, endIndex + 1);
                    cuadros.add(new Cuadro(cadena, lineaNum + 1, columnaNum + 1));
                    i = endIndex; // Saltar al final de la cadena
                    columnaNum = i + 1;
                    continue;
                }
            }

            // Manejar caracteres entre comillas simples
            if (c == '\'') {
                int endIndex = linea.indexOf('\'', i + 1);
                if (endIndex != -1 && (endIndex - i == 2 || (endIndex - i == 3 && linea.charAt(i + 1) == '\\'))) {
                    String caracter = linea.substring(i, endIndex + 1);
                    cuadros.add(new Cuadro(caracter, lineaNum + 1, columnaNum + 1));
                    i = endIndex; // Saltar al final del carácter
                    columnaNum = i + 1;
                    continue;
                }
            }

            // Manejar operadores compuestos
            boolean isCompoundOperator = false;
            for (String op : operadoresCompuestos) {
                if (linea.startsWith(op, i)) {
                    if (buffer.length() > 0) {
                        cuadros.add(new Cuadro(buffer.toString().trim(), lineaNum + 1, columnaNum + 1));
                        buffer.setLength(0);
                    }
                    cuadros.add(new Cuadro(op, lineaNum + 1, columnaNum + 1));
                    i += op.length() - 1; // Saltar el tamaño del operador compuesto
                    columnaNum = i + 1;
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
                        cuadros.add(new Cuadro(colorExpr, lineaNum + 1, columnaNum + 1));
                        i = endIndex; // Saltar el final de la expresión
                        columnaNum = i + 1;
                        continue;
                    }
                }

                // Manejar otros caracteres y palabras
                if (Character.isWhitespace(c) || !Character.isLetterOrDigit(c)) {
                    if (buffer.length() > 0) {
                        cuadros.add(new Cuadro(buffer.toString().trim(), lineaNum + 1, columnaNum+1 ));
                        buffer.setLength(0);
                    }
                    if (!Character.isWhitespace(c)) {
                        cuadros.add(new Cuadro(String.valueOf(c), lineaNum + 1, columnaNum + 1 ));
                    }
                    columnaNum++;
                } else {
                    if (buffer.length() == 0) {
                        columnaNum = i ; // Guardar la columna de inicio si el buffer estaba vacío
                    }
                    buffer.append(c);
                }
            }
        }

        // Si hay contenido en el buffer después de terminar de procesar la línea, agregarlo como un cuadro
        if (buffer.length() > 0) {
            if (dentroComentario) {
                cuadros.add(new Cuadro(buffer.toString().trim(), lineaNum + 1, columnaNum + 1));
            } else {
                cuadros.add(new Cuadro(buffer.toString().trim(), lineaNum + 1, columnaNum + 1));
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

    // Llenar el tablero con los cuadros y asignar filas y columnas
    for (int i = 0; i < maxCeldas; i++) {
        int fila = i / columnas; // Calcular la fila del cuadro
        int columna = i % columnas; // Calcular la columna del cuadro

        if (i < cuadros.size()) {
            Cuadro cuadro = cuadros.get(i);
            cuadro.setFilaCuadricula(fila + 1);
            cuadro.setColumnaCuadricula(columna + 1);
            add(cuadro);
        } else {
            Cuadro cuadroVacio = new Cuadro();
            cuadroVacio.setFilaCuadricula(fila + 1);
            cuadroVacio.setColumnaCuadricula(columna + 1);
            add(cuadroVacio); // Agregar cuadros vacíos para llenar el espacio
        }
    }

    revalidate();
    repaint();
}




}





