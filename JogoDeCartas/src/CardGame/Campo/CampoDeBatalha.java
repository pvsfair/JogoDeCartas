/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame.Campo;

import CardGame.Cartas.Carta;
import CardGame.Cartas.Monstro;
import CardGame.Usuario.Jogador;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Paulo Victor
 */
public class CampoDeBatalha extends javax.swing.JDialog {

    public enum Lado {

        AZUL, VERMELHO
    }

    protected Jogador jogadorAzul;
    protected Jogador jogadorVermelho;
    protected Campo campoAzul = new Campo();
    protected Campo campoVermelho = new Campo();
    protected int turno = 1;
    private boolean controladorTurno = false;
    protected Lado jogadorDaVez;

    public CampoDeBatalha(Jogador jogadorAzul, Jogador jogadorVermelho) {
        super((JDialog) null, true);
        this.jogadorAzul = jogadorAzul;
        this.jogadorVermelho = jogadorVermelho;
        iniciarBatalha();
    }

    private void iniciarBatalha() {
        jogadorDaVez = selecionarJogadorAleatorio();
        jogadorAzul.getBaralho().embaralhaCartas();
        jogadorVermelho.getBaralho().embaralhaCartas();
        for (int i = 0; i < 5; i++) {
            jogadorAzul.comprarCarta();
            jogadorVermelho.comprarCarta();
        }
        inicarTurno();
    }

    protected Lado ladoOposto(Lado lado) {
        if (lado == Lado.AZUL) {
            return Lado.VERMELHO;
        } else {
            return Lado.AZUL;
        }
    }

    private Lado selecionarJogadorAleatorio() {
        if (Math.random() < 0.5) {
            return Lado.AZUL;
        } else {
            return Lado.VERMELHO;
        }

    }

    public boolean invocarCarta(int id, Lado lado) {
        if (lado == Lado.AZUL) {
            Carta carta = jogadorAzul.pegarCarta(id);
            if (!jogadorAzul.invocarCarta(carta)) {
                return false;
            }
            if (campoAzul.cartas[5] == null) {
                campoAzul.cartas[5] = carta;
                return true;
            } else if (campoAzul.cartas[6] == null) {
                campoAzul.cartas[6] = carta;
                return true;
            }
        } else {
            Carta carta = jogadorVermelho.pegarCarta(id);
            if (!jogadorVermelho.invocarCarta(carta)) {
                return false;
            }
            if (campoVermelho.cartas[5] == null) {
                campoVermelho.cartas[5] = carta;
                return true;
            } else if (campoVermelho.cartas[6] == null) {
                campoVermelho.cartas[6] = carta;
                return true;
            }
        }

        return false;
    }

    public boolean finalizarTurnoJogadorAtual() {
        if (jogadorDaVez == Lado.AZUL) {
            if (!jogadorAzul.avaliarFimTurno()) {
                return false;
            }
            if (campoAzul.cartas[5] != null) {
                campoAzul.subirCarta(5);
            }
            if (campoAzul.cartas[6] != null) {
                campoAzul.subirCarta(6);
            }
        } else {
            if (!jogadorVermelho.avaliarFimTurno()) {
                return false;
            }
            if (campoVermelho.cartas[5] != null) {
                campoVermelho.subirCarta(5);
            }
            if (campoVermelho.cartas[6] != null) {
                campoVermelho.subirCarta(6);
            }
        }
        if (controladorTurno) {
            turno++;
        }
        controladorTurno = !controladorTurno;
        jogadorDaVez = ladoOposto(jogadorDaVez);
        inicarTurno();
        return true;
    }

    public void inicarTurno() {
        if (jogadorDaVez == Lado.AZUL) {
            jogadorAzul.iniciarTurno(turno);
        } else {
            jogadorVermelho.iniciarTurno(turno);
        }
    }

    public boolean atacar(int posicaoCarta) {
        int limiteAtaque = posicaoCarta;
        limiteAtaque -= (limiteAtaque % 2 == 1) ? ((limiteAtaque != 1) ? 2 : 1) : 1;
        if (jogadorDaVez == Lado.AZUL) {
            Monstro origem = (Monstro) campoAzul.cartas[posicaoCarta];
            ArrayList<Monstro> monstrosAdversarios = new ArrayList<>();
            for (int i = limiteAtaque; i < 5; i++) {
                if (campoVermelho.cartas[i] != null) {
                    monstrosAdversarios.add((Monstro) campoVermelho.cartas[i]);
                }
            }
            //Convertendo o ArrayList em um array
            if (monstrosAdversarios.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não pode atacar nenhum monstro inimigo.");
                return false;
            }
            Monstro[] monstros = new Monstro[monstrosAdversarios.size()];
            monstrosAdversarios.toArray(monstros);

            Monstro monstroAlvo = (Monstro) JOptionPane.showInputDialog(null,
                    "Escolha o monstro adversário a ser atacado", "ATACAR!!!",
                    JOptionPane.INFORMATION_MESSAGE, null, monstros, monstros[0]);
            if (origem.atacar(monstroAlvo)) {
                campoVermelho.cartas[campoVermelho.getPosicaoCarta(monstroAlvo)] = null;
                campoVermelho.remanejarCartas();
                JOptionPane.showMessageDialog(null, "Você destruiu uma carta adversária!!");
                jogadorVermelho.setVida(jogadorVermelho.getVida() - 1);
                if (jogadorVermelho.getVida() <= 0) {
                    dispose();
                }
            }
        } else {
            Monstro origem = (Monstro) campoVermelho.cartas[posicaoCarta];
            String cartasAdversario = "";
            ArrayList<Monstro> monstrosAdversarios = new ArrayList<>();
            for (int i = limiteAtaque; i < 5; i++) {
                if (campoAzul.cartas[i] != null) {
                    monstrosAdversarios.add((Monstro) campoAzul.cartas[i]);
                }
            }
            //Convertendo o ArrayList em um array
            if (monstrosAdversarios.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não pode atacar nenhum monstro inimigo.");
                return false;
            }
            Monstro[] monstros = new Monstro[monstrosAdversarios.size()];
            monstrosAdversarios.toArray(monstros);

            Monstro monstroAlvo = (Monstro) JOptionPane.showInputDialog(null,
                    "Escolha o monstro adversário a ser atacado", "ATACAR!!!",
                    JOptionPane.INFORMATION_MESSAGE, null, monstros, monstros[0]);
            if (origem.atacar(monstroAlvo)) {
                campoAzul.cartas[campoAzul.getPosicaoCarta(monstroAlvo)] = null;
                campoAzul.remanejarCartas();
                JOptionPane.showMessageDialog(null, "Você destruiu uma carta adversária!!");
                jogadorAzul.setVida(jogadorAzul.getVida() - 1);
                if (jogadorAzul.getVida() <= 0) {
                    dispose();
                }
            }
        }
        return true;
    }

    public int getTurno() {
        return turno;
    }

    public Lado getJogadorDaVez() {
        return jogadorDaVez;
    }

    public void setJogadorDaVez(Lado jogadorDaVez) {
        this.jogadorDaVez = jogadorDaVez;
    }
}
