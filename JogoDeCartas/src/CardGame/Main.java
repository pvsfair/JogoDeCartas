/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame;

import CardGame.Campo.CampoDeBatalha;
import CardGame.Campo.CampoDeBatalhaDialog;
import CardGame.Campo.CampoDeBatalhaFrame;
import CardGame.Cartas.CartasDoJogo;
import CardGame.Deck.Deck;
import CardGame.Cartas.Carta;
import CardGame.Cartas.Magia;
import CardGame.Cartas.Monstro;
import CardGame.Deck.CriarDeck;
import CardGame.Deck.VerDeck;
import CardGame.Monstros.*;
import CardGame.Usuario.CartasDoJogador;
import CardGame.Usuario.Usuario;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Paulo Victor
 */
public class Main extends javax.swing.JFrame {

    CartasDoJogador cartasDoJogador = new CartasDoJogador();
    CartasDoJogo cartasDoJogo = new CartasDoJogo();
    Random rand = new Random();

    Usuario user = new Usuario("Paulo", cartasDoJogador, new ArrayList<Deck>(), null, 1, 0);

    /**
     * Creates new form Main
     */
    public Main() {
//        cartas.mostraCartas();

        for (int i = 0; i < 50; i++) {
            carregarCartasDoJogador();
        }

        user.getCartas().embaralhaCartas();

        initComponents();

        this.setLocationRelativeTo(null);
        labelNomeTopo.setText(user.getNomeUsr());

        configuraTabela(tabelaCartas);
        configuraTabela(tabelaCartasDoJogo);
        povoeTabelaCartas();
        atualizarListaDecks();
        labelInfoJogador();
    }

    private void carregarCartasDoJogador() {
        int aleatorio = rand.nextInt(cartasDoJogo.getNumCartas()) + 1;
        if (cartasDoJogo.getCarta(aleatorio) instanceof Monstro) {
            Monstro monstro = (Monstro) cartasDoJogo.getCarta(aleatorio);
            if (monstro instanceof MonstroAgua) {
                cartasDoJogador.adicionaCarta(new MonstroAgua((Monstro) cartasDoJogo.getCarta(aleatorio)));
            } else if (monstro instanceof MonstroFogo) {
                cartasDoJogador.adicionaCarta(new MonstroFogo((Monstro) cartasDoJogo.getCarta(aleatorio)));
            } else if (monstro instanceof MonstroLama) {
                cartasDoJogador.adicionaCarta(new MonstroLama((Monstro) cartasDoJogo.getCarta(aleatorio)));
            } else if (monstro instanceof MonstroNeutro) {
                cartasDoJogador.adicionaCarta(new MonstroNeutro((Monstro) cartasDoJogo.getCarta(aleatorio)));
            } else if (monstro instanceof MonstroTerra) {
                cartasDoJogador.adicionaCarta(new MonstroTerra((Monstro) cartasDoJogo.getCarta(aleatorio)));
            }
        } else if (cartasDoJogo.getCarta(aleatorio) instanceof Magia) {
            cartasDoJogador.adicionaCarta(new Magia((Magia) cartasDoJogo.getCarta(aleatorio)));
        }
//        cartasDoJogador.adicionaCarta(new Monstro(cartasDoJogo.getCarta(aleatorio)));
    }

    private void labelInfoJogador() {
        labelNomeIn.setText(user.getNomeUsr());
        labelNumDecksIn.setText(Integer.toString(user.getBaralhos().size()));
        labelNumCartasIn.setText(Integer.toString(user.getCartas().getCartas().size()));
        labelNivelIn.setText(Integer.toString(user.getNivel()));
        labelExperienciaIn.setText(Integer.toString(user.getExperiencia()));
    }

    private void configuraTabela(JTable tabela) {
        tabela.setAutoCreateColumnsFromModel(false);

        TableRowSorter sorter = new TableRowSorter(tabela.getModel());
        tabela.setRowSorter(sorter);

        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel model = tabela.getColumnModel();
        model.getColumn(0).setPreferredWidth(56);
        model.getColumn(1).setPreferredWidth(246);
        model.getColumn(2).setPreferredWidth(161);
        model.getColumn(3).setPreferredWidth(56);
        model.getColumn(4).setPreferredWidth(56);

    }

