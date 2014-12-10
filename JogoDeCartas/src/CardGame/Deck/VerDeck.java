/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame.Deck;

import CardGame.Cartas.Carta;
import CardGame.Cartas.ConjuntoDeCartas;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Paulo Victor
 */
public class VerDeck extends javax.swing.JDialog {

    Deck deck;

    /**
     * Creates new form VerDeck
     */
    public VerDeck(java.awt.Frame parent, boolean modal, Deck deck) {
        super(parent, modal);
        this.deck = deck;
        initComponents();
        configuraTabela(tabelaCartasDeck);
    }

    public static void verDeck(Deck deck) {
        VerDeck dialog = new VerDeck(null, true, deck);
        dialog.povoeTabelaCartas(dialog.tabelaCartasDeck, deck);
        dialog.setVisible(true);
    }

    private void povoeTabelaCartas(JTable tabela, ConjuntoDeCartas cartas) {
        cartas.editTableModel((DefaultTableModel) tabela.getModel());
        numCartas.setText(Integer.toString(tabela.getRowCount()));
    }

    private void configuraTabela(JTable tabela) {
        tabela.setAutoCreateColumnsFromModel(false);

        TableRowSorter sorter = new TableRowSorter(tabela.getModel());
        tabela.setRowSorter(sorter);

        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel model = tabela.getColumnModel();
        model.getColumn(0).setPreferredWidth(56);
        model.getColumn(1).setPreferredWidth(232);
        model.getColumn(2).setPreferredWidth(162);
        model.getColumn(3).setPreferredWidth(56);
        model.getColumn(4).setPreferredWidth(56);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCartasDeck = new javax.swing.JTable();
        voltar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        numCartas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tabelaCartasDeck.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome(Custo)", "Tipo", "Vida", "Dano"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCartasDeck.getTableHeader().setReorderingAllowed(false);
        tabelaCartasDeck.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCartasDeckMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCartasDeck);

        voltar.setText("Voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });

        jLabel1.setText("Numero de Cartas no Deck:");

        numCartas.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numCartas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(voltar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(voltar)
                    .addComponent(jLabel1)
                    .addComponent(numCartas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaCartasDeckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCartasDeckMouseClicked
        if (evt.getClickCount() == 2) {
            int selectedRow = tabelaCartasDeck.getSelectedRow();
            DefaultTableModel model = ((DefaultTableModel) tabelaCartasDeck.getModel());
            Carta carta = (Carta) model.getValueAt(selectedRow, 1);
            JOptionPane.showMessageDialog(null, carta);
        }
    }//GEN-LAST:event_tabelaCartasDeckMouseClicked

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        dispose();
    }//GEN-LAST:event_voltarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel numCartas;
    public javax.swing.JTable tabelaCartasDeck;
    private javax.swing.JButton voltar;
    // End of variables declaration//GEN-END:variables
}
