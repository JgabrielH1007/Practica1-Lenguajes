/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.Node;
import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.MutableNode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Cuadro extends JLabel  {
    private final String COLOR_IDENTIFICADOR = "#FFD300";
    private final String COLOR_SUMA = "#FF33FF";
    private final String COLOR_RESTA = "#C19A6B";
    private final String COLOR_EXPONENTE = "#FCD0B4";
    private final String COLOR_DIVISION = "#B4D941";
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
    private Color colorActual;
    private String texto;
    
    public Cuadro(String texto) {
            this.texto = texto;
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setOpaque(true);
            asignarColores(texto);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        mostrarDiagramaAutomata(texto);
                    } catch (IOException ex) {
                        Logger.getLogger(Cuadro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
    }
    
    public Cuadro() {
            // Cuadro vacío
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
            setOpaque(true);
        }
    
    public void asignarColores(String palabra) {
        if (palabra.equals("+")) {
            colorActual = Color.decode(COLOR_SUMA);
            setBackground(colorActual);
        } else if (palabra.equals("-")) {
            colorActual = Color.decode(COLOR_RESTA);
            setBackground(colorActual);
        } else if (palabra.equals("^")) {
            colorActual = Color.decode(COLOR_EXPONENTE);
            setBackground(colorActual);
        } else if (palabra.equals("/")) {
            colorActual = Color.decode(COLOR_DIVISION);
            setBackground(colorActual);
        } else if (palabra.equals("Mod")) {
            colorActual = Color.decode(COLOR_MODULO);
            setBackground(colorActual);
        } else if (palabra.equals("*")) {
            colorActual = Color.decode(COLOR_MULTIPLICACION);
            setBackground(colorActual);
        } else if (palabra.equals("==")) {
            colorActual = Color.decode(COLOR_IGUAL);
            setBackground(colorActual);
        } else if (palabra.equals("<>")) {
            colorActual = Color.decode(COLOR_DIFERENTE);
            setBackground(colorActual);
        } else if (palabra.equals(">")) {
            colorActual = Color.decode(COLOR_MAYORQUE);
            setBackground(colorActual);
        } else if (palabra.equals("<")) {
            colorActual = Color.decode(COLOR_MENORQUE);
            setBackground(colorActual);
        } else if (palabra.equals(">=")) {
            colorActual = Color.decode(COLOR_MAYORIGUAL);
            setBackground(colorActual);
        } else if (palabra.equals("<=")) {
            colorActual = Color.decode(COLOR_MENORIGUAL);
            setBackground(colorActual);
        } else if (palabra.equals("And")) {
            colorActual = Color.decode(COLOR_AND);
            setBackground(colorActual);
        } else if (palabra.equals("Or")) {
            colorActual = Color.decode(COLOR_OR);
            setBackground(colorActual);
        } else if (palabra.equals("Not")) {
            colorActual = Color.decode(COLOR_NOT);
            setBackground(colorActual);
        } else if (palabra.equals("=")) {
            colorActual = Color.decode(COLOR_ASIGNACION_SIMPLE);
            setBackground(colorActual);
        } else if (palabra.equals("+=") || palabra.equals("-=") || palabra.equals("*=") || palabra.equals("/=")) {
            colorActual = Color.decode(COLOR_ASIGNACION_COMPUESTA);
            setBackground(colorActual);
        } else if (palabra.equals("Module") || palabra.equals("End") || palabra.equals("Sub")
                || palabra.equals("Main") || palabra.equals("Dim") || palabra.equals("As")
                || palabra.equals("Integer") || palabra.equals("String") || palabra.equals("Boolean")
                || palabra.equals("Double") || palabra.equals("Char") || palabra.equals("Console.WriteLine")
                || palabra.equals("Console.ReadLine") || palabra.equals("If") || palabra.equals("ElseIf")
                || palabra.equals("Else") || palabra.equals("Then") || palabra.equals("While") || palabra.equals("Do")
                || palabra.equals("Loop") || palabra.equals("For") || palabra.equals("To") || palabra.equals("Next")
                || palabra.equals("Function") || palabra.equals("Return") || palabra.equals("Const")) {
            colorActual = Color.decode(COLOR_PALABRA_RESERVADA);
            setBackground(colorActual);
        } else if (isIdentificador(palabra)) {
            colorActual = Color.decode(COLOR_IDENTIFICADOR);
            setBackground(colorActual);
        } else if (palabra.startsWith("\"") && palabra.endsWith("\"")) {
            colorActual = Color.decode(COLOR_CADENA);
            setBackground(colorActual);
        } else if (palabra.startsWith("'") && palabra.endsWith("'") && palabra.length() == 3) {
            colorActual = Color.decode(COLOR_CARACTER);
            setBackground(colorActual);
        } else if (palabra.equals("True")) {
            colorActual = Color.decode(COLOR_BOOLEANO);
            setBackground(colorActual);
        } else if (palabra.equals("False")) {
            colorActual = Color.decode(COLOR_BOOLEANO);
            setBackground(colorActual);
        } else if (palabra.startsWith("'") && palabra.length() > 1) {
            colorActual = Color.decode(COLOR_COMENTARIO);
            setBackground(colorActual);
        } else if (palabra.equals("(") || palabra.equals(")")) {
            colorActual = Color.decode(COLOR_PARENTESIS);
            setBackground(colorActual);
        } else if (palabra.equals("{") || palabra.equals("}")) {
            colorActual = Color.decode(COLOR_LLAVES);
            setBackground(colorActual);
        } else if (palabra.equals("[") || palabra.equals("]")) {
            colorActual = Color.decode(COLOR_CORCHETES);
            setBackground(colorActual);
        } else if (palabra.equals(",")) {
            colorActual = Color.decode(COLOR_COMA);
            setBackground(colorActual);
        } else if (palabra.equals(".")) {
            colorActual = Color.decode(COLOR_PUNTO);
            setBackground(colorActual);
        } else if (palabra.startsWith("Square.Color(") && palabra.endsWith(")")) {
            String colorHex = palabra.substring(13, palabra.length() - 1).trim();
            if (colorHex.length() == 7 && colorHex.charAt(0) == '#') {
                try {
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
                colorActual = Color.decode(COLOR_ENTERO);
                setBackground(colorActual);
            } catch (NumberFormatException e1) {
                try {
                    Double.parseDouble(palabra);
                    colorActual = Color.decode(COLOR_DECIMAL);
                    setBackground(colorActual);
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
        if (!Character.isLetter(palabra.charAt(0))) {
            return false;
        }
        for (int i = 1; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '_') {
                return false;
            }
        }
        return true;
    }
    
     private void mostrarDiagramaAutomata(String expresion) throws IOException {
            // Generar un nombre de archivo único basado en la marca de tiempo en milisegundos
            long timestamp = System.currentTimeMillis();
            String dotPath = "automata_" + timestamp + ".dot";
            File archivoImagen = new File("automata_" + timestamp + ".png");

            // Crear el archivo DOT y renderizar la imagen
            crearArchivoDOT(expresion, dotPath);
            Graphviz.fromFile(new File(dotPath)).render(Format.PNG).toFile(archivoImagen);

            // Verificar si el archivo PNG se ha creado correctamente
            if (!archivoImagen.exists()) {
                throw new IOException("No se pudo crear el archivo de imagen PNG.");
            }

            String tipoExpresion = determinarTipoExpresion(expresion);
            // Mostrar la imagen y el tipo de expresión en un JOptionPane
            mostrarImagenEnDialogo(archivoImagen, tipoExpresion);
        }

        private void crearArchivoDOT(String expresion, String dotPath) throws IOException {
            StringBuilder dot = new StringBuilder("digraph G {\n");
            dot.append("rankdir=LR;\n"); // Dirección de izquierda a derecha

            // Crear los nodos y las transiciones para el autómata
            for (int i = 0; i < expresion.length(); i++) {
                dot.append("q").append(i).append(" -> ").append("q").append(i + 1).append(" [label=\"")
                        .append(expresion.charAt(i)).append("\"];\n");
            }
            dot.append("q").append(expresion.length()).append(" [shape=doublecircle];\n"); // Estado final
            dot.append("}");

            // Guardar el archivo DOT
            try (FileWriter writer = new FileWriter(dotPath)) {
                writer.write(dot.toString());
            }
        }

        private void mostrarImagenEnDialogo(File archivoImagen, String tipoExpresion) {
            ImageIcon icon = new ImageIcon(archivoImagen.getAbsolutePath());
            JLabel label = new JLabel(icon);
            JScrollPane scrollPane = new JScrollPane(label);
            scrollPane.setPreferredSize(new Dimension(600, 400));
            JOptionPane.showMessageDialog(null, scrollPane, "Diagrama del Autómata - " + tipoExpresion, JOptionPane.INFORMATION_MESSAGE);
        }

        private String determinarTipoExpresion(String expresion) {
            // Determinar el tipo de expresión basado en el contenido
            switch (expresion) {
                case "+": return "Suma";
                case "-": return "Resta";
                case "^": return "Exponente";
                case "/": return "División";
                case "Mod": return "Módulo";
                case "*": return "Multiplicación";
                case "==": return "Igual";
                case "<>": return "Diferente";
                case ">": return "Mayor que";
                case "<": return "Menor que";
                case ">=": return "Mayor o igual que";
                case "<=": return "Menor o igual que";
                case "And": return "Y";
                case "Or": return "O";
                case "Not": return "No";
                case "=": return "Asignación";
                case "+=":
                case "-=":
                case "*=":
                case "/=": return "Asignación compuesta";
                case "Module":
                case "End":
                case "Sub":
                case "Main":
                case "Dim":
                case "As":
                case "Integer":
                case "String":
                case "Boolean":
                case "Double":
                case "Char":
                case "Console.WriteLine":
                case "Console.ReadLine":
                case "If":
                case "ElseIf":
                case "Else":
                case "Then":
                case "While":
                case "Do":
                case "Loop":
                case "For":
                case "To":
                case "Next":
                case "Function":
                case "Return":
                case "Const": return "Palabra reservada";
                case "True":
                case "False": return "Booleano";
                default:
                    if (isIdentificador(expresion)) {
                        return "Identificador";
                    } else if (expresion.startsWith("\"") && expresion.endsWith("\"")) {
                        return "Cadena";
                    } else if (expresion.startsWith("'") && expresion.endsWith("'") && expresion.length() == 3) {
                        return "Carácter";
                    } else if (expresion.startsWith("'") && expresion.length() > 1) {
                        return "Comentario";
                    } else if (expresion.equals("(") || expresion.equals(")")) {
                        return "Paréntesis";
                    } else if (expresion.equals("{") || expresion.equals("}")) {
                        return "Llaves";
                    } else if (expresion.equals("[") || expresion.equals("]")) {
                        return "Corchetes";
                    } else if (expresion.equals(",")) {
                        return "Coma";
                    } else if (expresion.equals(".")) {
                        return "Punto";
                    }else {
                        try {
                            Integer.parseInt(expresion);
                            return "Entero";
                        } catch (NumberFormatException e1) {
                            try {
                                Double.parseDouble(expresion);
                                return "Decimal";
                            } catch (NumberFormatException e2) {
                                return "Square.Color";
                            }
                        }
                    }
                }
            }
}
    
   
   
    