    private void povoeTabelaCartas() {
        cartasDoJogador.editTableModel((DefaultTableModel) tabelaCartas.getModel());
        cartasDoJogo.editTableModel((DefaultTableModel) tabelaCartasDoJogo.getModel());
    }

    private void atualizarListaDecks() {
        DefaultListModel model = new DefaultListModel();

        ArrayList<Deck> baralhos = user.getBaralhos();

        for (Deck baralho : baralhos) {
            model.addElement(baralho);
        }

        labelDeckPrincipal.setText((user.getDefaultDeck() != null) ? user.getDefaultDeck().toString() : "Nenhum");

        listaDecks.setModel(model);
    }

    private void iniciarBatalha() {
        if (user.getDefaultDeck() == null) {
            return;
        }
        CampoDeBatalhaDialog campo = new CampoDeBatalhaDialog(
                user.iniciarBatalha(CampoDeBatalha.Lado.AZUL), user.iniciarBatalha(CampoDeBatalha.Lado.VERMELHO));
        this.setVisible(false);
        campo.setVisible(true);
        this.setVisible(true);
    }

    private void criarDeck() {
        if (user.montarBaralho()) {
            user.setDefaultDeck(1);
        }

        atualizarListaDecks();
    }

    private void verCartaTabela(JTable tabela) {
        int selectedRow = tabela.getSelectedRow();
        DefaultTableModel model = ((DefaultTableModel) tabela.getModel());
        Carta carta = (Carta) model.getValueAt(selectedRow, 1);
        carta.showInfo();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        batalharBtn = new javax.swing.JButton();
        sairBtn = new javax.swing.JButton();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        cartas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCartas = new javax.swing.JTable();
        verCartaBtn = new javax.swing.JButton();
        decks = new javax.swing.JPanel();
        verDecksBtn = new javax.swing.JButton();
        criarDeckBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaDecks = new javax.swing.JList();
        atualizarListaBtn = new javax.swing.JButton();
        editarDeckBtn = new javax.swing.JButton();
        btnSelecionarDeck = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        labelDeckPrincipal = new javax.swing.JLabel();
        habilidades = new javax.swing.JPanel();
        infoJogador = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelNomeIn = new javax.swing.JLabel();
        labelNumDecksIn = new javax.swing.JLabel();
        labelNumCartasIn = new javax.swing.JLabel();
        labelNivelIn = new javax.swing.JLabel();
        labelExperienciaIn = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaCartasDoJogo = new javax.swing.JTable();
        labelNomeTopo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        batalharBtn.setText("Batalhar");
        batalharBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalharBtnActionPerformed(evt);
            }
        });

        sairBtn.setText("Sair");
        sairBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairBtnActionPerformed(evt);
            }
        });

        jTabbedPane5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTabbedPane5MouseReleased(evt);
            }
        });

        tabelaCartas.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaCartas.getTableHeader().setReorderingAllowed(false);
        tabelaCartas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCartasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCartas);
        if (tabelaCartas.getColumnModel().getColumnCount() > 0) {
            tabelaCartas.getColumnModel().getColumn(0).setResizable(false);
            tabelaCartas.getColumnModel().getColumn(1).setResizable(false);
            tabelaCartas.getColumnModel().getColumn(2).setResizable(false);
            tabelaCartas.getColumnModel().getColumn(3).setResizable(false);
            tabelaCartas.getColumnModel().getColumn(4).setResizable(false);
        }

        verCartaBtn.setText("Ver Carta");
        verCartaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verCartaBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cartasLayout = new javax.swing.GroupLayout(cartas);
        cartas.setLayout(cartasLayout);
        cartasLayout.setHorizontalGroup(
            cartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(verCartaBtn)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
        );
        cartasLayout.setVerticalGroup(
            cartasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartasLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(verCartaBtn)
                .addGap(6, 6, 6))
        );

        jTabbedPane5.addTab("Cartas", cartas);

        verDecksBtn.setText("Ver Deck");
        verDecksBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verDecksBtnActionPerformed(evt);
            }
        });

        criarDeckBtn.setText("Criar um novo Deck");
        criarDeckBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarDeckBtnActionPerformed(evt);
            }
        });

        listaDecks.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaDecksMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listaDecks);

        atualizarListaBtn.setText("Atualizar Lista de Decks");
        atualizarListaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atualizarListaBtnActionPerformed(evt);
            }
        });

        editarDeckBtn.setText("Editar Deck");

        btnSelecionarDeck.setText("Selecionar Deck Primário");
        btnSelecionarDeck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarDeckActionPerformed(evt);
            }
        });

        jLabel6.setText("Deck principal:");

        labelDeckPrincipal.setText("jLabel7");

        javax.swing.GroupLayout decksLayout = new javax.swing.GroupLayout(decks);
        decks.setLayout(decksLayout);
        decksLayout.setHorizontalGroup(
            decksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decksLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(decksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(verDecksBtn)
                    .addComponent(criarDeckBtn)
                    .addComponent(atualizarListaBtn)
                    .addComponent(editarDeckBtn)
                    .addComponent(btnSelecionarDeck)
                    .addGroup(decksLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDeckPrincipal)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        decksLayout.setVerticalGroup(
            decksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(decksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(decksLayout.createSequentialGroup()
                        .addComponent(verDecksBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editarDeckBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(criarDeckBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(atualizarListaBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSelecionarDeck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(decksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(labelDeckPrincipal)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(108, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Decks", decks);

        javax.swing.GroupLayout habilidadesLayout = new javax.swing.GroupLayout(habilidades);
        habilidades.setLayout(habilidadesLayout);
        habilidadesLayout.setHorizontalGroup(
            habilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 567, Short.MAX_VALUE)
        );
        habilidadesLayout.setVerticalGroup(
            habilidadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Habilidades", habilidades);

        jLabel1.setText("Nome:");

        jLabel2.setText("Numero de Decks:");

        jLabel3.setText("Numero de Cartas:");

        jLabel4.setText("Nível:");

        jLabel5.setText("Experiência:");

        labelNomeIn.setText("jLabel6");

        labelNumDecksIn.setText("jLabel7");

        labelNumCartasIn.setText("jLabel8");

        labelNivelIn.setText("jLabel9");

        labelExperienciaIn.setText("jLabel10");

        javax.swing.GroupLayout infoJogadorLayout = new javax.swing.GroupLayout(infoJogador);
        infoJogador.setLayout(infoJogadorLayout);
        infoJogadorLayout.setHorizontalGroup(
            infoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoJogadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(infoJogadorLayout.createSequentialGroup()
                        .addGroup(infoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(infoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelExperienciaIn)
                            .addComponent(labelNivelIn)
                            .addComponent(labelNumDecksIn)
                            .addComponent(labelNomeIn)
                            .addComponent(labelNumCartasIn))))
                .addContainerGap(408, Short.MAX_VALUE))
        );
        infoJogadorLayout.setVerticalGroup(
            infoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoJogadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelNomeIn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelNumDecksIn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelNumCartasIn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labelNivelIn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(labelExperienciaIn))
                .addContainerGap(296, Short.MAX_VALUE))
        );

        jTabbedPane5.addTab("Informações do Jogador", infoJogador);

        tabelaCartasDoJogo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Tipo", "Vida", "Dano"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCartasDoJogo.getTableHeader().setReorderingAllowed(false);
        tabelaCartasDoJogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCartasDoJogoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelaCartasDoJogo);
        if (tabelaCartasDoJogo.getColumnModel().getColumnCount() > 0) {
            tabelaCartasDoJogo.getColumnModel().getColumn(0).setResizable(false);
            tabelaCartasDoJogo.getColumnModel().getColumn(1).setResizable(false);
            tabelaCartasDoJogo.getColumnModel().getColumn(2).setResizable(false);
            tabelaCartasDoJogo.getColumnModel().getColumn(3).setResizable(false);
            tabelaCartasDoJogo.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Cartas No Jogo", jPanel1);

        labelNomeTopo.setText("NomeDoJogador");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(batalharBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sairBtn)))
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addGap(258, 258, 258)
                .addComponent(labelNomeTopo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelNomeTopo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batalharBtn)
                    .addComponent(sairBtn))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sairBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairBtnActionPerformed
        dispose();
    }//GEN-LAST:event_sairBtnActionPerformed

    private void verCartaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verCartaBtnActionPerformed
        verCartaTabela(tabelaCartas);
    }//GEN-LAST:event_verCartaBtnActionPerformed

    private void tabelaCartasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCartasMouseClicked
        if (evt.getClickCount() == 2) {
            verCartaTabela(tabelaCartas);
        }
    }//GEN-LAST:event_tabelaCartasMouseClicked

    private void batalharBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalharBtnActionPerformed
        iniciarBatalha();
    }//GEN-LAST:event_batalharBtnActionPerformed

    private void listaDecksMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaDecksMouseClicked
        if (evt.getClickCount() == 2) {

        }
    }//GEN-LAST:event_listaDecksMouseClicked

    private void criarDeckBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarDeckBtnActionPerformed
        criarDeck();
        atualizarListaDecks();
    }//GEN-LAST:event_criarDeckBtnActionPerformed

    private void verDecksBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verDecksBtnActionPerformed
        Deck deckMarcado = (Deck) listaDecks.getSelectedValue();
        if (deckMarcado != null) {
            System.out.println(deckMarcado.getNome());
            VerDeck.verDeck(deckMarcado);
        }
        atualizarListaDecks();
    }//GEN-LAST:event_verDecksBtnActionPerformed

    private void atualizarListaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atualizarListaBtnActionPerformed
        atualizarListaDecks();
    }//GEN-LAST:event_atualizarListaBtnActionPerformed

    private void tabelaCartasDoJogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCartasDoJogoMouseClicked
        if (evt.getClickCount() == 2) {
            verCartaTabela(tabelaCartasDoJogo);
        }
    }//GEN-LAST:event_tabelaCartasDoJogoMouseClicked

    private void jTabbedPane5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane5MouseReleased
        labelInfoJogador();
    }//GEN-LAST:event_jTabbedPane5MouseReleased

    private void btnSelecionarDeckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarDeckActionPerformed
        Deck deck = (Deck) listaDecks.getSelectedValue();
        if (deck == null) {
            JOptionPane.showMessageDialog(null, "Nenhum Deck selecionado.");
        } else {
            user.setDefaultDeck(deck.getId());
        }
        atualizarListaDecks();
    }//GEN-LAST:event_btnSelecionarDeckActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
         if ("Nimbus".equals(info.getName())) {
         javax.swing.UIManager.setLookAndFeel(info.getClassName());
         break;
         }
         }
         } catch (ClassNotFoundException ex) {
         java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         }*/
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atualizarListaBtn;
    private javax.swing.JButton batalharBtn;
    private javax.swing.JButton btnSelecionarDeck;
    private javax.swing.JPanel cartas;
    private javax.swing.JButton criarDeckBtn;
    private javax.swing.JPanel decks;
    private javax.swing.JButton editarDeckBtn;
    private javax.swing.JPanel habilidades;
    private javax.swing.JPanel infoJogador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JLabel labelDeckPrincipal;
    private javax.swing.JLabel labelExperienciaIn;
    private javax.swing.JLabel labelNivelIn;
    private javax.swing.JLabel labelNomeIn;
    private javax.swing.JLabel labelNomeTopo;
    private javax.swing.JLabel labelNumCartasIn;
    private javax.swing.JLabel labelNumDecksIn;
    private javax.swing.JList listaDecks;
    private javax.swing.JButton sairBtn;
    private javax.swing.JTable tabelaCartas;
    private javax.swing.JTable tabelaCartasDoJogo;
    private javax.swing.JButton verCartaBtn;
    private javax.swing.JButton verDecksBtn;
    // End of variables declaration//GEN-END:variables
}
