/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Fronted;

import Backend.Cuadricula;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;
import java.awt.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;
;

/**
 *
 * @author gabrielh
 */
public class FramePrincipal extends javax.swing.JFrame {
    private DialogFilasColumnas dialogFilasColumnas;
    private int filas;
    private int columnas;
    /**
     * Creates new form FramePrincipal
     */
    public FramePrincipal() {
        initComponents(); // Inicializar componentes generados por NetBeans
        dialogFilasColumnas = new DialogFilasColumnas(this, true);
        // Configuración básica del JFrame
        setTitle("Analizador Léxico");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Crear el JTextArea para el texto
        iniciarLista();
        // Añadir un DocumentListener para actualizar los números de línea
        txaEditor.getDocument().addDocumentListener(new DocumentListener() {
            public void updateLineNumbers() {
                int caretPosition = txaEditor.getDocument().getLength();
                Element root = txaEditor.getDocument().getDefaultRootElement();
                StringBuilder text = new StringBuilder("1\n");

                for (int i = 2; i <= root.getElementIndex(caretPosition) + 1; i++) {
                    text.append(i).append("\n");
                }
                txaListaNumero.setText(text.toString());
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLineNumbers();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLineNumbers();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLineNumbers();
            }
        });
        


        // Ajustar el diseño del JFrame
        pack();
    }
    
    
    public void iniciarLista(){
        txaEditor.setEnabled(false);
        txaListaNumero.setEnabled(false);
        txaEditor.setVisible(true);
        txaEditor.setEditable(true);
        txaEditor.setColumns(51); // Define un ancho de 20 columnas
        txaEditor.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Fuente monoespaciada para alineación
          ((AbstractDocument) txaEditor.getDocument()).setDocumentFilter(new limitarColumnas(50));
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


// Configurar el JTextArea para los números de línea
        txaListaNumero.setEditable(false);
        txaListaNumero.setVisible(true);
        txaListaNumero.setBackground(Color.white);
        txaListaNumero.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Misma fuente que txaEditor
        jScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER); // Deshabilitar barra de desplazamiento vertical en jScrollPane2

    // Sincronizar los scrolls de ambos JScrollPane
        jScrollPane2.getViewport().setView(txaListaNumero);
        jScrollPane1.getVerticalScrollBar().setModel(jScrollPane2.getVerticalScrollBar().getModel());   
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txaEditor = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaListaNumero = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jbtInicio = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txaEditor.setColumns(20);
        txaEditor.setRows(5);
        jScrollPane1.setViewportView(txaEditor);

        txaListaNumero.setColumns(20);
        txaListaNumero.setRows(5);
        jScrollPane2.setViewportView(txaListaNumero);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 369, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
        );

        jButton1.setText("Exportar Imagen");

        jButton2.setText("Cargar archivo");

        jbtInicio.setText("Iniciar");
        jbtInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtInicioActionPerformed(evt);
            }
        });

        jMenu2.setText("Reportes");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jbtInicio)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 652, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtInicio)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtInicioActionPerformed
        // TODO add your handling code here:
     dialogFilasColumnas.setVisible(true);
    filas = dialogFilasColumnas.getFilas();
    columnas = dialogFilasColumnas.getColumnas();

    if (filas > 0 && columnas > 0) {
            // Configurar el tablero
            Cuadricula cuadricula = new Cuadricula(filas, columnas);

            // Limpiar jPanel1 antes de agregar la cuadrícula
            jPanel1.removeAll();

            // Añadir la cuadrícula al panel
            jPanel1.setLayout(new BorderLayout()); // Asegúrate de que jPanel1 use un BorderLayout
            jPanel1.add(cuadricula, BorderLayout.CENTER);

            // Ajustar el tamaño de jPanel1
            jPanel1.revalidate();
            jPanel1.repaint();

            // Habilitar los JTextArea
            txaEditor.setEnabled(true);
            txaListaNumero.setEnabled(true);
            jbtInicio.setEnabled(false);
            pack();
        }
    }//GEN-LAST:event_jbtInicioActionPerformed

    /**
     * @param args the command line arguments
     */
    private static class limitarColumnas extends DocumentFilter {
        private final int maxColumnas;

        public limitarColumnas(int maxColumnas) {
            this.maxColumnas = maxColumnas;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (isWithinColumnLimit(fb, offset, string)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (isWithinColumnLimit(fb, offset, text)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        private boolean isWithinColumnLimit(FilterBypass fb, int offset, String text) throws BadLocationException {
            // Obtener el texto actual del documento
            String documentText = fb.getDocument().getText(0, fb.getDocument().getLength());

            // Construir el texto después de la inserción o reemplazo
            StringBuilder newText = new StringBuilder(documentText);
            newText.insert(offset, text);

            // Dividir el texto en líneas y verificar el límite de columnas
            String[] lines = newText.toString().split("\n");
            for (String line : lines) {
                if (line.length() > maxColumnas) {
                    return false; // Si una línea excede el límite, no permitir
                }
            }

            return true; // Si está dentro del límite, permitir
        }
    }
    
    
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        SwingUtilities.invokeLater(() -> {
            new FramePrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtInicio;
    private javax.swing.JTextArea txaEditor;
    private javax.swing.JTextArea txaListaNumero;
    // End of variables declaration//GEN-END:variables
}
