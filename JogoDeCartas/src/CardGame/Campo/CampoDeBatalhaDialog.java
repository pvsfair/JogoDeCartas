/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame.Campo;

import CardGame.Cartas.Carta;
import CardGame.Cartas.Magia;
import CardGame.Cartas.Monstro;
import CardGame.Usuario.Jogador;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Paulo Victor
 */
public class CampoDeBatalhaDialog extends CampoDeBatalha {

    /**
     * Creates new form CampoDeBatalhaDialog
     */
    public CampoDeBatalhaDialog(Jogador jogadorAzul, Jogador jogadorVermelho) {
        super(jogadorAzul, jogadorVermelho);
        initComponents();
        this.setLocationRelativeTo(null);
        adjustLabels();
        adjustCampos();
        atualizarCartasMao(jogadorDaVez);
        atualizarCartasMao(ladoOposto(jogadorDaVez));
    }

    private void adjustLabels() {
        this.nomeDeckAzul.setText(jogadorAzul.getBaralho().getNome());
        this.nomeDeckVermelho.setText(jogadorVermelho.getBaralho().getNome());
        this.numCartasDeckAzul.setText(Integer.toString(jogadorAzul.getBaralho().getCartas().size()));
        this.numCartasDeckVermelho.setText(Integer.toString(jogadorVermelho.getBaralho().getCartas().size()));
        this.labelEnergiaAzul.setText(Integer.toString(jogadorAzul.getEnergia()));
        this.labelEnergiaVarmelho.setText(Integer.toString(jogadorVermelho.getEnergia()));
        this.labelVidaVermelho.setText(Integer.toString(jogadorVermelho.getVida()));
        this.labelVidaAzul.setText(Integer.toString(jogadorAzul.getVida()));
        this.setTitle("Turno: " + turno);
    }

    private void mostrarCartasNoCampo() {
        adjustCarta(cartaCampoA1, campoAzul.cartas[0]);
        adjustCarta(cartaCampoA2, campoAzul.cartas[1]);
        adjustCarta(cartaCampoA3, campoAzul.cartas[2]);
        adjustCarta(cartaCampoA4, campoAzul.cartas[3]);
        adjustCarta(cartaCampoA5, campoAzul.cartas[4]);
        adjustCarta(cartaCampoA6, campoAzul.cartas[5]);
        adjustCarta(cartaCampoA7, campoAzul.cartas[6]);
        adjustCarta(cartaCampoV1, campoVermelho.cartas[0]);
        adjustCarta(cartaCampoV2, campoVermelho.cartas[1]);
        adjustCarta(cartaCampoV3, campoVermelho.cartas[2]);
        adjustCarta(cartaCampoV4, campoVermelho.cartas[3]);
        adjustCarta(cartaCampoV5, campoVermelho.cartas[4]);
        adjustCarta(cartaCampoV6, campoVermelho.cartas[5]);
        adjustCarta(cartaCampoV7, campoVermelho.cartas[6]);
    }

    private void adjustCarta(JList listaCarta, Carta carta) {
        if (carta == null) {
            listaCarta.setModel(new DefaultListModel());
            return;
        }
        DefaultListModel model = new DefaultListModel();
        model.addElement(carta);
        if (carta instanceof Monstro) {
            Monstro monstro = (Monstro) carta;
            model.addElement("Tipo: " + monstro.getTipo());
            model.addElement("Vida: " + monstro.getVida());
            model.addElement("Dano: " + monstro.getDano());
        } else if (carta instanceof Magia) {
            model.addElement("Tipo: Magia");
            model.addElement("Efeito: ");
        }

        listaCarta.setModel(model);
    }

    private void adjustCampos() {
        onOffLado(jogadorDaVez, true);
        onOffLado(ladoOposto(jogadorDaVez), false);
    }

    private void atualizarCartasMao(Lado lado) {
        if (lado == Lado.AZUL) {
            DefaultListModel model = new DefaultListModel();

            for (Carta carta : jogadorAzul.getCartasNaMao().getCartas()) {
                model.addElement(carta);
            }

            listaCartasMaoAzul.setModel(model);
        } else {
            DefaultListModel model = new DefaultListModel();

            for (Carta carta : jogadorVermelho.getCartasNaMao().getCartas()) {
                model.addElement(carta);
            }

            listaCartasMaoVermelho.setModel(model);
        }
    }

