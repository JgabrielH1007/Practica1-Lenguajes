/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        for (int i = 0; i < filas * columnas; i++) {
            JLabel label = new JLabel();
            label.setBackground(Color.RED);
            label.setBorder(BorderFactory.createLineBorder(Color.RED));
            this.add(label);
            
        }
        
    }
    
}
