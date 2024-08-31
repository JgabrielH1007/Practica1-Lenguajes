/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
        for (String linea : lineas) {
            if (linea.trim().startsWith("'")) {
                // Tratar todo lo que sigue al apóstrofe como un comentario
                String comentario = linea.trim();
                Cuadro cuadro = new Cuadro();
                cuadro.asignarColores(comentario);
                cuadros.add(cuadro);
            } else {
                // Dividir la línea en palabras, operadores y delimitadores especiales
                String[] palabras = linea.split("((?<=\\W)|(?=\\W))");

                for (String palabra : palabras) {
                    if (!palabra.trim().isEmpty()) {  // Ignorar espacios en blanco
                        Cuadro cuadro = new Cuadro();
                        cuadro.asignarColores(palabra.trim());
                        cuadros.add(cuadro);
                    }
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