    private void onOffLado(Lado lado, boolean enabled) {
        if (lado == Lado.AZUL) {
            btnFimTurnoAzul.setEnabled(enabled);
            btnInvocarAzul.setEnabled(enabled);
            btnJogarForaAzul.setEnabled(enabled);
            listaCartasMaoAzul.setVisible(enabled);
            vezAzul.setVisible(enabled);
            cartaCampoA1.setEnabled(enabled);
            cartaCampoA2.setEnabled(enabled);
            cartaCampoA3.setEnabled(enabled);
            cartaCampoA4.setEnabled(enabled);
            cartaCampoA5.setEnabled(enabled);
            cartaCampoA6.setEnabled(enabled);
            cartaCampoA7.setEnabled(enabled);
        } else {
            btnFimTurnoVermelho.setEnabled(enabled);
            btnInvocarVermelho.setEnabled(enabled);
            btnJogarForaVermelho.setEnabled(enabled);
            listaCartasMaoVermelho.setVisible(enabled);
            vezVermelho.setVisible(enabled);
            cartaCampoV1.setEnabled(enabled);
            cartaCampoV2.setEnabled(enabled);
            cartaCampoV3.setEnabled(enabled);
            cartaCampoV4.setEnabled(enabled);
            cartaCampoV5.setEnabled(enabled);
            cartaCampoV6.setEnabled(enabled);
            cartaCampoV7.setEnabled(enabled);
        }
    }

    private void fimTurno() {
        if (!finalizarTurnoJogadorAtual()) {
            return;
        }
        adjustCampos();
        adjustLabels();
        atualizarCartasMao(jogadorDaVez);
    }

