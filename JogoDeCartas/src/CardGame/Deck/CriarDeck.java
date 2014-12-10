/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame.Deck;

import CardGame.Cartas.Carta;
import CardGame.Cartas.ConjuntoDeCartas;
import CardGame.Cartas.Magia;
import CardGame.Cartas.Monstro;
import CardGame.Usuario.Usuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Paulo Victor
 */
public class CriarDeck extends javax.swing.JDialog {

    private boolean sucesso = false;

    private int numCartasDeck = 0;

    private String nomeDeck = "";

    private ArrayList<Deck> decksDoUsuario;

    private Deck deckNovo;

    /**
     * Creates new form CriarDeck
     */
    public CriarDeck(java.awt.Frame parent, boolean modal, ArrayList<Deck> decksDoUsuario) {
        super(parent, modal);
        initComponents();
        this.decksDoUsuario = decksDoUsuario;
        numCartasDeck = tabelaCartasDeck.getRowCount();
        configuraTabela(tabelaCartasUsuario);
        configuraTabela(tabelaCartasDeck);
    }

    public static boolean novoDeck(Usuario user) {
        CriarDeck dialog = new CriarDeck(null, true, user.getBaralhos());
        dialog.povoeTabelaCartas(dialog.tabelaCartasUsuario, user.getCartas());
        dialog.pack();
        dialog.setVisible(true);
        if (dialog.sucesso) {
            user.getBaralhos().add(dialog.deckNovo);
        }
        return dialog.sucesso;
    }

    private void povoeTabelaCartas(JTable tabela, ConjuntoDeCartas cartas) {
        cartas.editTableModel((DefaultTableModel) tabela.getModel());
    }

    private void configuraTabela(JTable tabela) {
        tabela.setAutoCreateColumnsFromModel(false);

        TableRowSorter sorter = new TableRowSorter(tabela.getModel());
        tabela.setRowSorter(sorter);

        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel model = tabela.getColumnModel();
        model.getColumn(0).setPreferredWidth(56);
        model.getColumn(1).setPreferredWidth(255);
        model.getColumn(2).setPreferredWidth(162);
        model.getColumn(3).setPreferredWidth(56);
        model.getColumn(4).setPreferredWidth(56);
    }

