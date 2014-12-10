/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardGame.Campo;

import CardGame.Cartas.Carta;
import CardGame.Usuario.Jogador;
import javax.swing.JDialog;

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
            if (campoAzul.carta[5] == null) {
                campoAzul.carta[5] = carta;
                return true;
            } else if (campoAzul.carta[6] == null) {
                campoAzul.carta[6] = carta;
                return true;
            }
        } else {
            Carta carta = jogadorVermelho.pegarCarta(id);
            if (campoVermelho.carta[5] == null) {
                campoVermelho.carta[5] = carta;
                return true;
            } else if (campoVermelho.carta[6] == null) {
                campoVermelho.carta[6] = carta;
                return true;
            }
        }
        
        return false;
    }

    public boolean finalizarTurnoJogadorAtual() {
        return true;
    }

    public boolean remanejarCartas() {
        return true;
    }

    public boolean subirCarta(int posicao) {
        return true;
    }

    private int AchaMelhorCandidato(int posicao) {
        return 0;
    }

    private boolean MoverCarta(int origem, int destino) {
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