    private void jogarCartaFora(Lado lado) {
        if (lado == Lado.AZUL) {
            Carta carta = (Carta) listaCartasMaoAzul.getSelectedValue();
            if (carta == null) {
                return;
            }
            jogadorAzul.jogarCartaFora(carta.getId());
            atualizarCartasMao(lado);
        } else {
            Carta carta = (Carta) listaCartasMaoVermelho.getSelectedValue();
            if (carta == null) {
                return;
            }
            jogadorVermelho.jogarCartaFora(carta.getId());
            atualizarCartasMao(lado);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelVermelho = new javax.swing.JPanel();
        panelCampoVermelho = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        cartaCampoV4 = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        cartaCampoV2 = new javax.swing.JList();
        jScrollPane5 = new javax.swing.JScrollPane();
        cartaCampoV1 = new javax.swing.JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        cartaCampoV3 = new javax.swing.JList();
        jScrollPane7 = new javax.swing.JScrollPane();
        cartaCampoV5 = new javax.swing.JList();
        jScrollPane8 = new javax.swing.JScrollPane();
        cartaCampoV6 = new javax.swing.JList();
        jScrollPane9 = new javax.swing.JScrollPane();
        cartaCampoV7 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaCartasMaoVermelho = new javax.swing.JList();
        btnInvocarVermelho = new javax.swing.JButton();
        btnJogarForaVermelho = new javax.swing.JButton();
        btnFimTurnoVermelho = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        numCartasDeckVermelho = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nomeDeckVermelho = new javax.swing.JLabel();
        vezVermelho = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelEnergiaVarmelho = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        labelVidaVermelho = new javax.swing.JLabel();
        panelAzul = new javax.swing.JPanel();
        panelCampoAzul = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        cartaCampoA5 = new javax.swing.JList();
        jScrollPane11 = new javax.swing.JScrollPane();
        cartaCampoA3 = new javax.swing.JList();
        jScrollPane12 = new javax.swing.JScrollPane();
        cartaCampoA1 = new javax.swing.JList();
        jScrollPane13 = new javax.swing.JScrollPane();
        cartaCampoA2 = new javax.swing.JList();
        jScrollPane14 = new javax.swing.JScrollPane();
        cartaCampoA4 = new javax.swing.JList();
        jScrollPane15 = new javax.swing.JScrollPane();
        cartaCampoA7 = new javax.swing.JList();
        jScrollPane16 = new javax.swing.JScrollPane();
        cartaCampoA6 = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaCartasMaoAzul = new javax.swing.JList();
        btnInvocarAzul = new javax.swing.JButton();
        btnJogarForaAzul = new javax.swing.JButton();
        btnFimTurnoAzul = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nomeDeckAzul = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        numCartasDeckAzul = new javax.swing.JLabel();
        vezAzul = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelEnergiaAzul = new javax.swing.JLabel();
        labelVidaAzul = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelVermelho.setBackground(new java.awt.Color(255, 102, 102));
        panelVermelho.setPreferredSize(new java.awt.Dimension(0, 300));

        cartaCampoV4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cartaCampoV4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartaCampoV4MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(cartaCampoV4);

        cartaCampoV2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cartaCampoV2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartaCampoV2MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(cartaCampoV2);

        cartaCampoV1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cartaCampoV1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartaCampoV1MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(cartaCampoV1);

        cartaCampoV3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cartaCampoV3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartaCampoV3MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(cartaCampoV3);

        cartaCampoV5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cartaCampoV5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartaCampoV5MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(cartaCampoV5);

        jScrollPane8.setAutoscrolls(true);

        cartaCampoV6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cartaCampoV6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartaCampoV6MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(cartaCampoV6);

        cartaCampoV7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cartaCampoV7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartaCampoV7MouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(cartaCampoV7);

        javax.swing.GroupLayout panelCampoVermelhoLayout = new javax.swing.GroupLayout(panelCampoVermelho);
        panelCampoVermelho.setLayout(panelCampoVermelhoLayout);
        panelCampoVermelhoLayout.setHorizontalGroup(
            panelCampoVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCampoVermelhoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(panelCampoVermelhoLayout.createSequentialGroup()
                .addGap(185, 185, 185)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCampoVermelhoLayout.setVerticalGroup(
            panelCampoVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCampoVermelhoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelCampoVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCampoVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        listaCartasMaoVermelho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaCartasMaoVermelhoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(listaCartasMaoVermelho);

        btnInvocarVermelho.setText("Invocar");
        btnInvocarVermelho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInvocarVermelhoActionPerformed(evt);
            }
        });

        btnJogarForaVermelho.setText("Jogar Fora");
        btnJogarForaVermelho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJogarForaVermelhoActionPerformed(evt);
            }
        });

        btnFimTurnoVermelho.setText("Finalizar Turno");
        btnFimTurnoVermelho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFimTurnoVermelhoActionPerformed(evt);
            }
        });

        jLabel1.setText("Informações do Deck:");

        jLabel2.setText("Cartas Restantes:");

        numCartasDeckVermelho.setText("50");

        jLabel5.setText("Nome:");

        nomeDeckVermelho.setText("NomeDeck1");

        vezVermelho.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        vezVermelho.setText("SUA VEZ");

        jLabel6.setText("Energia:");

        labelEnergiaVarmelho.setText("10");

        jLabel9.setText("Vida:");

        labelVidaVermelho.setText("10");

        javax.swing.GroupLayout panelVermelhoLayout = new javax.swing.GroupLayout(panelVermelho);
        panelVermelho.setLayout(panelVermelhoLayout);
        panelVermelhoLayout.setHorizontalGroup(
            panelVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVermelhoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelCampoVermelho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelVermelhoLayout.createSequentialGroup()
                        .addGroup(panelVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVermelhoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnInvocarVermelho))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVermelhoLayout.createSequentialGroup()
                                .addGroup(panelVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelVermelhoLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(numCartasDeckVermelho)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVermelhoLayout.createSequentialGroup()
                                        .addGroup(panelVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelVermelhoLayout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGroup(panelVermelhoLayout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nomeDeckVermelho)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                                                .addComponent(vezVermelho)
                                                .addGap(69, 69, 69)))
                                        .addGroup(panelVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel6))))
                                .addGroup(panelVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelEnergiaVarmelho)
                                    .addComponent(labelVidaVermelho)))
                            .addGroup(panelVermelhoLayout.createSequentialGroup()
                                .addComponent(btnFimTurnoVermelho)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnJogarForaVermelho)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelVermelhoLayout.setVerticalGroup(
            panelVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVermelhoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVermelhoLayout.createSequentialGroup()
                        .addGroup(panelVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnJogarForaVermelho)
                            .addComponent(btnFimTurnoVermelho))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(labelEnergiaVarmelho))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(panelVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(nomeDeckVermelho)
                                .addComponent(vezVermelho)
                                .addComponent(labelVidaVermelho)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelVermelhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(numCartasDeckVermelho))
                        .addGap(13, 13, 13)
                        .addComponent(btnInvocarVermelho)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCampoVermelho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelAzul.setBackground(new java.awt.Color(51, 51, 255));

        cartaCampoA5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cartaCampoA5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartaCampoA5MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(cartaCampoA5);

        cartaCampoA3.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cartaCampoA3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartaCampoA3MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(cartaCampoA3);

        cartaCampoA1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cartaCampoA1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartaCampoA1MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(cartaCampoA1);

        cartaCampoA2.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cartaCampoA2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartaCampoA2MouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(cartaCampoA2);

        cartaCampoA4.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cartaCampoA4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartaCampoA4MouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(cartaCampoA4);

        jScrollPane15.setAutoscrolls(true);

        cartaCampoA7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cartaCampoA7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartaCampoA7MouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(cartaCampoA7);

        cartaCampoA6.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        cartaCampoA6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartaCampoA6MouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(cartaCampoA6);

        javax.swing.GroupLayout panelCampoAzulLayout = new javax.swing.GroupLayout(panelCampoAzul);
        panelCampoAzul.setLayout(panelCampoAzulLayout);
        panelCampoAzulLayout.setHorizontalGroup(
            panelCampoAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCampoAzulLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(panelCampoAzulLayout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCampoAzulLayout.setVerticalGroup(
            panelCampoAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCampoAzulLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCampoAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCampoAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        listaCartasMaoAzul.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaCartasMaoAzulMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaCartasMaoAzul);

        btnInvocarAzul.setText("Invocar");
        btnInvocarAzul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInvocarAzulActionPerformed(evt);
            }
        });

        btnJogarForaAzul.setText("Jogar Fora");
        btnJogarForaAzul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJogarForaAzulActionPerformed(evt);
            }
        });

        btnFimTurnoAzul.setText("Finalizar Turno");
        btnFimTurnoAzul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFimTurnoAzulActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Informações do Deck:");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nome:");

        nomeDeckAzul.setForeground(new java.awt.Color(255, 255, 255));
        nomeDeckAzul.setText("NomeDeck2");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cartas Restantes:");

        numCartasDeckAzul.setForeground(new java.awt.Color(255, 255, 255));
        numCartasDeckAzul.setText("50");

        vezAzul.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        vezAzul.setForeground(new java.awt.Color(255, 255, 255));
        vezAzul.setText("SUA VEZ");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Energia:");

        labelEnergiaAzul.setForeground(new java.awt.Color(255, 255, 255));
        labelEnergiaAzul.setText("10");

        labelVidaAzul.setForeground(new java.awt.Color(255, 255, 255));
        labelVidaAzul.setText("10");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Vida:");

        javax.swing.GroupLayout panelAzulLayout = new javax.swing.GroupLayout(panelAzul);
        panelAzul.setLayout(panelAzulLayout);
        panelAzulLayout.setHorizontalGroup(
            panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAzulLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCampoAzul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelAzulLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAzulLayout.createSequentialGroup()
                                .addComponent(btnJogarForaAzul)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnFimTurnoAzul))
                            .addGroup(panelAzulLayout.createSequentialGroup()
                                .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelAzulLayout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(vezAzul)
                                        .addGap(144, 144, 144))
                                    .addGroup(panelAzulLayout.createSequentialGroup()
                                        .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnInvocarAzul)
                                            .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(labelVidaAzul)
                                                .addGroup(panelAzulLayout.createSequentialGroup()
                                                    .addComponent(jLabel8)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(labelEnergiaAzul))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAzulLayout.createSequentialGroup()
                                        .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addGroup(panelAzulLayout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nomeDeckAzul)))
                                        .addGap(1, 1, 1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAzulLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(numCartasDeckAzul)))))))
                .addContainerGap())
        );
        panelAzulLayout.setVerticalGroup(
            panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAzulLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCampoAzul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelAzulLayout.createSequentialGroup()
                        .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAzulLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(nomeDeckAzul))
                                .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelAzulLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(numCartasDeckAzul)))
                                    .addGroup(panelAzulLayout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addComponent(vezAzul))))
                            .addGroup(panelAzulLayout.createSequentialGroup()
                                .addComponent(btnInvocarAzul)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(labelEnergiaAzul))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(labelVidaAzul))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelAzulLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnJogarForaAzul)
                            .addComponent(btnFimTurnoAzul))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelVermelho, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addComponent(panelAzul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelVermelho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAzul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFimTurnoAzulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFimTurnoAzulActionPerformed
        fimTurno();
        mostrarCartasNoCampo();
    }//GEN-LAST:event_btnFimTurnoAzulActionPerformed

    private void btnInvocarAzulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInvocarAzulActionPerformed
        Carta carta = (Carta) listaCartasMaoAzul.getSelectedValue();
        if (carta == null) {
            return;
        }
        if (invocarCarta(carta.getId(), Lado.AZUL)) {
            jogadorAzul.getCartasNaMao().retiraCarta(carta.getId());
        }
        mostrarCartasNoCampo();
        atualizarCartasMao(Lado.AZUL);
        adjustLabels();
    }//GEN-LAST:event_btnInvocarAzulActionPerformed

    private void btnInvocarVermelhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInvocarVermelhoActionPerformed
        Carta carta = (Carta) listaCartasMaoVermelho.getSelectedValue();
        if (carta == null) {
            return;
        }
        if (invocarCarta(carta.getId(), Lado.VERMELHO)) {
            jogadorVermelho.getCartasNaMao().retiraCarta(carta.getId());
        }
        mostrarCartasNoCampo();
        atualizarCartasMao(Lado.VERMELHO);
        adjustLabels();
    }//GEN-LAST:event_btnInvocarVermelhoActionPerformed

    private void btnFimTurnoVermelhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFimTurnoVermelhoActionPerformed
        fimTurno();
        mostrarCartasNoCampo();
    }//GEN-LAST:event_btnFimTurnoVermelhoActionPerformed

    private void btnJogarForaVermelhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJogarForaVermelhoActionPerformed
        jogarCartaFora(Lado.VERMELHO);
    }//GEN-LAST:event_btnJogarForaVermelhoActionPerformed

    private void btnJogarForaAzulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJogarForaAzulActionPerformed
        jogarCartaFora(Lado.AZUL);
    }//GEN-LAST:event_btnJogarForaAzulActionPerformed

    private void listaCartasMaoAzulMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaCartasMaoAzulMouseClicked
        if (evt.getClickCount() == 2) {
            Carta carta = (Carta) listaCartasMaoVermelho.getSelectedValue();
            carta.showInfo();
        }
    }//GEN-LAST:event_listaCartasMaoAzulMouseClicked

    private void listaCartasMaoVermelhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaCartasMaoVermelhoMouseClicked
        if (evt.getClickCount() == 2) {
            Carta carta = (Carta) listaCartasMaoVermelho.getSelectedValue();
            carta.showInfo();
        }
    }//GEN-LAST:event_listaCartasMaoVermelhoMouseClicked

    private void cartaCampoA1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartaCampoA1MouseClicked
        if (evt.getClickCount() == 2 && cartaCampoA1.isEnabled()) {
            cartaCampoA1.setEnabled(!atacar(1));
            mostrarCartasNoCampo();
            adjustLabels();
        }
    }//GEN-LAST:event_cartaCampoA1MouseClicked

    private void cartaCampoA2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartaCampoA2MouseClicked
        if (evt.getClickCount() == 2 && cartaCampoA2.isEnabled()) {
            cartaCampoA2.setEnabled(!atacar(2));
            mostrarCartasNoCampo();
            adjustLabels();
        }
    }//GEN-LAST:event_cartaCampoA2MouseClicked

    private void cartaCampoA3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartaCampoA3MouseClicked
        if (evt.getClickCount() == 2 && cartaCampoA3.isEnabled()) {
            cartaCampoA3.setEnabled(!atacar(3));
            mostrarCartasNoCampo();
            adjustLabels();
        }
    }//GEN-LAST:event_cartaCampoA3MouseClicked

    private void cartaCampoA4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartaCampoA4MouseClicked
        if (evt.getClickCount() == 2 && cartaCampoA4.isEnabled()) {
            cartaCampoA4.setEnabled(!atacar(4));
            mostrarCartasNoCampo();
            adjustLabels();
        }
    }//GEN-LAST:event_cartaCampoA4MouseClicked

    private void cartaCampoA5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartaCampoA5MouseClicked
        if (evt.getClickCount() == 2 && cartaCampoA5.isEnabled()) {
            cartaCampoA5.setEnabled(!atacar(5));
            mostrarCartasNoCampo();
            adjustLabels();
        }
    }//GEN-LAST:event_cartaCampoA5MouseClicked

    private void cartaCampoA6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartaCampoA6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cartaCampoA6MouseClicked

    private void cartaCampoA7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartaCampoA7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cartaCampoA7MouseClicked

    private void cartaCampoV1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartaCampoV1MouseClicked
        if (evt.getClickCount() == 2 && cartaCampoV1.isEnabled()) {
            cartaCampoV1.setEnabled(!atacar(1));
            mostrarCartasNoCampo();
            adjustLabels();
        }
    }//GEN-LAST:event_cartaCampoV1MouseClicked

    private void cartaCampoV2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartaCampoV2MouseClicked
        if (evt.getClickCount() == 2 && cartaCampoV2.isEnabled()) {
            cartaCampoV2.setEnabled(!atacar(2));
            mostrarCartasNoCampo();
            adjustLabels();
        }
    }//GEN-LAST:event_cartaCampoV2MouseClicked

    private void cartaCampoV3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartaCampoV3MouseClicked
        if (evt.getClickCount() == 2 && cartaCampoV3.isEnabled()) {
            cartaCampoV3.setEnabled(!atacar(3));
            mostrarCartasNoCampo();
            adjustLabels();
        }
    }//GEN-LAST:event_cartaCampoV3MouseClicked

    private void cartaCampoV4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartaCampoV4MouseClicked
        if (evt.getClickCount() == 2 && cartaCampoV4.isEnabled()) {
            cartaCampoV4.setEnabled(!atacar(4));
            mostrarCartasNoCampo();
            adjustLabels();
        }
    }//GEN-LAST:event_cartaCampoV4MouseClicked

    private void cartaCampoV5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartaCampoV5MouseClicked
        if (evt.getClickCount() == 2 && cartaCampoV5.isEnabled()) {
            cartaCampoV5.setEnabled(!atacar(5));
            mostrarCartasNoCampo();
            adjustLabels();
        }
    }//GEN-LAST:event_cartaCampoV5MouseClicked

    private void cartaCampoV6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartaCampoV6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cartaCampoV6MouseClicked

    private void cartaCampoV7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartaCampoV7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cartaCampoV7MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFimTurnoAzul;
    private javax.swing.JButton btnFimTurnoVermelho;
    private javax.swing.JButton btnInvocarAzul;
    private javax.swing.JButton btnInvocarVermelho;
    private javax.swing.JButton btnJogarForaAzul;
    private javax.swing.JButton btnJogarForaVermelho;
    private javax.swing.JList cartaCampoA1;
    private javax.swing.JList cartaCampoA2;
    private javax.swing.JList cartaCampoA3;
    private javax.swing.JList cartaCampoA4;
    private javax.swing.JList cartaCampoA5;
    private javax.swing.JList cartaCampoA6;
    private javax.swing.JList cartaCampoA7;
    private javax.swing.JList cartaCampoV1;
    private javax.swing.JList cartaCampoV2;
    private javax.swing.JList cartaCampoV3;
    private javax.swing.JList cartaCampoV4;
    private javax.swing.JList cartaCampoV5;
    private javax.swing.JList cartaCampoV6;
    private javax.swing.JList cartaCampoV7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel labelEnergiaAzul;
    private javax.swing.JLabel labelEnergiaVarmelho;
    private javax.swing.JLabel labelVidaAzul;
    private javax.swing.JLabel labelVidaVermelho;
    private javax.swing.JList listaCartasMaoAzul;
    private javax.swing.JList listaCartasMaoVermelho;
    private javax.swing.JLabel nomeDeckAzul;
    private javax.swing.JLabel nomeDeckVermelho;
    private javax.swing.JLabel numCartasDeckAzul;
    private javax.swing.JLabel numCartasDeckVermelho;
    private javax.swing.JPanel panelAzul;
    private javax.swing.JPanel panelCampoAzul;
    private javax.swing.JPanel panelCampoVermelho;
    private javax.swing.JPanel panelVermelho;
    private javax.swing.JLabel vezAzul;
    private javax.swing.JLabel vezVermelho;
    // End of variables declaration//GEN-END:variables
}