    private boolean moverCarta(JTable tabelaOrigem, JTable tabelaDestino) {
        if (tabelaOrigem.getSelectedRowCount() < 1) {
            return false;
        } else if (tabelaOrigem.getSelectedRowCount() == 1) {
            int selectedRow = tabelaOrigem.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) tabelaOrigem.getModel();
            Carta carta = (Carta) model.getValueAt(selectedRow, 1);
            model.removeRow(selectedRow);

            model = (DefaultTableModel) tabelaDestino.getModel();
            if (carta instanceof Monstro) {
                Monstro monstro = (Monstro) carta;
                Object[] rowData = {Integer.toString(monstro.getId()),
                    monstro, "Monstro: " + monstro.getTipo(),
                    Integer.toString(monstro.getVida()), Integer.toString(monstro.getDano())};
                model.addRow(rowData);
            } else if (carta instanceof Magia) {
                Magia magia = (Magia) carta;
                Object[] rowData = {Integer.toString(magia.getId()),
                    magia, "Magia", "Magia", "Magia"};
                model.addRow(rowData);
            }
        } else {
            int selectedRow = tabelaOrigem.getSelectedRow();
            int selectedRowCount = tabelaOrigem.getSelectedRowCount();
            for (int i = 0; i < selectedRowCount; i++) {
                DefaultTableModel model = (DefaultTableModel) tabelaOrigem.getModel();
                Carta carta = (Carta) model.getValueAt(selectedRow, 1);
                model.removeRow(selectedRow);

                model = (DefaultTableModel) tabelaDestino.getModel();
                if (carta instanceof Monstro) {
                    Monstro monstro = (Monstro) carta;
                    Object[] rowData = {Integer.toString(monstro.getId()),
                        monstro, "Monstro: " + monstro.getTipo(),
                        Integer.toString(monstro.getVida()), Integer.toString(monstro.getDano())};
                    model.addRow(rowData);
                } else if (carta instanceof Magia) {
                    Magia magia = (Magia) carta;
                    Object[] rowData = {Integer.toString(magia.getId()),
                        magia, "Magia", "Magia", "Magia"};
                    model.addRow(rowData);
                }

            }
        }
        return true;
    }

    private void nomearDeck() {
        do {
            nomeDeck = JOptionPane.showInputDialog("Insira o nome do Novo Deck.");
        } while (nomeRepetido(nomeDeck) || nomeDeck.equals(""));
    }

    private boolean nomeRepetido(String nome) {
        for (Deck deck : decksDoUsuario) {
            if (deck.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
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
        tabelaCartasUsuario = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaCartasDeck = new javax.swing.JTable();
        cartasToDeckBtn = new javax.swing.JButton();
        deckToCartasBtn = new javax.swing.JButton();
        salvarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        label1 = new javax.swing.JLabel();
        numDeck = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabelaCartasUsuario.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaCartasUsuario.getTableHeader().setReorderingAllowed(false);
        tabelaCartasUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCartasUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCartasUsuario);

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
        jScrollPane2.setViewportView(tabelaCartasDeck);

        cartasToDeckBtn.setText("Cartas-->Deck");
        cartasToDeckBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartasToDeckBtnActionPerformed(evt);
            }
        });

        deckToCartasBtn.setText("Deck-->Cartas");
        deckToCartasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deckToCartasBtnActionPerformed(evt);
            }
        });

        salvarBtn.setText("Salvar Deck");
        salvarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });

        label1.setText("Deck:");

        numDeck.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(salvarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelarBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(190, 190, 190)
                                .addComponent(cartasToDeckBtn)
                                .addGap(18, 18, 18)
                                .addComponent(deckToCartasBtn))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numDeck)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cartasToDeckBtn)
                    .addComponent(deckToCartasBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1)
                    .addComponent(numDeck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salvarBtn)
                    .addComponent(cancelarBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaCartasUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCartasUsuarioMouseClicked
        if ((evt.getClickCount() % 2) == 0) {
            if (moverCarta(tabelaCartasUsuario, tabelaCartasDeck)) {
                numCartasDeck = tabelaCartasDeck.getRowCount();
                numDeck.setText(Integer.toString(numCartasDeck));
            }
        }
    }//GEN-LAST:event_tabelaCartasUsuarioMouseClicked

    private void tabelaCartasDeckMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCartasDeckMouseClicked
        if ((evt.getClickCount() % 2) == 0) {
            if (moverCarta(tabelaCartasDeck, tabelaCartasUsuario)) {
                numCartasDeck = tabelaCartasDeck.getRowCount();
                numDeck.setText(Integer.toString(numCartasDeck));
            }
        }
    }//GEN-LAST:event_tabelaCartasDeckMouseClicked

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        sucesso = false;
        dispose();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void cartasToDeckBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartasToDeckBtnActionPerformed
        moverCarta(tabelaCartasUsuario, tabelaCartasDeck);
        numCartasDeck = tabelaCartasDeck.getRowCount();
        numDeck.setText(Integer.toString(numCartasDeck));
    }//GEN-LAST:event_cartasToDeckBtnActionPerformed

    private void deckToCartasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deckToCartasBtnActionPerformed
        moverCarta(tabelaCartasDeck, tabelaCartasUsuario);
        numCartasDeck = tabelaCartasDeck.getRowCount();
        numDeck.setText(Integer.toString(numCartasDeck));
    }//GEN-LAST:event_deckToCartasBtnActionPerformed

    private void salvarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarBtnActionPerformed
        nomearDeck();
        ArrayList<Carta> cartas = new ArrayList<>();
        for (int i = 0; i < tabelaCartasDeck.getRowCount(); i++) {
            cartas.add((Carta) tabelaCartasDeck.getModel().getValueAt(i, 1));
        }
        deckNovo = new Deck(decksDoUsuario.size() + 1, nomeDeck, cartas);
        sucesso = true;
        dispose();
    }//GEN-LAST:event_salvarBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JButton cartasToDeckBtn;
    private javax.swing.JButton deckToCartasBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel numDeck;
    private javax.swing.JButton salvarBtn;
    private javax.swing.JTable tabelaCartasDeck;
    public javax.swing.JTable tabelaCartasUsuario;
    // End of variables declaration//GEN-END:variables
}
