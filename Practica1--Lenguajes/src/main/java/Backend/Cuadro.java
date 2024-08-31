/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.awt.Color;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author gabrielh
 */
public class Cuadro extends JLabel{
    private final String COLOR_IDENTIFICADOR = "#FFD300";
    private final String COLOR_SUMA = "#FF33FF";
    private final String COLOR_RESTA = "#C19A6B";
    private final String COLOR_EXPONENTE = "#FCD0B4";
    private final String COLOR_DIVISION =  "#B4D941";
    private final String COLOR_MODULO = "#D9AB41";
    private final String COLOR_MULTIPLICACION = "#D80073";
    private final String COLOR_IGUAL = "#6A00FF";
    private final String COLOR_DIFERENTE = "#3F2212";
    private final String COLOR_MAYORQUE = "#D9D441";
    private final String COLOR_MENORQUE = "#D94A41";
    private final String COLOR_MAYORIGUAL = "#E3C800";
    private final String COLOR_MENORIGUAL = "#F0A30A";
    private final String COLOR_AND = "#414ED9";
    private final String COLOR_OR = "#41D95D";
    private final String COLOR_NOT = "#A741D9";
    private final String COLOR_ASIGNACION_SIMPLE = "#41D9D4";
    private final String COLOR_ASIGNACION_COMPUESTA = "#FFFFFF";
    private final String COLOR_PALABRA_RESERVADA = "#60A917";
    private final String COLOR_ENTERO = "#1BA1E2";
    private final String COLOR_DECIMAL = "#FFFF88";
    private final String COLOR_CADENA = "#E51400";
    private final String COLOR_BOOLEANO = "#FA6800";
    private final String COLOR_CARACTER = "#0050EF";
    private final String COLOR_COMENTARIO = "#B3B3B3";
    private final String COLOR_PARENTESIS = "#9AD8D8";
    private final String COLOR_LLAVES = "#DBD29A";
    private final String COLOR_CORCHETES = "#DBA49A";
    private final String COLOR_COMA = "#B79ADB";
    private final String COLOR_PUNTO = "#9ADBA6";
    private boolean error = false;
    public Cuadro() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(true);
    }
    
    public void asignarColores(String palabra){
        if(palabra.equals("+")){
            setBackground(Color.decode(COLOR_SUMA));
        }else if(palabra.equals("-")){
            setBackground(Color.decode(COLOR_RESTA));
        }else if(palabra.equals("^")){
            setBackground(Color.decode(COLOR_EXPONENTE));
        }else if(palabra.equals("/")){
            setBackground(Color.decode(COLOR_DIVISION));
        }else if(palabra.equals("Mod")){
            setBackground(Color.decode(COLOR_MODULO));
        }else if(palabra.equals("*")){
            setBackground(Color.decode(COLOR_MULTIPLICACION));
        }else if(palabra.equals("==")){
            setBackground(Color.decode(COLOR_IGUAL));
        }else if(palabra.equals("<>")){
            setBackground(Color.decode(COLOR_DIFERENTE));
        }else if(palabra.equals(">")){
            setBackground(Color.decode(COLOR_MAYORQUE));
        }else if(palabra.equals("<")){
            setBackground(Color.decode(COLOR_MENORQUE));
        }else if(palabra.equals(">=")){
            setBackground(Color.decode(COLOR_MAYORIGUAL));
        }else if(palabra.equals("<=")){
            setBackground(Color.decode(COLOR_MENORIGUAL));
        }else if(palabra.equals("And")){
            setBackground(Color.decode(COLOR_AND));
        }else if(palabra.equals("Or")){
            setBackground(Color.decode(COLOR_OR));
        }else if(palabra.equals("Not")){
            setBackground(Color.decode(COLOR_NOT));
        }else if(palabra.equals("=")){
            setBackground(Color.decode(COLOR_ASIGNACION_SIMPLE));
        }else if(palabra.equals("+=")||palabra.equals("-=")||palabra.equals("*=")||palabra.equals("/=")){
            setBackground(Color.decode(COLOR_ASIGNACION_COMPUESTA));
        }else if(palabra.equals("Module")||palabra.equals("End")||palabra.equals("Sub")
                ||palabra.equals("Main")||palabra.equals("Dim")||palabra.equals("As")||
                palabra.equals("Integer")||palabra.equals("String")||palabra.equals("Boolean")
                ||palabra.equals("Double")||palabra.equals("Char")||palabra.equals("Console.WriteLine")
                ||palabra.equals("Console.ReadLine")||palabra.equals("If")||palabra.equals("ElseIf")
                ||palabra.equals("Else")||palabra.equals("Then")||palabra.equals("While")||palabra.equals("Do")
                ||palabra.equals("Loop")||palabra.equals("For")||palabra.equals("To")||palabra.equals("Next")
                ||palabra.equals("Function")||palabra.equals("Return")||palabra.equals("Const")){
            setBackground(Color.decode(COLOR_PALABRA_RESERVADA));
        }else if (isIdentificador(palabra)) {
        // Verificar si la palabra empieza con una letra y contiene solo letras, dígitos o _
            setBackground(Color.decode(COLOR_IDENTIFICADOR));  // Definir color para identificadores
        }else if (palabra.startsWith("\"") && palabra.endsWith("\"")) {
            setBackground(Color.decode(COLOR_CADENA));
        }else if (palabra.startsWith("'") && palabra.endsWith("'") && palabra.length() == 3) {
    // Verificar si la palabra empieza y termina con comillas simples y es solo un carácter
            setBackground(Color.decode(COLOR_CARACTER));
        }else if(palabra.equals("True")){
            setBackground(Color.decode(COLOR_BOOLEANO));
        }else if(palabra.equals("False")){
            setBackground(Color.decode(COLOR_BOOLEANO));
        }else if (palabra.startsWith("'") && palabra.length() > 1) {
    // Verificar si la palabra comienza con una comilla simple y sigue con cualquier otra cadena
            setBackground(Color.decode(COLOR_COMENTARIO));
        }else if(palabra.equals("(")||palabra.equals(")")){
            setBackground(Color.decode(COLOR_PARENTESIS));
        }else if(palabra.equals("{")||palabra.equals("}")){
            setBackground(Color.decode(COLOR_LLAVES));
        }else if(palabra.equals("[")||palabra.equals("]")){
            setBackground(Color.decode(COLOR_CORCHETES));
        }else if(palabra.equals(",")){
            setBackground(Color.decode(COLOR_COMA));
        }else if(palabra.equals(".")){
            setBackground(Color.decode(COLOR_PUNTO));
        }else if (palabra.startsWith("Square.Color(") && palabra.endsWith(")")) {
        // Extraer el parámetro del color dentro de los paréntesis
        // Extraer el parámetro del color dentro de los paréntesis
            String colorHex = palabra.substring(13, palabra.length() - 1).trim();

            // Verificar si el color comienza con "#" y tiene exactamente 7 caracteres (# seguido de 6)
            if (colorHex.length() == 7 && colorHex.charAt(0) == '#') {
                try {
                    // Intentar convertir el color hexadecimal
                    setBackground(Color.decode(colorHex));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Color no válido: " + colorHex, "Error", JOptionPane.ERROR_MESSAGE);
                    error = true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Formato de color no válido: " + colorHex, "Error", JOptionPane.ERROR_MESSAGE);
                error = true;
            } 
        } else {
        try {
            Integer.parseInt(palabra);
            setBackground(Color.decode(COLOR_ENTERO));
        } catch (NumberFormatException e1) {
            try {
                Double.parseDouble(palabra);
                setBackground(Color.decode(COLOR_DECIMAL));
            } catch (NumberFormatException e2) {
                JOptionPane.showMessageDialog(null, "PALABRA O EXPRESIÓN NO ACEPTADA.", "NO ACEPTADA", JOptionPane.ERROR_MESSAGE);
                error = true;
            }
        }
    }
}

    public boolean isError() {
        return error;
    }
    
    private boolean isIdentificador(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return false;
        }

        // Verificar que el primer carácter es una letra
        if (!Character.isLetter(palabra.charAt(0))) {
            return false;
        }

        // Verificar que el resto de los caracteres sean letras, dígitos o guiones bajos
        for (int i = 1; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }

        return true;
    }

    
    
    
}
    

